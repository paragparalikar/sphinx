package com.sphinx.workflow.task;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Builder
public class TaskExecution {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional = false)
	private Task task;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TaskExecutionStatus status;
	
	private LocalDateTime startTimestamp;
	
	private LocalDateTime endTimestamp;
	
}
