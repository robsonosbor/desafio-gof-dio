package com.example.pagamento.service.testDataBuilder;

import java.util.Optional;

import com.example.pagamento.entity.Transacao;
import com.example.pagamento.model.TransacaoDTO;

public class TransacaoDTODataBuilder {
	
	public Optional<TransacaoDTO> cria(Integer id) {
		
		Transacao transacao = new TransacaoDataBuilder().cria(id).get();
		
		TransacaoDTO dto = new TransacaoDTO();
		
		dto.setTransacao(dto.new Retorno(transacao.getCartao(), transacao.getId(), transacao.getDescricao(), transacao.getFormaPagamento()));
		
		dto.getTransacao().getDescricao().setStatus(null);
		
		return Optional.of(dto);
	}

}
