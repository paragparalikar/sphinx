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
import javax.persistence.PrePersist;

import com.sphinx.form.Form;

import lombok.Data;

@Data
@Entity
public class AccessRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private AccessRequestStatus status;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String payload;
	
	@ManyToOne
	private Form form;
	
	@Column(insertable = true, updatable = false)
	private LocalDateTime submitTimestamp;
	
	@PrePersist
	public void prePersist() {
		this.status = AccessRequestStatus.NEW;
		this.submitTimestamp = LocalDateTime.now();
	}
	
}
