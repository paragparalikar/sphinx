package com.sphinx.web.search;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import lombok.Data;

@Data
public class PrimengQueries<T> {

	private Pageable pageQuery;
	private String rsqlQuery = null;
	private String sortQuery = null;
	private Specification<T> spec;
	
}
