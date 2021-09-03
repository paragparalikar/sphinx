package com.sphinx.web.workflow;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.web.search.PrimengQueries;
import com.sphinx.web.search.SearchBuilder;
import com.sphinx.workflow.WorkflowService;
import com.sphinx.workflow.model.Workflow;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping("/workflows")
@CrossOrigin(origins = "*")
public class WorkflowController {
	
	private final SearchBuilder searchBuilder;
	private final WorkflowMapper workflowMapper;
	private final WorkflowService workflowService;
	
	@GetMapping(value = "/{id}", params = "name")
	public Boolean exists(@PathVariable("id") final Long id, @RequestParam("name") final String name) {
		return workflowService.existsByName(id, name);
	}
	
	@GetMapping(params = "q") 
	public List<WorkflowDTO> findSuggestions(@RequestParam("q") final String query){
		final Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
		final List<Workflow> workflows = workflowService.findSuggestions(query, pageable);
		return workflowMapper.entitiesToDTOs(workflows);
	}

	@PostMapping("/pages")
	public Page<WorkflowDTO> findAll(@RequestBody String pageRequest){
		final PrimengQueries<Workflow> primengQueries = searchBuilder.process(pageRequest, Workflow.class, "id", "name");
		final Page<Workflow> page = workflowService.findAll(primengQueries.getSpec(), primengQueries.getPageQuery());
		final List<WorkflowDTO> dtos = workflowMapper.entitiesToDTOs(page.getContent());
		return new PageImpl<>(dtos, primengQueries.getPageQuery(), page.getTotalElements());
	}
	
	@GetMapping("/{id}")
	public WorkflowDetailsDTO findById(@PathVariable final Long id) {
		return workflowService.mapById(id, workflowMapper::entityToDTO);
	}
	
	@PostMapping
	public WorkflowDetailsDTO create(@Valid @RequestBody final WorkflowDetailsDTO dto) {
		final Workflow entity = workflowMapper.dtoToEntity(dto);
		final Workflow managed = workflowService.save(entity);
		return workflowMapper.entityToDTO(managed);
	}

	@PutMapping
	public WorkflowDetailsDTO update(@Valid @RequestBody final WorkflowDetailsDTO dto) {
		final Workflow entity = workflowMapper.dtoToEntity(dto);
		final Workflow managed = workflowService.save(entity);
		return workflowMapper.entityToDTO(managed);
	} 
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable final Long id) {
		workflowService.deleteById(id);
	}

}
