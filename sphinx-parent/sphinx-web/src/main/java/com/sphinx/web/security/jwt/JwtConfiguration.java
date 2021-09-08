package com.sphinx.web.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtConfiguration {

	@Value("${sphinx.jwt.secret:sphinx}")
	private String secret;
	
	@Value("${sphinx.jwt.expiry-millis:1800000}")
	private Long expiryMillis;
	
	@Bean
	public JwtBuilder jwtBuilder() {
		return new JwtBuilder(secret, expiryMillis);
	}
	
	@Bean
	public JwtParser jwtParser() {
		return new JwtParser(secret);
	}
	
	@Bean
	public JwtVerifier jwtVerifier() {
		return new JwtVerifier(secret);
	}
}
