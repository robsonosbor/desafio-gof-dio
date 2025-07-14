package com.example.pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pagamento.entity.Transacao;
import com.example.pagamento.enums.StatusPagamentoEnum;
import com.example.pagamento.exception.TransacaoCanceladaException;
import com.example.pagamento.exception.TransacaoNaoEncontradaException;
import com.example.pagamento.model.TransacaoDTO;
import com.example.pagamento.repository.TransacaoRepository;

@Service
public class EstornoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private PagamentoService pagamentoService;

	
	public TransacaoDTO consultaPorIdRetornaDTO(Integer id) {
		
		return TransacaoDTO.converterTransacaoParaDTO(this.consultaPorId(id));		
	}
	
	public Transacao consultaPorId(Integer id) {
		
		return pagamentoService.consultaPorId(id);		
	}
	
	public TransacaoDTO estornarTransacao(Integer id) {
		
		Transacao transacao;
		try {
			transacao = this.consultaPorId(id);
			
		} catch (TransacaoNaoEncontradaException e) {
			throw new TransacaoNaoEncontradaException("Transacao de id: " + id + " não encontrada.");
		}
		
		if (transacao.getDescricao().getStatus() == StatusPagamentoEnum.CANCELADO.toString()) {
			throw new TransacaoCanceladaException("Transacao de id: " + id + " já cancelada.");
		}
		
		transacao.getDescricao().setStatus(StatusPagamentoEnum.CANCELADO.toString());
		
		var entidadeSalva = transacaoRepository.save(transacao);
		
		return TransacaoDTO.converterTransacaoParaDTO(entidadeSalva);
	}

}
