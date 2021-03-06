package com.sphinx.web.workflow;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphinx.request.RequestType;
import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.WorkflowRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WorkflowConfiguration {
	
	private final ObjectMapper objectMapper;
	private final WorkflowMapper workflowMapper;
	private final WorkflowRepository workflowRepository;
	
	private void setupWorkflow(RequestType type, Resource resource) throws JsonParseException, JsonMappingException, IOException {
		if(!workflowRepository.existsByRequestType(type)) {
			final WorkflowDetailsDTO dto = objectMapper.readValue(resource.getInputStream(), WorkflowDetailsDTO.class);
			final Workflow workflow = workflowMapper.dtoToEntity(dto);
			workflow.setRequestType(type);
			workflowRepository.saveAndFlush(workflow);
		}
	}
	
	@Bean
	public CommandLineRunner workflowWorkflowSetup(@Value("${sphinx.workflows.workflow.json:classpath:workflows/workflow-workflow.json}") Resource json) {
		return args -> setupWorkflow(RequestType.WORKFLOW, json);
	}
	
	@Bean
	public CommandLineRunner formWorkflowSetup(@Value("${sphinx.workflows.form.json:classpath:workflows/workflow-form.json}") Resource json) {
		return args -> setupWorkflow(RequestType.FORM, json);
	}
}
