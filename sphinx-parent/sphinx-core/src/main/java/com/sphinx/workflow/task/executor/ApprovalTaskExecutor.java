package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;

import com.sphinx.request.Request;
import com.sphinx.request.resolver.ApproverResolver;
import com.sphinx.user.User;
import com.sphinx.workflow.task.ApprovalTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionRepository;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ApprovalTaskExecutor implements TaskExecutor {

	private final ApproverResolver approverResolver;
	private final TaskExecutionRepository taskExecutionRepository;
	
	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Request request) {
		final ApprovalTask approvalTask = ApprovalTask.class.cast(taskExecution.getTask());
		final User approver = approverResolver.resolve(request, approvalTask.getApprover(), approvalTask.getApplicationId());
		taskExecution.getAssignees().add(approver.getUsername());
		taskExecutionRepository.saveAndFlush(taskExecution);
		return TaskExecutionStatus.PENDING;
	}

}
