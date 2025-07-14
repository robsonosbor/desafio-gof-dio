package com.example.pagamento.model;

import com.example.pagamento.entity.Descricao;
import com.example.pagamento.entity.FormaPagamento;
import com.example.pagamento.entity.Transacao;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransacaoDTO {

	@JsonProperty("transacao")
	private Retorno transacao;
	
	public class Retorno {
		private String cartao;
		private int id;
		private Descricao descricao;
		private FormaPagamento formaPagamento;
		
		public Retorno() {
			super();
		}
		public Retorno(String cartao, int id, Descricao descricao, FormaPagamento formaPagamento) {
			super();
			this.cartao = cartao;
			this.id = id;
			this.descricao = descricao;
			this.formaPagamento = formaPagamento;
		}
		public String getCartao() {
			return cartao;
		}
		public void setCartao(String cartao) {
			this.cartao = cartao;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Descricao getDescricao() {
			return descricao;
		}
		public void setDescricao(Descricao descricao) {
			this.descricao = descricao;
		}
		public FormaPagamento getFormaPagamento() {
			return formaPagamento;
		}
		public void setFormaPagamento(FormaPagamento formaPagamento) {
			this.formaPagamento = formaPagamento;
		}	
		
	}
	
	public static TransacaoDTO converterTransacaoParaDTO( Transacao transacao ) {
		
		TransacaoDTO dto = new TransacaoDTO();
		
		dto.transacao = dto.new Retorno(transacao.getCartao(), transacao.getId(), transacao.getDescricao(), transacao.getFormaPagamento());
		
		return dto;
	}
	
	public static Transacao converterTransacaoDTOParaEntidade( TransacaoDTO dto ) {
		
		Transacao transacao = new Transacao();
		
		transacao.setCartao(dto.getTransacao().getCartao());
		transacao.setDescricao(dto.getTransacao().getDescricao());
		transacao.setFormaPagamento(dto.getTransacao().getFormaPagamento());
		
		return transacao;
	}

	public TransacaoDTO() {
		super();
	}

	public TransacaoDTO(Retorno transacao) {
		super();
		this.transacao = transacao;
	}

	public Retorno getTransacao() {
		return transacao;
	}

	public void setTransacao(Retorno transacao) {
		this.transacao = transacao;
	}
	
	
}
