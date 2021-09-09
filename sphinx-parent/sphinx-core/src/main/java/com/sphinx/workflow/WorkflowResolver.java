package com.sphinx.workflow;

import org.springframework.stereotype.Component;

import com.sphinx.request.AccessRequest;
import com.sphinx.request.Request;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkflowResolver {

	private final WorkflowRepository workflowRepository;

	public Workflow resolve(Request request) {
		switch (request.getType()) {
		case FORM:
			return workflowRepository.findOneByNameIgnoreCase(Workflow.FORM);
		case WORKFLOW:
			return workflowRepository.findOneByNameIgnoreCase(Workflow.WORKFLOW);
		case ACCESS:
			return AccessRequest.class.cast(request).getForm().getWorkflow();
		default:
			return null;
		}
	}

}
