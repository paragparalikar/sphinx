package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphinx.request.Request;
import com.sphinx.request.WorkflowRequest;
import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.WorkflowRepository;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkflowTaskExecutor implements TaskExecutor {
	
	private final ObjectMapper objectMapper;
	private final WorkflowRepository workflowRepository;

	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Request request) throws JsonMappingException, JsonProcessingException {
		final WorkflowRequest workflowRequest = WorkflowRequest.class.cast(request);
		if(!StringUtils.hasText(request.getPayload())) {
			final Workflow workflow = workflowRequest.getWorkflow();
			workflowRepository.delete(workflow);
		} else {
			final Workflow object = objectMapper.readValue(request.getPayload(), Workflow.class);
			object.getData().values().forEach(node -> {
				if(null != node.getInputs()) {
					node.getInputs().entrySet().forEach(entry -> entry.getValue().setName(entry.getKey()));
				}
				if(null != node.getOutputs()) {
					node.getOutputs().entrySet().forEach(entry -> entry.getValue().setName(entry.getKey()));
				}
			});
			workflowRepository.saveAndFlush(object);
		}
		return TaskExecutionStatus.COMPLETED;
	}


}
