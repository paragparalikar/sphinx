package com.sphinx.request.specification;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.sphinx.request.Request;

@Component
public class RequestSpecificationFactory {

	private final Map<String, Function<String, Specification<Request>>> cache = new HashMap<>();
	
	@PostConstruct
	public void init() {
		cache.put("requester", RequesterSpecification::new);
		cache.put("approver", AssigneeSpecification::new);
		cache.put("executor", ExecutorSpecification::new);
	}
	
	public Specification<Request> get(String username, String role) {
		return cache.get(role).apply(username);
	}
	
}
