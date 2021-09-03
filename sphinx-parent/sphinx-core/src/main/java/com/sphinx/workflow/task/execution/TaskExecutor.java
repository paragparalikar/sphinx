package com.sphinx.workflow.task.execution;

import com.sphinx.workflow.task.Task;

public interface TaskExecutor<T extends Task> {

	void execute(T task, Object payload);
	
}
