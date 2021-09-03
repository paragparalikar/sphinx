package com.sphinx.web.form;

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

	private final FormMapper formMapper;
	private final FormService formService;
	private final SearchBuilder searchBuilder;
	
	@GetMapping(value = "/{id}", params = "name")
	public Boolean exists(@PathVariable("id") final Long id, @RequestParam("name") final String name) {
		return formService.existsByName(id, name);
	}
	
	@GetMapping(params = "q")
	public List<FormDTO> findSuggestions(@RequestParam("q") final String query){
		final Pageable pageable = PageRequest.of(0, 10, Sort.by("name"));
		final List<Form> forms = formService.findSuggestions(query, pageable);
		return formMapper.entitiesToDTOs(forms);
	}

	@PostMapping("/pages")
	public Page<FormDTO> findAll(@RequestBody String pageRequest){
		final PrimengQueries<Form> primengQueries = searchBuilder.process(pageRequest, Form.class, "id", "name");
		final Page<Form> page = formService.findAll(primengQueries.getSpec(), primengQueries.getPageQuery());
		final List<FormDTO> dtos = formMapper.entitiesToDTOs(page.getContent());
		return new PageImpl<>(dtos, primengQueries.getPageQuery(), page.getTotalElements());
	}
	
	@GetMapping("/{id}")
	public FormDetailsDTO findById(@PathVariable final Long id) {
		final Form form = formService.findById(id);
		return null == form ? null : formMapper.entityToDTO(form);
	}
	
	@PostMapping
	public FormDetailsDTO create(@Valid @RequestBody final FormDetailsDTO dto) {
		final Form entity = formMapper.dtoToEntity(dto);
		final Form managed = formService.save(entity);
		return formMapper.entityToDTO(managed);
	}

	@PutMapping
	public FormDetailsDTO update(@Valid @RequestBody final FormDetailsDTO dto) {
		final Form entity = formMapper.dtoToEntity(dto);
		final Form managed = formService.save(entity);
		return formMapper.entityToDTO(managed);
	} 
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable final Long id) {
		formService.deleteById(id);
	}
	
}
