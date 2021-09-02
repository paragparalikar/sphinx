package com.sphinx.web.workflow;

import java.util.List;

import org.mapstruct.Mapper;

import com.sphinx.workflow.model.Workflow;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {

	WorkflowDetailsDTO entityToDTO(Workflow workflow);
	
	Workflow dtoToEntity(WorkflowDetailsDTO dto);
	
	List<WorkflowDTO> entitiesToDTOs(List<Workflow> workflow);
	
}
