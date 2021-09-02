package com.sphinx.workflow;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.sphinx.workflow.model.Workflow;

@Repository
public interface WorkflowRepository extends JpaRepository<Workflow, Long>, JpaSpecificationExecutor<Workflow> {

	List<Workflow> findByNameContainingIgnoreCase(String query, Pageable pageable);
	
}
