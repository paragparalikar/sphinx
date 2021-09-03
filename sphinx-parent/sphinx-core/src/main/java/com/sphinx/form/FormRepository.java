package com.sphinx.form;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long>, JpaSpecificationExecutor<Form> {
	
	List<Form> findByNameContainingIgnoreCase(String query, Pageable pageable);
	
	Boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
	
}
