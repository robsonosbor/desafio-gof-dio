package com.example.pagamento.exception;

public class TransacaoNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TransacaoNaoEncontradaException(String erro) {
		super(erro);
	}
}
