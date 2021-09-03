package com.sphinx.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ErrorResponse handleException(MethodArgumentNotValidException exception) {
		final List<ErrorMessage> errorMessages = exception.getBindingResult().getFieldErrors().stream()
				.map(error -> ErrorMessage.builder()
						.fieldName(error.getField())
						.text(error.getDefaultMessage())
						.rejectedValue(error.getRejectedValue())
						.build())
				.distinct()
				.collect(Collectors.toList());
		return ErrorResponse.builder()
				.messages(errorMessages)
				.status(HttpStatus.BAD_REQUEST.value())
				.build();
	}

	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ErrorResponse handleUnprosseasableMsgException(HttpMessageNotReadableException msgNotReadable) {
		return ErrorResponse.builder()
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value())
				.message(ErrorMessage.builder()
						.text("Received unprocessable data, please contact your system administrator")
						.build())
				.build();
	}

}
