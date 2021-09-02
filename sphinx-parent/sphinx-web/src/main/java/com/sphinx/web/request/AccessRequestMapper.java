package com.sphinx.web.request;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.sphinx.request.AccessRequest;

@Mapper(componentModel = "spring")
public interface AccessRequestMapper {
	
	@Mappings({
		@Mapping(target="formId", source="form.id"),
		@Mapping(target="formName", source="form.name")})
	AccessRequestDTO entityToThinDTO(AccessRequest request);

	@InheritInverseConfiguration(name = "entityToThinDTO")
	AccessRequest dtoToEntity(AccessRequestDetailsDTO dto);
	
	@InheritConfiguration(name = "entityToThinDTO")
	AccessRequestDetailsDTO entityToDTO(AccessRequest request);
	
	@IterableMapping(elementTargetType = AccessRequestDTO.class)
	List<AccessRequestDTO> entityToDTOs(List<AccessRequest> requests);
}
