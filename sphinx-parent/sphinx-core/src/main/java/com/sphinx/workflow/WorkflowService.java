package com.sphinx.workflow;

import java.util.List;
import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.sphinx.workflow.model.Workflow;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkflowService {

	private final WorkflowRepository workflowRepository;
	
	public List<Workflow> findSuggestions(@NonNull String query,@NonNull Pageable pageable){
		return workflowRepository.findByNameContainingIgnoreCase(query, pageable);
	}
	
	public Page<Workflow> findAll(Specification<Workflow> spec, Pageable pageable){
		return workflowRepository.findAll(spec, pageable);
	}
	
	public Workflow save(Workflow workflow) {
		return workflowRepository.save(workflow);
	}
	
	public Workflow findById(Long id) {
		return workflowRepository.findById(id).orElse(null);
	}
	
	@Transactional
	public <T> T mapById(@NonNull Long id, Function<Workflow, T> mapper) {
		return mapper.apply(findById(id));
	}
	
	public void deleteById(Long id) {
		workflowRepository.deleteById(id);
	}
}
