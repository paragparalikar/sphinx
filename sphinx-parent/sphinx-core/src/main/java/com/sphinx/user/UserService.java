package com.sphinx.user;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public List<User> findSuggestions(final String text){
		if(!StringUtils.hasText(text)) return Collections.emptyList();
		final Pageable pageable = PageRequest.of(0, 10, Sort.by("lastName", "firstName", "middleName"));
		final String lowerCasePattern = "%" + text.trim().toLowerCase() + "%";
		return userRepository.findSuggestions(lowerCasePattern, pageable);
	}
	
	public User findById(Long userId) {
		return userRepository.findById(userId).orElse(null);
	}
	
}
