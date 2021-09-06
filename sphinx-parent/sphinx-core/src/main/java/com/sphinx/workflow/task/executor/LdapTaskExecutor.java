package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class LdapTaskExecutor implements TaskExecutor {

	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Object payload) {
		System.out.println(taskExecution);
		return TaskExecutionStatus.COMPLETED;
	}

}
