package com.sphinx.workflow.execution;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.node.Node;
import com.sphinx.workflow.task.Task;
import com.sphinx.workflow.task.execution.TaskExecutor;
import com.sphinx.workflow.task.execution.TaskExecutorFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkflowExecutor {

	private final TaskExecutorFactory taskHandlerFactory;
	
	public void handle(Workflow workflow, Object payload) {
		final Map<String, Node> nodes = workflow.getData();
		final Node requestNode = nodes.values().stream()
				.filter(node -> "request".equalsIgnoreCase(node.getName()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Request node not found"));
		
	}
	
	private void handle(Node node, Object payload) {
		final Task task = node.getData();
		final TaskExecutor<Task> taskHandler = taskHandlerFactory.getTaskHandlerFor(task);
		taskHandler.execute(task, payload);
		
	}
	
}
