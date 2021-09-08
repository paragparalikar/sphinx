package com.sphinx.user;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class User implements UserDetails {
	private static final long serialVersionUID = 4751074524523952146L;
	
	@NonNull 
	private final String firstName;
	
	private final String middleName;
	
	private final String lastName;
	
	@NonNull 
	private final String username;
	
	private final String password;
	
	private final String manager;
	
	@NonNull 
	private final String email;
	
	@Builder.Default
	private final boolean enabled = true;
	
	@Builder.Default
	private final boolean accountNonExpired = true;
	
	@Builder.Default
	private final boolean accountNonLocked = true;
	
	@Builder.Default
	private final boolean credentialsNonExpired = true;
	
	@Builder.Default
	private final Collection<? extends GrantedAuthority> authorities = Collections.emptyList();

}
