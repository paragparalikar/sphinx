package com.sphinx.web.workflow;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.sphinx.workflow.model.Workflow;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {
	
	WorkflowDTO entityToThinDTO(Workflow workflow);

	WorkflowDetailsDTO entityToDTO(Workflow workflow);
	
	Workflow dtoToEntity(WorkflowDetailsDTO dto);
	
	@IterableMapping(elementTargetType = WorkflowDTO.class)
	List<WorkflowDTO> entitiesToDTOs(List<Workflow> workflow);
	
}
