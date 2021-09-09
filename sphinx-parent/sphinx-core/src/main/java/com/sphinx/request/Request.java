package com.sphinx.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Immutable;

import com.sphinx.common.NamedModel;
import com.sphinx.workflow.execution.WorkflowExecution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Immutable
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Request implements Serializable {
	private static final long serialVersionUID = 6319659442843630209L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@NonNull
	@OneToOne(cascade = CascadeType.ALL, optional = false) 
	private WorkflowExecution workflowExecution;
	
	@NonNull
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, insertable = false, updatable = false)
	private RequestType type = RequestType.ACCESS;
	
	@NonNull
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestStatus status = RequestStatus.NEW;
	
	@Lob 
	@Basic(fetch=FetchType.LAZY)
	private String payload;
	
	@Column(nullable = false, insertable = true, updatable = false)
	private LocalDateTime createTimestamp = LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime updateTimestamp = LocalDateTime.now();
	
	@Column(nullable = false)
	private LocalDateTime lockTimestamp = LocalDateTime.MIN;
	
	public abstract NamedModel getTarget();
	
}
