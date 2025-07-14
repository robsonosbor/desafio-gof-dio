package com.example.pagamento.exception;

public class TransacaoAutorizadaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransacaoAutorizadaException(String erro) {
		super(erro);
	}
}