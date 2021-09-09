package com.sphinx.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sphinx.request.resolver.WorkflowResolver;
import com.sphinx.request.specification.RequestSpecificationFactory;
import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.execution.WorkflowExecution;
import com.sphinx.workflow.execution.WorkflowExecutor;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

	private final WorkflowResolver workflowResolver;
	private final WorkflowExecutor workflowExecutor;
	private final RequestRepository requestRepository;
	private final RequestSpecificationFactory requestSpecificationFactory;

	public Page<Request> findAllByRole(
			@NonNull String role, 
			@NonNull String username, 
			@NonNull Specification<Request> spec, 
			@NonNull Pageable pageable){
		final Specification<Request> roleSpec = requestSpecificationFactory.get(username, role);
		return requestRepository.findAll(roleSpec.and(spec), pageable);
	}
	
	public Request save(@NonNull Request request) throws Exception {
		if(null != request.getId()) throw new IllegalArgumentException(
				"Request can only be created/cancelled, but can not be updated");
		final Workflow workflow = workflowResolver.resolve(request);
		request.setWorkflowExecution(WorkflowExecution.of(workflow));
		final Request managedRequest = requestRepository.save(request); 
		workflowExecutor.execute(managedRequest);
		return managedRequest;
	}
	
	public Request findById(@NonNull Long id) {
		return requestRepository.findById(id).orElse(null);
	}
	
	public void cancelById(@NonNull Long id) {
		final Request accessRequest = findById(id);
		if(null != accessRequest) {
			accessRequest.setStatus(RequestStatus.CANCELLED);
			requestRepository.save(accessRequest);
		}
	}
}
