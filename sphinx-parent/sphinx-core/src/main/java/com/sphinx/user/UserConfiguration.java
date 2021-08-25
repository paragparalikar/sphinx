package com.sphinx.user;

import java.util.Arrays;

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
			final User parag = User.builder()
					.firstName("Parag")
					.middleName("Kiran")
					.lastName("Paralikar")
					.identifier(UserIdentifier.builder()
							.system("bank")
							.identifier("xbbnn7a")
							.build())
					.identifier(UserIdentifier.builder()
							.system("google")
							.identifier("parag.paralikar@gmail.com")
							.build())
					.build();
			final User yogesh = User.builder()
					.firstName("Yogesh")
					.lastName("Sing")
					.identifier(UserIdentifier.builder()
							.system("bank")
							.identifier("xbbabc1")
							.build())
					.identifier(UserIdentifier.builder()
							.system("google")
							.identifier("ykstomer")
							.build())
					.build();
			userRepository.saveAllAndFlush(Arrays.asList(parag, yogesh));
		};
	}
	
}
