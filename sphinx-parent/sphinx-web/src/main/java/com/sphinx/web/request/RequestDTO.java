package com.sphinx.web.request;

import java.time.LocalDateTime;

import com.sphinx.request.RequestStatus;
import com.sphinx.request.RequestType;

import lombok.Data;

@Data
public class RequestDTO {

	private Long id;
	private Long formId;
	private String formName;
	private RequestType type;
	private RequestStatus status;
	private LocalDateTime submitTimestamp;
}
