package com.sphinx.workflow.execution;

import java.util.Map;

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
import com.sphinx.workflow.task.TaskExecution;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class WorkflowExecution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Workflow workflow;
	
	private Boolean success;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private WorkflowExecutionStatus status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private Map<Long, TaskExecution> taskExecutions;
	
}
