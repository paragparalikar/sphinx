package com.sphinx.web.request;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.sphinx.form.Form;
import com.sphinx.request.AccessRequest;
import com.sphinx.request.FormRequest;
import com.sphinx.request.Request;
import com.sphinx.request.RequestType;
import com.sphinx.request.WorkflowRequest;
import com.sphinx.workflow.Workflow;

@Component
public class RequestMapper {
	
	private final Map<RequestType, Function<RequestDetailsDTO, Request>> builders = new EnumMap<>(RequestType.class);
	
	public RequestMapper() {
		builders.put(RequestType.FORM, this::dtoToFormRequest);
		builders.put(RequestType.ACCESS, this::dtoToAccessRequest);
		builders.put(RequestType.WORKFLOW, this::dtoToWorkflowRequest);
	}
	
	public Request dtoToEntity(RequestDetailsDTO dto) {
		return builders.get(dto.getType()).apply(dto);
	}
	
	public FormRequest dtoToFormRequest(RequestDetailsDTO dto) {
		final Form form = new Form();
		form.setId(dto.getTargetId());
		final FormRequest request = new FormRequest();
		request.setTarget(form);
		map(request, dto);
		return request;
	}
	
	public AccessRequest dtoToAccessRequest(RequestDetailsDTO dto) {
		final Form form = new Form();
		form.setId(dto.getTargetId());
		final AccessRequest request = new AccessRequest();
		request.setTarget(form);
		map(request, dto);
		return request; 
	}
	
	public WorkflowRequest dtoToWorkflowRequest(RequestDetailsDTO dto) {
		final Workflow workflow = new Workflow();
		workflow.setId(dto.getTargetId());
		final WorkflowRequest request = new WorkflowRequest();
		request.setTarget(workflow);
		map(request, dto);
		return request; 
	}
	
	private void map(Request request, RequestDetailsDTO dto) {
		request.setId(dto.getId());
		request.setType(dto.getType());
		request.setPayload(dto.getPayload());
	}
	
	public RequestDetailsDTO entityToDTO(Request request) {
		final RequestDetailsDTO dto = new RequestDetailsDTO();
		dto.setPayload(request.getPayload());
		map(dto, request);
		return dto;
	}
	
	public RequestDTO entityToThinDTO(Request request) {
		final RequestDTO dto = new RequestDTO();
		map(dto, request);
		return dto;
	}
	
	private void map(RequestDTO dto, Request request) {
		dto.setCreateTimestamp(request.getCreateTimestamp());
		dto.setId(request.getId());
		dto.setStatus(request.getStatus());
		dto.setTargetId(request.getTarget().getId());
		dto.setTargetName(request.getTarget().getName());
		dto.setType(request.getType());
		dto.setUpdateTimestamp(request.getUpdateTimestamp());
	}
	
	public List<RequestDTO> entityToDTOs(List<Request> requests) {
		return requests.stream().map(this::entityToThinDTO).collect(Collectors.toList());
	}
	
}
