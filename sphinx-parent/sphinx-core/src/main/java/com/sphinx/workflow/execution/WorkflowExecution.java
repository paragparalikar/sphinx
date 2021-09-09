package com.sphinx.workflow.execution;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "taskExecutions")
public class WorkflowExecution {
	
	public static WorkflowExecution of(Workflow workflow) {
		final Set<TaskExecution> taskExecutions = workflow.getData().values().stream()
			.map(Node::getData)
			.map(TaskExecution::of)
			.collect(Collectors.toSet());
		return WorkflowExecution.builder()
				.workflow(workflow)
				.taskExecutions(taskExecutions)
				.build();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@ManyToOne(optional = false)
	private Workflow workflow;
	
	@NonNull
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
