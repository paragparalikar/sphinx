package com.sphinx.form;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormService {

	private final FormRepository formRepository;
	
	public List<Form> findSuggestions(@NonNull String query,@NonNull Pageable pageable){
		return formRepository.findByNameContainingIgnoreCase(query, pageable);
	}
	
	public Page<Form> findAll(Specification<Form> spec, Pageable pageable){
		return formRepository.findAll(spec, pageable);
	}
	
	public Form save(Form form) {
		return formRepository.save(form);
	}
	
	public Form findById(Long id) {
		return formRepository.findById(id).orElse(null);
	}
	
	public void deleteById(Long id) {
		formRepository.deleteById(id);
	}
	
}
