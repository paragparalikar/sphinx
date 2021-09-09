package com.sphinx.workflow.task.execution;

import java.time.LocalDateTime;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.sphinx.user.User;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskExecutionService {

	private final TaskExecutionRepository taskExecutionRepository;
	
	public void decide(
			@NonNull final User user,
			@NonNull final String decision,
			@NonNull final TaskExecution taskExecution) {
		
		if(!TaskExecutionStatus.PENDING.equals(taskExecution.getStatus())) {
			throw new IllegalArgumentException("Task must be in pending status to complete");
		}
		
		if(null == taskExecution.getAssignees() || taskExecution.getAssignees().isEmpty()) {
			throw new IllegalArgumentException("Task is not assigned to anyone");
		}
		
		if(null == taskExecution.getAssignees() 
				|| taskExecution.getAssignees().isEmpty()
				|| !taskExecution.getAssignees().stream().anyMatch(Predicate.isEqual(user.getUsername()))) {
			throw new IllegalArgumentException("Task is not assigned to user with id " + user.getUsername());
		}
		
		taskExecution.setDecision(decision);
		taskExecution.setTimestamp(LocalDateTime.now());
		taskExecution.setCompletedBy(user.getUsername());
		taskExecution.setStatus(TaskExecutionStatus.COMPLETED);
		taskExecutionRepository.save(taskExecution);
	}
	
}
