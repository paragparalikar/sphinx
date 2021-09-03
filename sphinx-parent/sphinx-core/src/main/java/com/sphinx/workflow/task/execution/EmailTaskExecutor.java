package com.sphinx.workflow.task.execution;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.EmailTask;

@Component
public class EmailTaskExecutor implements TaskExecutor<EmailTask> {

	@Override
	public void execute(EmailTask task, Object payload) {
		
	}

}
