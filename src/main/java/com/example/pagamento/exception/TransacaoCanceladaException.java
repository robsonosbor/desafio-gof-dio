package com.example.pagamento.exception;

public class TransacaoCanceladaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransacaoCanceladaException(String erro) {
		super(erro);
	}
}