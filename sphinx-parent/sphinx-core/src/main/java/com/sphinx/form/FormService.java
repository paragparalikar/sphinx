package com.sphinx.form;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormService {

	private final FormRepository formRepository;
	
	public Page<Form> findAll(Pageable pageable){
		return formRepository.findAll(pageable);
	}
	
	
}
