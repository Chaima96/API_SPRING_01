package com.springofanhella.recurso.excecao;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springofanhella.excecao.NaoEncontradoException;

@ControllerAdvice
public class ExcecaoRecursoHandler {

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ApiError> HandlerNotFoundException(NaoEncontradoException ex) {
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
