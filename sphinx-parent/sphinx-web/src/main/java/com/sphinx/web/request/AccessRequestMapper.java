package com.sphinx.web.request;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sphinx.request.AccessRequest;

@Mapper(componentModel = "spring")
public interface AccessRequestMapper {

	@Mapping(source = "formId", target = "form.id")
	AccessRequest dtoToEntity(AccessRequestDetailsDTO dto);
	
	@Mappings({
		@Mapping(source = "form.id", target = "formId"),
		@Mapping(source = "form.name", target = "formName")
	})
	AccessRequestDetailsDTO entityToDTO(AccessRequest request);
	
	@InheritConfiguration(name = "entityToDTO")
	List<AccessRequestDTO> entityToDTOs(List<AccessRequest> requests);
}
