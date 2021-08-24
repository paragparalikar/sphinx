package com.sphinx.form;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormService {

	private final FormRepository formRepository;
	
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
