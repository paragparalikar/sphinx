package com.sphinx.workflow.execution;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.node.Node;
import com.sphinx.workflow.task.execution.TaskExecution;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkflowExecution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Workflow workflow;
	
	@Builder.Default
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkflowExecutionStatus status = WorkflowExecutionStatus.NEW;
	
	@Builder.Default
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Set<TaskExecution> taskExecutions = new HashSet<>();
	
	public TaskExecution getTaskExecution(Node node) {
		if(null == node || null == taskExecutions) return null;
		return taskExecutions.stream()
				.filter(taskExecution -> Objects.equals(taskExecution.getTask(), node.getData()))
				.findFirst()
				.orElse(null);
	}
	
}
