package com.example.pagamento.service.testDataBuilder;

import java.util.Optional;

import com.example.pagamento.entity.Transacao;

public class TransacaoDataBuilder {
	
	public Optional<Transacao> cria(Integer id) {
		
		Transacao transacao = new Transacao();
        transacao.setCartao("777******1234");
        transacao.setDescricao(new DescricaoDataBuilder().cria().get());
        transacao.setFormaPagamento(new FormaPagamentoDataBuilder().cria().get());
        transacao.setId(id);
        
		return Optional.of(transacao);
	}
	
public Optional<Transacao> criaEscolhendoStatus(Integer id, String status) {
		
		Transacao transacao = new Transacao();
        transacao.setCartao("777******1234");
        transacao.setDescricao(new DescricaoDataBuilder().criaEscolhendoStatus(status).get());
        transacao.setFormaPagamento(new FormaPagamentoDataBuilder().cria().get());
        transacao.setId(id);
        
		return Optional.of(transacao);
	}
	
}
