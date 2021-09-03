package com.sphinx.workflow.task.execution;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.RequestTask;

@Component
public class RequestTaskExecutor implements TaskExecutor<RequestTask> {

	@Override
	public void execute(RequestTask task, Object payload) {
		
	}

}
