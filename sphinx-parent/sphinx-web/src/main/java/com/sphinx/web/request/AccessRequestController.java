package com.sphinx.web.request;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.request.AccessRequest;
import com.sphinx.request.AccessRequestService;
import com.sphinx.web.search.PrimengQueries;
import com.sphinx.web.search.SearchBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping("/requests")
public class AccessRequestController {
	
	private final SearchBuilder searchBuilder;
	private final AccessRequestMapper accessRequestMapper;
	private final AccessRequestService accessRequestService;

	@PostMapping("/pages")
	public Page<AccessRequestDTO> findAll(@RequestBody String pageRequest){
		final PrimengQueries<AccessRequest> primengQueries = searchBuilder.process(pageRequest, AccessRequest.class, "id", "status");
		final Page<AccessRequest> page = accessRequestService.findAll(primengQueries.getSpec(), primengQueries.getPageQuery());
		final List<AccessRequestDTO> dtos = accessRequestMapper.entityToDTOs(page.getContent());
		return new PageImpl<>(dtos, primengQueries.getPageQuery(), page.getTotalElements());
	}
	
	@GetMapping("/{id}")
	public AccessRequestDetailsDTO findById(@PathVariable final Long id) {
		final AccessRequest accessRequest = accessRequestService.findById(id);
		return null == accessRequest ? null : accessRequestMapper.entityToDTO(accessRequest);
	}
	
	@PostMapping
	public AccessRequestDetailsDTO create(@RequestBody @Valid final AccessRequestDetailsDTO dto) {
		final AccessRequest accessRequest = accessRequestMapper.dtoToEntity(dto);
		final AccessRequest managedEntity = accessRequestService.save(accessRequest);
		return accessRequestMapper.entityToDTO(managedEntity);
	}

	@PutMapping
	public AccessRequestDetailsDTO update(@RequestBody @Valid final AccessRequestDetailsDTO dto) {
		final AccessRequest accessRequest = accessRequestMapper.dtoToEntity(dto);
		final AccessRequest managedEntity = accessRequestService.save(accessRequest);
		return accessRequestMapper.entityToDTO(managedEntity);
	}
	
	@DeleteMapping("/{id}")
	public void cancel(@PathVariable final Long id) {
		accessRequestService.cancelById(id);
	}
	
}
