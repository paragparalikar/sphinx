package com.sphinx.web.request;

import java.util.List;

import org.mapstruct.Mapper;

import com.sphinx.request.AccessRequest;

@Mapper(componentModel = "spring")
public interface AccessRequestMapper {

	AccessRequest dtoToEntity(AccessRequestDetailsDTO dto);
	
	AccessRequestDetailsDTO entityToDTO(AccessRequest request);
	
	List<AccessRequestDTO> entityToDTOs(List<AccessRequest> requests);
}
