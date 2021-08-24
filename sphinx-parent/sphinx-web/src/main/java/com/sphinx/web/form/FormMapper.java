package com.sphinx.web.form;

import java.util.List;

import org.mapstruct.Mapper;

import com.sphinx.form.Form;

@Mapper(componentModel = "spring")
public interface FormMapper {

	FormDetailsDTO entityToDTO(Form form);
	
	Form dtoToEntity(FormDetailsDTO dto);
	
	List<FormDTO> entitiesToDTOs(List<Form> forms);
	
}
