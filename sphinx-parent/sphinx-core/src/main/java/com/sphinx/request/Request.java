package com.sphinx.request;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.sphinx.form.Form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private RequestType type = RequestType.ACCESS;
	
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private RequestStatus status = RequestStatus.NEW;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String payload;
	
	@ManyToOne
	private Form form;
	
	@Builder.Default
	@Column(insertable = true, updatable = false)
	private LocalDateTime submitTimestamp = LocalDateTime.now();
	
}
