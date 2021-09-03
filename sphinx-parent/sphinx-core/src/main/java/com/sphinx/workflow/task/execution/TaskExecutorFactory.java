package com.sphinx.workflow.task.execution;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.ApprovalTask;
import com.sphinx.workflow.task.EmailTask;
import com.sphinx.workflow.task.LdapTask;
import com.sphinx.workflow.task.RequestTask;
import com.sphinx.workflow.task.Task;
import com.sphinx.workflow.task.TransformerTask;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskExecutorFactory {

	private final RequestTaskExecutor requestTaskExecutor;
	private final EmailTaskExecutor emailTaskExecutor;
	private final ApprovalTaskExecutor approvalTaskExecutor;
	private final TransformerTaskExecutor transformerTaskExecutor;
	private final LdapTaskExecutor ldapTaskExecutor;
	private final Map<Class<? extends Task>, TaskExecutor<? extends Task>> cache = new HashMap<>();
	
	@PostConstruct
	public void init() {
		cache.put(RequestTask.class, requestTaskExecutor);
		cache.put(EmailTask.class, emailTaskExecutor);
		cache.put(ApprovalTask.class, approvalTaskExecutor);
		cache.put(TransformerTask.class, transformerTaskExecutor);
		cache.put(LdapTask.class, ldapTaskExecutor);
	}
	
	@SuppressWarnings("unchecked")
	public TaskExecutor<Task> getTaskHandlerFor(Object task){
		return (TaskExecutor<Task>) cache.get(task.getClass());
	}
	
}
