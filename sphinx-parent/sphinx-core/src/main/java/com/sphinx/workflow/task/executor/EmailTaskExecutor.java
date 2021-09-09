package com.sphinx.workflow.task.executor;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.sphinx.request.Request;
import com.sphinx.request.resolver.RecipientResolver;
import com.sphinx.user.User;
import com.sphinx.workflow.task.EmailTask;
import com.sphinx.workflow.task.execution.TaskExecution;
import com.sphinx.workflow.task.execution.TaskExecutionStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailTaskExecutor implements TaskExecutor {
	
	private final JavaMailSender javaMailSender;
	private final RecipientResolver recipientResolver;

	@Override
	public TaskExecutionStatus execute(TaskExecution taskExecution, Request request) throws MessagingException {
		final EmailTask emailTask = EmailTask.class.cast(taskExecution.getTask());
		final MimeMessage message = javaMailSender.createMimeMessage();
		final User user = recipientResolver.resolve(request, emailTask.getTo(), emailTask.getApplicationId());
		message.setRecipients(RecipientType.TO, user.getEmail());
		message.setSubject(emailTask.getSubjectTemplate()); // TODO use a templating engine
		message.setText(emailTask.getContentTemplate()); // TODO use a templating engine
		javaMailSender.send(message);
		return TaskExecutionStatus.COMPLETED;
	}

}
