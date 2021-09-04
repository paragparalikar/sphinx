package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.TransformerTask;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class TransformerTaskExecutor implements TaskExecutor<TransformerTask> {

	@Override
	public TaskExecutionStatus execute(TransformerTask task, Object payload) {
		return null;
	}

}
