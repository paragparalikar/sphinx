package com.sphinx.web.request;

import java.time.LocalDateTime;

import com.sphinx.request.AccessRequestStatus;
import com.sphinx.web.form.FormDTO;

import lombok.Data;

@Data
public class AccessRequestDTO {

	private Long id;
	private FormDTO form;
	private AccessRequestStatus status;
	private LocalDateTime createTimestamp;
	private LocalDateTime updateTimestamp;
}
