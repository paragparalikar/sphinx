package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.LdapTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class LdapTaskExecutor implements TaskExecutor<LdapTask> {

	@Override
	public TaskExecutionStatus execute(TaskExecution<LdapTask> taskExecution, Object payload) {
		return null;
	}

}
