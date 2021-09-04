package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.ApprovalTask;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

@Component
public class ApprovalTaskExecutor implements TaskExecutor<ApprovalTask> {

	@Override
	public TaskExecutionStatus execute(ApprovalTask task, Object payload) {
		return null;
	}

}
