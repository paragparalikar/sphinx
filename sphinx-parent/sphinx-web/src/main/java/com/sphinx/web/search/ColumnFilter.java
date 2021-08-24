package com.sphinx.web.search;

import lombok.Data;

@Data
public class ColumnFilter {

	private String valueToSearch;
	private String matchMode= "default";
	private String operator = null;
	private ColumnType type;
	
}
