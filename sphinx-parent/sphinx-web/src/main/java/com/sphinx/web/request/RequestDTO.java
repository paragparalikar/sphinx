package com.sphinx.web.request;

import java.time.LocalDateTime;

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
	private LocalDateTime createTimestamp;
	private LocalDateTime updateTimestamp;
}
