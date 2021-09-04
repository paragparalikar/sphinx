package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.TransformerTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class TransformerTaskExecutor implements TaskExecutor<TransformerTask> {

	@Override
	public TaskExecutionStatus execute(TaskExecution<TransformerTask> taskExecution, Object payload) {
		return null;
	}

}
