package com.sphinx.workflow.execution;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Component;

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
	
	public void execute(WorkflowExecution workflowExecution, Object payload) {
		final Workflow workflow = workflowExecution.getWorkflow();
		final Node requestNode = workflow.getRequestNode();
		execute(requestNode, payload, workflowExecution);
	}
	
	private void execute(Node node, Object payload, WorkflowExecution workflowExecution) {
		final TaskExecution taskExecution = getTaskExecution(node, workflowExecution);
		if(TaskExecutionStatus.NEW.equals(taskExecution.getStatus())) {
			execute(taskExecution, payload);
		}
		if(TaskExecutionStatus.COMPLETED.equals(taskExecution.getStatus())) {
			final Set<Node> nextNodes = workflowExecution.getWorkflow().getNextNodes(node, taskExecution.getDecision());
			nextNodes.forEach(nextNode -> execute(nextNode, payload, workflowExecution));
		}
	}
	
	private TaskExecution getTaskExecution(Node node, WorkflowExecution workflowExecution) {
		return Optional.ofNullable(workflowExecution.getTaskExecution(node))
				.orElseGet(() -> {
					final Task task = node.getData();
					final TaskExecution newTaskExecution = new TaskExecution();
					newTaskExecution.setTask(task);
					newTaskExecution.setWorkflowExecution(workflowExecution);
					final TaskExecution managedTaskExecution = taskExecutionRepository.save(newTaskExecution);
					workflowExecution.getTaskExecutions().add(managedTaskExecution);
					return (TaskExecution) managedTaskExecution;
				});
	}
	
	private void execute(TaskExecution taskExecution, Object payload) {
		final Task task = taskExecution.getTask();
		final TaskExecutor taskExecutor = taskHandlerFactory.getTaskHandler(task.getType());
		final TaskExecutionStatus status = taskExecutor.execute(taskExecution, payload);
		taskExecution.setStatus(status);
		taskExecution.setTimestamp(LocalDateTime.now());
		taskExecutionRepository.save(taskExecution);
	}
	
}
