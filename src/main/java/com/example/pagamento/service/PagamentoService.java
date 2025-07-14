package com.example.pagamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pagamento.entity.Transacao;
import com.example.pagamento.enums.StatusPagamentoEnum;
import com.example.pagamento.exception.TransacaoAutorizadaException;
import com.example.pagamento.exception.TransacaoNaoEncontradaException;
import com.example.pagamento.model.TransacaoDTO;
import com.example.pagamento.repository.TransacaoRepository;
import com.example.pagamento.validador.SimuladorAutorizacaoPagamento;

@Service
public class PagamentoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;

	public List<TransacaoDTO> consultaTodos() {

		var lista = transacaoRepository.findAll();
		
		if (lista.isEmpty()) {
			throw new TransacaoNaoEncontradaException("Não foram encontradas transações.");
		}
		
		List<TransacaoDTO> listaDTO = new ArrayList<TransacaoDTO>();
		
		for (Transacao entidade : lista) {
			listaDTO.add(TransacaoDTO.converterTransacaoParaDTO(entidade));			
		}
		return listaDTO;
		
	}
	
	public TransacaoDTO consultaPorIdRetornaDTO(Integer id) {
		
		return TransacaoDTO.converterTransacaoParaDTO(this.consultaPorId(id));
	}

	public Transacao consultaPorId(Integer id) {
		
			Optional<Transacao> transacao = transacaoRepository.findById(id);
			
			if (transacao.isEmpty()) {
				throw new TransacaoNaoEncontradaException("Transacao de id: " + id + " não encontrada.");
			}
			return transacao.get();

	}

	public TransacaoDTO solicitaPagamento(TransacaoDTO dto) {
		
		try {
			
			validaSeJaAutorizada(dto);
			
			simuladorAutorizacaoPagamento(dto);
			
			Transacao transacao = TransacaoDTO.converterTransacaoDTOParaEntidade(dto);
			
			var entidadeSalva = transacaoRepository.save(transacao);
			
			return TransacaoDTO.converterTransacaoParaDTO(entidadeSalva);
			
		} catch (TransacaoAutorizadaException e) {
        	throw new TransacaoAutorizadaException(e.getMessage());
        	
		} catch (Exception e) {
			e.printStackTrace();
        	throw new RuntimeException("Erro interno.");
		}
	}

	private void validaSeJaAutorizada(TransacaoDTO dto) {
		
		if (dto.getTransacao().getDescricao() != null &&
			dto.getTransacao().getDescricao().getStatus() != null &&
			dto.getTransacao().getDescricao().getStatus().toString()
			.compareToIgnoreCase(StatusPagamentoEnum.AUTORIZADO.toString()) == 0) {
			
			throw new TransacaoAutorizadaException("Transacao já autorizada.");
		}
	}

	private void simuladorAutorizacaoPagamento(TransacaoDTO dto) {
		
		SimuladorAutorizacaoPagamento.getInstancia();
		Boolean seAutorizado = SimuladorAutorizacaoPagamento.simulaAutorizacao();		
		
		buscaDescricaoPagamento(dto, seAutorizado);
		
	}

	private void buscaDescricaoPagamento(TransacaoDTO dto, Boolean seAutorizado) {
		
		if (seAutorizado) {
			
			buscaDescricaoAutorizado(dto);
			
		} else {

			buscaDescricaoNegado(dto);
		}
	}

	private void buscaDescricaoAutorizado(TransacaoDTO dto) {
		String codigoAutorizacao = SimuladorAutorizacaoPagamento.integerAleatorio().toString();
		dto.getTransacao().getDescricao().setCodigoAutorizacao(codigoAutorizacao);
		dto.getTransacao().getDescricao().setStatus(StatusPagamentoEnum.AUTORIZADO.toString());
		String nsu = SimuladorAutorizacaoPagamento.integerAleatorio().toString();
		dto.getTransacao().getDescricao().setNsu(nsu);
	}
	
	private void buscaDescricaoNegado(TransacaoDTO dto) {
		String nsu = SimuladorAutorizacaoPagamento.integerAleatorio().toString();
		dto.getTransacao().getDescricao().setNsu(nsu);
		dto.getTransacao().getDescricao().setStatus(StatusPagamentoEnum.NEGADO.toString());
	}
}
