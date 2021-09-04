package com.sphinx.workflow.task.executor;

import com.sphinx.workflow.task.Task;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

public interface TaskExecutor<T extends Task> {

	TaskExecutionStatus execute(T task, Object payload);
	
}
