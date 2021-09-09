package com.sphinx.request.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sphinx.request.Request;
import com.sphinx.request.RequestType;
import com.sphinx.request.WorkflowRequest;
import com.sphinx.workflow.Workflow;

public class ImmutablePayloadNameValidator implements ConstraintValidator<ImmutablePayloadName, Request> {

	@Override
	public boolean isValid(Request request, ConstraintValidatorContext context) {
		if(RequestType.WORKFLOW.equals(request.getType())
				&& StringUtils.hasText(request.getPayload())
				&& null != request.getTarget()) {
			final WorkflowRequest workflowRequest = WorkflowRequest.class.cast(request);
			final Workflow workflow = workflowRequest.getWorkflow();
			if(!workflow.isNameMutable()) {
				try {
					final Workflow object = new ObjectMapper().readValue(request.getPayload(), Workflow.class);
					return workflow.getName().trim().equalsIgnoreCase(object.getName().trim());
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

}
