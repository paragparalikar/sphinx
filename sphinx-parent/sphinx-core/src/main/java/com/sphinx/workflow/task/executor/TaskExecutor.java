package com.sphinx.workflow.task.executor;

import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

public interface TaskExecutor {

	TaskExecutionStatus execute(TaskExecution taskExecution, Object payload);
	
}
