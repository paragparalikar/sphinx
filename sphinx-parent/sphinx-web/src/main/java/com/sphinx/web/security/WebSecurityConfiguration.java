package com.sphinx.web.security;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphinx.user.User;
import com.sphinx.web.security.jwt.JwtBuilder;
import com.sphinx.web.security.jwt.JwtParser;
import com.sphinx.web.security.jwt.JwtVerifier;
import com.sphinx.web.user.UserDTO;
import com.sphinx.web.user.UserMapper;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	private final JwtParser jwtParser;
	private final JwtBuilder jwtBuilder;
	private final JwtVerifier jwtVerifier;
	private final UserMapper userMapper;
	private final ObjectMapper objectMapper;
	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return JwtAuthenticationFilter.builder()
				.jwtParser(jwtParser)
				.jwtVerifier(jwtVerifier)
				.userDetailsService(userDetailsService)
				.build();
	}
	
	@Bean
	public JsonCredentialsAuthenticationFilter jsonCredentialsuthenticationFilter() throws Exception {
		final JsonCredentialsAuthenticationFilter authenticationFilter = new JsonCredentialsAuthenticationFilter();
		authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
		authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
		authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
		authenticationFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationFilter;
	}

	private void loginSuccessHandler(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		response.setStatus(HttpStatus.OK.value());
		response.setHeader(HttpHeaders.AUTHORIZATION, jwtBuilder.buildJwt(authentication));
		final User user = User.class.cast(authentication.getPrincipal());
		final UserDTO dto = userMapper.entityToDTO(user);
		objectMapper.writeValue(response.getWriter(), dto);
	}

	private void loginFailureHandler(final HttpServletRequest request, final HttpServletResponse response,
			final AuthenticationException e) throws IOException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
	}

	private void logoutSuccessHandler(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication authentication) throws IOException {
		response.setStatus(HttpStatus.OK.value());
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOriginPatterns(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors().and()
			.authorizeRequests().anyRequest().authenticated().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.addFilterBefore(jsonCredentialsuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthenticationFilter(), JsonCredentialsAuthenticationFilter.class)
			.logout().logoutUrl("/logout").logoutSuccessHandler(this::logoutSuccessHandler).and()
			.exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}

}
