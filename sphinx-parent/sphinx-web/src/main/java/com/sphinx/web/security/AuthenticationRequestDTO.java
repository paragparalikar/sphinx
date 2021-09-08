package com.sphinx.web.security;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AuthenticationRequestDTO implements Serializable {
	private static final long serialVersionUID = 5442712872766038419L;

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
}
