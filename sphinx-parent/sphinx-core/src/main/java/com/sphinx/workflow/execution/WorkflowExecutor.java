package com.sphinx.workflow.execution;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.sphinx.request.Request;
import com.sphinx.workflow.Workflow;
import com.sphinx.workflow.node.Node;
import com.sphinx.workflow.task.Task;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionRepository;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;
import com.sphinx.workflow.task.executor.TaskExecutor;
import com.sphinx.workflow.task.executor.TaskExecutorFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WorkflowExecutor {

	private final TaskExecutorFactory taskHandlerFactory;
	private final TaskExecutionRepository taskExecutionRepository;
	
	@Async
	public void execute(Request request) throws Exception {
		final WorkflowExecution workflowExecution = request.getWorkflowExecution();
		final Workflow workflow = workflowExecution.getWorkflow();
		final Node requestNode = workflow.getRequestNode();
		execute(requestNode, request);
	}
	
	private void execute(Node node, Request request) throws Exception {
		final WorkflowExecution workflowExecution = request.getWorkflowExecution();
		final TaskExecution taskExecution = getTaskExecution(node, workflowExecution);
		if(TaskExecutionStatus.NEW.equals(taskExecution.getStatus())) {
			execute(taskExecution, request);
		}
		if(TaskExecutionStatus.COMPLETED.equals(taskExecution.getStatus())) {
			final Workflow workflow = workflowExecution.getWorkflow();
			final Set<Node> nextNodes = workflow.getNextNodes(node, taskExecution.getDecision());
			for(Node nextNode : nextNodes) {
				execute(nextNode, request);
			}
		}
	}
	
	private TaskExecution getTaskExecution(Node node, WorkflowExecution workflowExecution) {
		return Optional.ofNullable(workflowExecution.getTaskExecution(node))
				.orElseGet(() -> {
					final Task task = node.getData();
					final TaskExecution newTaskExecution = new TaskExecution();
					newTaskExecution.setTask(task);
					final TaskExecution managedTaskExecution = taskExecutionRepository.save(newTaskExecution);
					workflowExecution.getTaskExecutions().add(managedTaskExecution);
					return (TaskExecution) managedTaskExecution;
				});
	}
	
	private void execute(TaskExecution taskExecution, Request request) throws Exception {
		final Task task = taskExecution.getTask();
		final TaskExecutor taskExecutor = taskHandlerFactory.getTaskHandler(task.getType());
		final TaskExecutionStatus status = taskExecutor.execute(taskExecution, request);
		taskExecution.setStatus(status);
		taskExecution.setTimestamp(LocalDateTime.now());
		taskExecutionRepository.save(taskExecution);
	}
	
}
