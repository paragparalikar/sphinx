package com.sphinx.web.request;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sphinx.request.Request;

@Mapper(componentModel = "spring")
public interface RequestMapper {
	
	@Mappings({
		@Mapping(target="formId", source="form.id"),
		@Mapping(target="formName", source="form.name")})
	RequestDTO entityToThinDTO(Request request);

	@InheritInverseConfiguration(name = "entityToThinDTO")
	Request dtoToEntity(RequestDetailsDTO dto);
	
	@InheritConfiguration(name = "entityToThinDTO")
	RequestDetailsDTO entityToDTO(Request request);
	
	@IterableMapping(elementTargetType = RequestDTO.class)
	List<RequestDTO> entityToDTOs(List<Request> requests);
}
