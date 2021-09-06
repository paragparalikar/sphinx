package com.sphinx.workflow.task.execution;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.sphinx.user.User;
import com.sphinx.user.UserService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskExecutionService {

	private final UserService userService;
	private final TaskExecutionRepository taskExecutionRepository;
	
	public void complete(
			@NonNull final Long taskExecutionId, 
			@NonNull final Long userId, 
			@NonNull final String decision) {
		
		final TaskExecution taskExecution = taskExecutionRepository.findById(taskExecutionId)
				.orElseThrow(() -> new IllegalArgumentException("No task execution found by id " + taskExecutionId));
		
		if(!TaskExecutionStatus.PENDING.equals(taskExecution.getStatus())) {
			throw new IllegalArgumentException("Task must be in pending status to complete");
		}
		
		if(null == taskExecution.getAssignees() || taskExecution.getAssignees().isEmpty()) {
			throw new IllegalArgumentException("Task is not assigned to anyone");
		}
		
		final User user = userService.findById(userId);
		if(null == user) {
			throw new IllegalArgumentException("No user found for id " + userId);
		}
		
		if(taskExecution.getAssignees().stream().map(User::getId).anyMatch(Predicate.isEqual(userId))) {
			throw new IllegalArgumentException("Task is not assigned to user with id " + userId);
		}
		
		taskExecution.setCompletedBy(user);
		taskExecution.setDecision(decision);
		taskExecution.setTimestamp(LocalDateTime.now());
		taskExecution.setStatus(TaskExecutionStatus.COMPLETED);
		taskExecutionRepository.save(taskExecution);
	}
	
}
