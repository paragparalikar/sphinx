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
		final List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
				.map(err -> new ErrorModel(err.getField(), err.getRejectedValue(), err.getDefaultMessage())).distinct()
				.collect(Collectors.toList());
		return ErrorResponse.builder().errorMessage(errorMessages).build();
	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErrorResponse handleUnprosseasableMsgException(HttpMessageNotReadableException msgNotReadable) {
		return ErrorResponse.builder().message("UNPROCESSABLE INPUT DATA")
				.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).build();
	}

}
