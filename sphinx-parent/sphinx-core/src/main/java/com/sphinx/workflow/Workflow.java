package com.sphinx.workflow;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.sphinx.common.interfaces.NamedModel;
import com.sphinx.request.RequestType;
import com.sphinx.workflow.node.Node;
import com.sphinx.workflow.node.Output;
import com.sphinx.workflow.node.OutputConnection;
import com.sphinx.workflow.validation.WorkflowConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "data")
public class Workflow implements NamedModel {
	private static final long serialVersionUID = -760095821715213633L;

	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NonNull
	@NotBlank 
	@Size(min = 3, max = 255) 
	@Column(nullable = false, unique = true)
	private String name;
	
	@NonNull
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private RequestType requestType;
	
	@NotEmpty 
	@Builder.Default
	@WorkflowConstraint
	@MapKey(name = "id")
	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Map<@NotNull String, @Valid Node> data = new HashMap<>();
	
	public Node getRequestNode() {
		if(null == data) {
			return null;
		}
		return data.values().stream()
			.filter(node -> "request".equalsIgnoreCase(node.getName()))
			.findFirst()
			.orElse(null);
	}
	
	public Set<Node> getNextNodes(Node from, String outputName){
		if(null == from || null == data) {
			return Collections.emptySet();
		}
		return from.getOutputs().values().stream()
				.filter(output -> !StringUtils.hasText(outputName) 
						|| output.getName().equalsIgnoreCase(outputName))
				.map(Output::getConnections)
				.flatMap(Collection::stream)
				.map(OutputConnection::getNode)
				.map(data::get)
				.collect(Collectors.toSet());
	}
	
}
