package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.EmailTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class EmailTaskExecutor implements TaskExecutor<EmailTask> {

	@Override
	public TaskExecutionStatus execute(TaskExecution<EmailTask> taskExecution, Object payload) {
		return null;
	}

}
