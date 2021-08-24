package com.sphinx.user;

import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	public List<User> findSuggestions(final String text){
		if(!StringUtils.hasText(text)) return Collections.emptyList();
		final Pageable pageable = PageRequest.ofSize(10);
		final String lowerCasePattern = "%" + text.trim().toLowerCase() + "%";
		return userRepository.findSuggestions(lowerCasePattern, pageable);
	}
	
}
