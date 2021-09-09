package com.sphinx.web.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.request.Request;
import com.sphinx.request.RequestService;
import com.sphinx.user.User;
import com.sphinx.web.search.PrimengQueries;
import com.sphinx.web.search.SearchBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/requests")
public class RequestController {
	
	private final SearchBuilder searchBuilder;
	private final RequestMapper requestMapper;
	private final RequestService requestService;

	@PostMapping("/pages")
	public Page<RequestDTO> findAll(@RequestBody String pageRequest){
		final PrimengQueries<Request> primengQueries = searchBuilder.process(pageRequest, Request.class, "id", "name",  "type", "status");
		final Page<Request> page = requestService.findAll(primengQueries.getSpec(), primengQueries.getPageQuery());
		final List<RequestDTO> dtos = requestMapper.entityToDTOs(page.getContent());
		return new PageImpl<>(dtos, primengQueries.getPageQuery(), page.getTotalElements());
	}
	
	@PostMapping(value = "/pages", params = "assignee=me")
	public Page<RequestDTO> findAll(@AuthenticationPrincipal User user, @RequestBody String pageRequest){
		final PrimengQueries<Request> primengQueries = searchBuilder.process(pageRequest, Request.class, "id", "name",  "type", "status");
		final Page<Request> page = requestService.findAllByAssignee(user.getUsername(), primengQueries.getSpec(), primengQueries.getPageQuery());
		final List<RequestDTO> dtos = requestMapper.entityToDTOs(page.getContent());
		return new PageImpl<>(dtos, primengQueries.getPageQuery(), page.getTotalElements());
	}
	
	@GetMapping("/{id}")
	public RequestDetailsDTO findById(@PathVariable final Long id) {
		final Request accessRequest = requestService.findById(id);
		return null == accessRequest ? null : requestMapper.entityToDTO(accessRequest);
	}
	
	@PostMapping
	public RequestDetailsDTO create(@RequestBody @Valid final RequestDetailsDTO dto) throws Exception {
		final Request accessRequest = requestMapper.dtoToEntity(dto);
		final Request managedEntity = requestService.save(accessRequest);
		return requestMapper.entityToDTO(managedEntity);
	}
	
	@DeleteMapping("/{id}")
	public void cancel(@PathVariable final Long id) {
		requestService.cancelById(id);
	}
	
}
