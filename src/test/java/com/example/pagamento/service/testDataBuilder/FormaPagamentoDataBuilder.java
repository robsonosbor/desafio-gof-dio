package com.example.pagamento.service.testDataBuilder;

import java.util.Optional;

import com.example.pagamento.entity.FormaPagamento;

public class FormaPagamentoDataBuilder {
	
	public Optional<FormaPagamento> cria() {
		
		FormaPagamento formaPagamento = new FormaPagamento();
	
		formaPagamento.setId(1);
        formaPagamento.setParcelas("1");
        formaPagamento.setTipo("AVISTA");
		
		return Optional.of(formaPagamento);
	}

}
