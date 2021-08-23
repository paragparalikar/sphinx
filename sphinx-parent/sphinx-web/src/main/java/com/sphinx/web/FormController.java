package com.sphinx.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.form.Form;
import com.sphinx.form.FormService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
public class FormController {

	private final FormService formService;

	@GetMapping
	public Page<Form> findAll(){
		return formService.findAll(Pageable.unpaged());
	}
	
}
