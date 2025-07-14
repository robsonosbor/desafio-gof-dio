package com.example.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pagamento.entity.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer>{
	
}
