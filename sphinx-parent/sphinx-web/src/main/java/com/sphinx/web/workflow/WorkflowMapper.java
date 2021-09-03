package com.sphinx.web.workflow;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import com.sphinx.workflow.Workflow;

@Mapper(componentModel = "spring")
public interface WorkflowMapper {
	
	WorkflowDTO entityToThinDTO(Workflow workflow);

	WorkflowDetailsDTO entityToDTO(Workflow workflow);
	
	
	default Workflow dtoToEntity(WorkflowDetailsDTO dto) {
		final Workflow workflow = new Workflow();
		workflow.setId(dto.getId());
		workflow.setName(dto.getName());
		workflow.setData(dto.getData());
		if(null != workflow.getData()) {
			workflow.getData().values().forEach(node -> {
				if(null != node.getInputs()) {
					node.getInputs().entrySet().forEach(entry -> entry.getValue().setName(entry.getKey()));
				}
				if(null != node.getOutputs()) {
					node.getOutputs().entrySet().forEach(entry -> entry.getValue().setName(entry.getKey()));
				}
			});
		}
		return workflow;
	}
	
	@IterableMapping(elementTargetType = WorkflowDTO.class)
	List<WorkflowDTO> entitiesToDTOs(List<Workflow> workflow);
	
}
