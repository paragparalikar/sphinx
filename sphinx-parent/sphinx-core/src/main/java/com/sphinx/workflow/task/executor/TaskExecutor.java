package com.sphinx.workflow.task.executor;

import com.sphinx.workflow.task.Task;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

public interface TaskExecutor<T extends Task> {

	TaskExecutionStatus execute(TaskExecution<T> taskExecution, Object payload);
	
}
