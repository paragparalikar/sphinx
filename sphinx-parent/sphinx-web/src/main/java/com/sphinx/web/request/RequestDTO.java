package com.sphinx.web.request;

import com.sphinx.request.RequestStatus;
import com.sphinx.request.RequestType;

import lombok.Data;

@Data
public class RequestDTO {
	private Long id;
	private String name;
	private Long targetId;
	private String targetName;
	private RequestType type;
	private RequestStatus status;
}
