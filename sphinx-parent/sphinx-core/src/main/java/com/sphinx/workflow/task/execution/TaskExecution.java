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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity 
@NoArgsConstructor
@AllArgsConstructor
public class TaskExecution {  

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Task task;
	
	@ManyToMany
	private Set<User> assignees = new HashSet<>();
	
	@ManyToOne
	private User completedBy;
	
	private String decision;
	
	@ManyToOne(optional = false)
	private WorkflowExecution workflowExecution;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskExecutionStatus status = TaskExecutionStatus.NEW;
	
	private LocalDateTime timestamp = LocalDateTime.now();
	
}