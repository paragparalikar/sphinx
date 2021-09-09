package com.sphinx.workflow.task.executor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphinx.form.Form;
import com.sphinx.form.FormRepository;
import com.sphinx.request.FormRequest;
import com.sphinx.request.Request;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FormTaskExecutor implements TaskExecutor {

	private final ObjectMapper objectMapper;
	private final FormRepository formRepository;
	
	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Request request) throws JsonMappingException, JsonProcessingException {
		final FormRequest formRequest = FormRequest.class.cast(request);
		if(!StringUtils.hasText(request.getPayload())) {
			formRepository.delete(formRequest.getForm());
		} else {
			final Form object = objectMapper.readValue(request.getPayload(), Form.class);
			formRepository.saveAndFlush(object);
		}
		return TaskExecutionStatus.COMPLETED;
	}

}
