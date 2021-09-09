package com.sphinx.workflow;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>, JpaSpecificationExecutor<Workflow> {

	Boolean existsByNameIgnoreCase(String name);
	
	Workflow findOneByNameIgnoreCase(String name);
	
	Boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	
	List<Workflow> findByNameContainingIgnoreCase(String query, Pageable pageable);
	
}
