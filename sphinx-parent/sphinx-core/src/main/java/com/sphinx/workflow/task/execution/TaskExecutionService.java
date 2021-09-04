package com.sphinx.workflow.task.execution;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskExecutionService {

	private final TaskExecutionRepository taskExecutionRepository;
	
}
