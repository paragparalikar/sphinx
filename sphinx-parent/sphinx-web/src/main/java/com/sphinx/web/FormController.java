package com.sphinx.web;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sphinx.form.Form;
import com.sphinx.form.FormService;
import com.sphinx.web.search.PrimengQueries;
import com.sphinx.web.search.SearchBuilder;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/forms")
@CrossOrigin(origins = "*")
public class FormController {

	private final FormService formService;
	private final SearchBuilder searchBuilder;

	@PostMapping("/pages")
	public Page<Form> findAll(@RequestBody String pageRequest){
		final PrimengQueries<Form> primengQueries = searchBuilder.process(pageRequest, Form.class, "id", "name");
		return formService.findAll(primengQueries.getSpec(), primengQueries.getPageQuery());
	}
	
	@GetMapping("/{id}")
	public Form findById(@PathVariable final Long id) {
		return formService.findById(id);
	}
	
	@PostMapping
	public Form create(@RequestBody final Form form) {
		return formService.save(form);
	}
	
	@PutMapping
	public Form update(@RequestBody final Form form) {
		return formService.save(form);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable final Long id) {
		formService.deleteById(id);
	}
	
}
