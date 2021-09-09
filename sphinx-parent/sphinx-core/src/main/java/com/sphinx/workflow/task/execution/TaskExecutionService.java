package com.sphinx.workflow.task.execution;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.sphinx.request.Request;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskExecutionService {

	private final UserDetailsService userService;
	private final TaskExecutionRepository taskExecutionRepository;
	
	public void complete(
			@NonNull final Long taskExecutionId, 
			@NonNull final String username, 
			@NonNull final String decision) {
		
		final TaskExecution taskExecution = taskExecutionRepository.findById(taskExecutionId)
				.orElseThrow(() -> new IllegalArgumentException("No task execution found by id " + taskExecutionId));
		
		if(!TaskExecutionStatus.PENDING.equals(taskExecution.getStatus())) {
			throw new IllegalArgumentException("Task must be in pending status to complete");
		}
		
		if(null == taskExecution.getAssignees() || taskExecution.getAssignees().isEmpty()) {
			throw new IllegalArgumentException("Task is not assigned to anyone");
		}
		
		final UserDetails user = userService.loadUserByUsername(username);
		if(null == user) {
			throw new IllegalArgumentException("No user found for id " + username);
		}
		
		if(taskExecution.getAssignees().stream().anyMatch(Predicate.isEqual(user.getUsername()))) {
			throw new IllegalArgumentException("Task is not assigned to user with id " + username);
		}
		
		taskExecution.setCompletedBy(username);
		taskExecution.setDecision(decision);
		taskExecution.setTimestamp(LocalDateTime.now());
		taskExecution.setStatus(TaskExecutionStatus.COMPLETED);
		taskExecutionRepository.save(taskExecution);
	}
	
}
