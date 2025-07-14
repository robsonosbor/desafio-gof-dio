package com.example.pagamento.service.testDataBuilder;

import java.util.Optional;

import com.example.pagamento.entity.Descricao;

public class DescricaoDataBuilder {
	
	public Optional<Descricao> cria() {
		
		Descricao descricao = new Descricao();
	
		descricao.setCodigoAutorizacao("748450");
        descricao.setDataHora("01/05/2021 18:30:0");
        descricao.setEstabelecimento("Supermercado");
        descricao.setId(1);
        descricao.setNsu("150362");
        descricao.setStatus("AUTORIZADO");
        descricao.setValor("50");
		
		return Optional.of(descricao);
	}
	
public Optional<Descricao> criaEscolhendoStatus(String status) {
		
		Descricao descricao = new Descricao();
	
		descricao.setCodigoAutorizacao("748450");
        descricao.setDataHora("01/05/2021 18:30:0");
        descricao.setEstabelecimento("Supermercado");
        descricao.setId(1);
        descricao.setNsu("150362");
        descricao.setStatus(status);
        descricao.setValor("50");
		
		return Optional.of(descricao);
	}

}
