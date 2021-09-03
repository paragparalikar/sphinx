package com.sphinx.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor    
@AllArgsConstructor
public class ErrorMessage{
	
	private String text;
    private String fieldName;
    private Object rejectedValue;
    
}
