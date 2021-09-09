package com.sphinx.workflow.task.executor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.EmailTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailTaskExecutor implements TaskExecutor {
	
	private final JavaMailSender javaMailSender;

	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Object payload) {
		final EmailTask emailTask = EmailTask.class.cast(taskExecution.getTask());
		
		return TaskExecutionStatus.COMPLETED;
	}

}
