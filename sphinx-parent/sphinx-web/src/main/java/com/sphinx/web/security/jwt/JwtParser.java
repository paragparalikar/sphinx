package com.sphinx.web.security.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtParser {

	private final String secret;
	
	public String parseUsername(String jwt) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
	}
	
}
