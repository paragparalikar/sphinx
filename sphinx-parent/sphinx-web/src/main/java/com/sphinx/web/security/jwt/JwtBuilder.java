package com.sphinx.web.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;

import com.sphinx.user.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class JwtBuilder {

	private final String secret;
	private final Long expiryMillis;
	
	public String buildJwt(Authentication authentication) {
		final User user = User.class.cast(authentication.getPrincipal());
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + expiryMillis))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
}
