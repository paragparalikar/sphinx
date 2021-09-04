package com.sphinx.workflow.task.execution;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.sphinx.user.User;
import com.sphinx.workflow.execution.WorkflowExecution;
import com.sphinx.workflow.task.Task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecution<T extends Task> {
	
	public static <T extends Task> TaskExecution<T> of(T task, WorkflowExecution workflowExecution) {
		return TaskExecution.<T>builder()
				.task(task)
				.workflowExecution(workflowExecution)
				.build();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private T task;
	
	@ManyToMany
	@Builder.Default
	private Set<User> assignees = new HashSet<>();
	
	@ManyToOne
	private User completedBy;
	
	private String decision;
	
	@ManyToOne
	private WorkflowExecution workflowExecution;
	
	@Builder.Default
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskExecutionStatus status = TaskExecutionStatus.NEW;
	
	@Builder.Default
	private LocalDateTime timestamp = LocalDateTime.now();
	
}
