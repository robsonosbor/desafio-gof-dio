package com.example.pagamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pagamento.model.TransacaoDTO;
import com.example.pagamento.service.PagamentoService;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoController {
	
	@Autowired
	private PagamentoService pagamentoService; 

	@GetMapping("/consultaTodos")
	public ResponseEntity<List<TransacaoDTO>> consultaTodos()
	{		
		return ResponseEntity.ok(pagamentoService.consultaTodos());
	}
	
	@GetMapping("/consultaPorId")
	public ResponseEntity<TransacaoDTO> consultaPorId(Integer id) 
	{		
		return ResponseEntity.ok(pagamentoService.consultaPorIdRetornaDTO(id));
	}
	
	@PostMapping("/solicitaPagamento")
	public ResponseEntity<TransacaoDTO> solicitaPagamento(@RequestBody TransacaoDTO dto) 
	{		
		return ResponseEntity.ok(pagamentoService.solicitaPagamento(dto));
	}

}
