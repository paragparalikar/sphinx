package com.sphinx.web.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ParsingResult {

	private Integer startIndex;
	private Integer pageLength;	
	private String generalFilter;
	private Map<String, List<ColumnFilter>> columnsFilters = new HashMap<String, List<ColumnFilter>>();

	public boolean isGeneralFiltering() {
		if(generalFilter != null && !generalFilter.isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean isColumnsFiltering() {
		if( columnsFilters.size() > 0 ) {
			return true;
		}else {
			return false;
		}
	}	

	public int getPage() {
			return startIndex/pageLength;		
	}
	
}
