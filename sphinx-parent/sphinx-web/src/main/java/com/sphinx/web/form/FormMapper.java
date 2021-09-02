package com.sphinx.web.form;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.sphinx.form.Form;

@Mapper(componentModel = "spring")
public interface FormMapper {

	FormDTO entityToThinDTO(Form form);
	
	FormDetailsDTO entityToDTO(Form form);
	
	Form dtoToEntity(FormDetailsDTO dto);
	
	@IterableMapping(elementTargetType = FormDTO.class)
	List<FormDTO> entitiesToDTOs(List<Form> forms);
	
}
