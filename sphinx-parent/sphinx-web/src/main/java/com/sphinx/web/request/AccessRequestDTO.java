package com.sphinx.web.request;

import java.time.LocalDateTime;

import com.sphinx.request.AccessRequestStatus;

import lombok.Data;

@Data
public class AccessRequestDTO {

	private Long id;
	private Long formId;
	private String formName;
	private AccessRequestStatus status;
	private LocalDateTime submitTimestamp;
}
