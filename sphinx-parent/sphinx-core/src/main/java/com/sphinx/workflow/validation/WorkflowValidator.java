package com.sphinx.workflow.validation;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sphinx.workflow.model.Node;
import com.sphinx.workflow.model.Output;
import com.sphinx.workflow.model.OutputConnection;

public class WorkflowValidator implements ConstraintValidator<WorkflowConstraint, Map<String, Node>>{
	
	private final Predicate<Node> requestPredicate = node -> node.getName().equalsIgnoreCase("request");

	@Override
	public boolean isValid(Map<String, Node> value, ConstraintValidatorContext context) {
		boolean valid = true;
		
		context.disableDefaultConstraintViolation();
		
		if(!containsRequestNode(value)) {
			valid = false;
			context.buildConstraintViolationWithTemplate("Workflow must contain at least one request node as starting point")
				.addConstraintViolation();
		}
		
		if(!containsTaskNode(value)) {
			valid = false;
			context.buildConstraintViolationWithTemplate("Workflow must contain at least one task to perform")
				.addConstraintViolation();
		}
		
		if(valid && !areAllNodesReachable(value)) {
			valid = false;
			context.buildConstraintViolationWithTemplate("Not all task nodes are reachable from any of starting points")
				.addConstraintViolation();
		}
		
		return valid;
	}
	
	private boolean containsRequestNode(Map<String, Node> value) {
		return value.values().stream().anyMatch(requestPredicate);
	}
	
	private boolean containsTaskNode(Map<String, Node> value) {
		return value.values().stream().anyMatch(requestPredicate.negate());
	}

	private boolean areAllNodesReachable(Map<String, Node> value) {
		final Set<Node> requests = value.values().stream().filter(requestPredicate).collect(Collectors.toSet());
		final Set<Node> tasks = value.values().stream().filter(requestPredicate.negate()).collect(Collectors.toSet());
		for(Node task : tasks) {
			boolean reachable = false;
			for(Node request : requests) {
				if(isReachable(request, task, value)) {
					reachable = true;
					break;
				}
			}
			if(!reachable) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isReachable(Node from, Node to, Map<String, Node> nodes) {
		Node node = from;
		for(Output output : node.getOutputs().values()) {
			for(OutputConnection connection : output.getConnections()) {
				if(connection.getNode().equalsIgnoreCase(to.getId())) {
					return true;
				}
			}
		}
		return from.getOutputs().values().stream()
				.map(Output::getConnections)
				.flatMap(Collection::stream)
				.map(OutputConnection::getNode)
				.map(nodes::get)
				.anyMatch(n -> isReachable(n, to, nodes));
	}
}
