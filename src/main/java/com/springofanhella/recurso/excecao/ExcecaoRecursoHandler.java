package com.springofanhella.recurso.excecao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springofanhella.excecao.NaoEncontradoException;

@ControllerAdvice
public class ExcecaoRecursoHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ApiError> HandlerNotFoundException(NaoEncontradoException ex) {
		
		ApiError error = new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), new Date());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		  ex.getBindingResult().getAllErrors().forEach(error -> {
			  
			  errors.add(error.getDefaultMessage());
		  });
		  
		  String defaultMessag = "Invalid Fild(s)";
		ApiErrorList error = new ApiErrorList(HttpStatus.BAD_REQUEST.value(), defaultMessag, new Date(), errors);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
