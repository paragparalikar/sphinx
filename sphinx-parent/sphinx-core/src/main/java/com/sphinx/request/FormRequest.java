package com.sphinx.request;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Immutable;

import com.sphinx.form.Form;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Immutable
@NoArgsConstructor
@DiscriminatorValue("FORM")
@EqualsAndHashCode(callSuper = true)
public class FormRequest extends Request {
	private static final long serialVersionUID = 5235922257125621829L;

	@ManyToOne
	private Form target;
		
}
