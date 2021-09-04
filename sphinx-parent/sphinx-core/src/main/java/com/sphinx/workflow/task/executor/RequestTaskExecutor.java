package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.RequestTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class RequestTaskExecutor implements TaskExecutor<RequestTask> {

	@Override
	public TaskExecutionStatus execute(TaskExecution<RequestTask> taskExecution, Object payload) {
		return TaskExecutionStatus.COMPLETED;
	}

}
