package com.sphinx.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	
	private final PasswordEncoder passwordEncoder;
	private final Map<String, User> users = new HashMap<>();
	private final Map<String, User> applicationOwners = new HashMap<>();
	
	@PostConstruct
	public void init() {
		final User richard = User.builder()
				.firstName("Richard")
				.lastName("Barron")
				.username("richard.barron")
				.password(passwordEncoder.encode("test"))
				.email("parag.paralikar@gmail.com")
				.build();
		final User yogesh = User.builder()
				.firstName("Yogesh")
				.lastName("Singh")
				.username("ykstomar")
				.password(passwordEncoder.encode("test"))
				.manager("richard.barron")
				.email("ykstomar@gmail.com")
				.build();
		final User parag = User.builder()
				.firstName("Parag")
				.middleName("Kiran")
				.lastName("Paralikar")
				.username("parag.paralikar")
				.password(passwordEncoder.encode("test"))
				.manager("ykstomar")
				.email("parag.paralikar@gmail.com")
				.build();
		
		users.put(richard.getUsername(), richard);
		users.put(yogesh.getUsername(), yogesh);
		users.put(parag.getUsername(), parag);
		
		applicationOwners.put("IAM", richard);
		applicationOwners.put("EWH", yogesh);
		applicationOwners.put("TYRION", parag);
	}
	
	@Override
	public User loadUserByUsername(@NonNull final String username) throws UsernameNotFoundException {
		return Optional.ofNullable(users.get(username))
				.orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
	}

	public User getOwnerByApplicationId(@NonNull final String applicationId) {
		return Optional.ofNullable(applicationOwners.get(applicationId))
				.orElseThrow(() -> new IllegalArgumentException(applicationId + " not found"));
	}
	
}
