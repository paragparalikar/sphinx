package com.sphinx.workflow.task.executor;

import java.util.EnumMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.task.TaskType;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TaskExecutorFactory {

	private final RequestTaskExecutor requestTaskExecutor;
	private final EmailTaskExecutor emailTaskExecutor;
	private final ApprovalTaskExecutor approvalTaskExecutor;
	private final TransformerTaskExecutor transformerTaskExecutor;
	private final LdapTaskExecutor ldapTaskExecutor;
	private final FormTaskExecutor formTaskExecutor;
	private final WorkflowTaskExecutor workflowTaskExecutor;
	private final Map<TaskType, TaskExecutor> cache = new EnumMap<>(TaskType.class);
	
	@PostConstruct
	public void init() {
		cache.put(TaskType.REQUEST, requestTaskExecutor);
		cache.put(TaskType.EMAIL, emailTaskExecutor);
		cache.put(TaskType.APPROVAL, approvalTaskExecutor);
		cache.put(TaskType.TRANSFORMER, transformerTaskExecutor);
		cache.put(TaskType.LDAP, ldapTaskExecutor);
		cache.put(TaskType.FORM, formTaskExecutor);
		cache.put(TaskType.WORKFLOW, workflowTaskExecutor);
	}
	
	public TaskExecutor getTaskHandler(@NonNull TaskType type){
		return cache.get(type);
	}
	
}
