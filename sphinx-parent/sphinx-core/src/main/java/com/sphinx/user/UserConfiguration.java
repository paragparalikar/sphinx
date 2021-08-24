package com.sphinx.user;

import java.util.LinkedList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class UserConfiguration {

	@Bean
	@Profile("local")
	public CommandLineRunner intUsers(UserRepository userRepository) {
		return args -> {
			final List<User> users = new LinkedList<>();
			for(int index = 1; index <= 100; index++) {
				final User user = new User();
				user.setFirstName("Fname"+index);
				user.setMiddleName("Mname"+index);
				user.setLastName("Lname"+index);
				final UserIdentifier identifier = new UserIdentifier();
				identifier.setSystem("sys"+index);
				identifier.setIdentifier("id"+index);
				user.getIdentifiers().add(identifier);
				users.add(user);
			}
			userRepository.saveAllAndFlush(users);
		};
	}
	
}
