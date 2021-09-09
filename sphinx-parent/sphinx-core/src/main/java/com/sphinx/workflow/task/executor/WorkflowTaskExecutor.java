package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.request.Request;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class WorkflowTaskExecutor implements TaskExecutor {

	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Request request) {
		return TaskExecutionStatus.COMPLETED;
	}


}
