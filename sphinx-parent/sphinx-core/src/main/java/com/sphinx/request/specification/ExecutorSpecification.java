package com.sphinx.request.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sphinx.request.Request;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutorSpecification implements Specification<Request> {
	private static final long serialVersionUID = 1L;

	@NonNull private final String username;
	
	@Override
	public Predicate toPredicate(Root<Request> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO add condition for task type = manual
		return criteriaBuilder.isMember(username, root
				.join("workflowExecution")
				.join("taskExecutions")
				.get("assignees"));
	}

}
