package com.example.pagamento.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.pagamento.exception.TransacaoAutorizadaException;
import com.example.pagamento.exception.TransacaoCanceladaException;
import com.example.pagamento.exception.TransacaoNaoEncontradaException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(TransacaoNaoEncontradaException.class)
	protected ResponseEntity<String> handleTransacaoNaoEncontradaException( TransacaoNaoEncontradaException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}

    @ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<String> handleErroGenericoException( RuntimeException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
	}
    
    @ExceptionHandler(TransacaoCanceladaException.class)
	protected ResponseEntity<String> handleTransacaoCanceladaException( TransacaoCanceladaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}    
    
    @ExceptionHandler(TransacaoAutorizadaException.class)
	protected ResponseEntity<String> handleTransacaoAutorizadaException( TransacaoAutorizadaException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
    
}
