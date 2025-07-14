package com.example.pagamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pagamento.entity.Transacao;
import com.example.pagamento.enums.StatusPagamentoEnum;
import com.example.pagamento.exception.TransacaoAutorizadaException;
import com.example.pagamento.exception.TransacaoNaoEncontradaException;
import com.example.pagamento.model.TransacaoDTO;
import com.example.pagamento.repository.TransacaoRepository;
import com.example.pagamento.service.testDataBuilder.TransacaoDTODataBuilder;
import com.example.pagamento.service.testDataBuilder.TransacaoDataBuilder;

@ContextConfiguration(classes = {PagamentoService.class})
@ExtendWith(SpringExtension.class)
class PagamentoServiceTest {
	
    @Autowired
    private PagamentoService pagamentoService;

    @MockBean
    private TransacaoRepository transacaoRepository;

    private static final Integer ID = 1;
    
    /**
     * consultaTodos()
     */
    @Test
    void testConsultaTodosSeExcecaoLancada() {
        
    	when(this.transacaoRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(TransacaoNaoEncontradaException.class, () -> this.pagamentoService.consultaTodos());
        verify(this.transacaoRepository).findAll();
    }

    /**
     * consultaTodos()
     */
    @Test
    void testConsultaTodos() {
    	
    	Transacao transacao = new TransacaoDataBuilder().cria(ID).get();

        ArrayList<Transacao> transacaoList = new ArrayList<>();
        transacaoList.add(transacao);
        
        when(this.transacaoRepository.findAll()).thenReturn(transacaoList);
        
        List<TransacaoDTO> actualConsultaTodosResult = this.pagamentoService.consultaTodos();
        
        assertEquals(1, actualConsultaTodosResult.size());
        verify(this.transacaoRepository).findAll();
    }

    /**
     * consultaPorIdRetornaDTO(Integer)
     */
    @Test
    void testSeExcecaoLancadaTransacaoNaoEncontradaException() {
        when(this.transacaoRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(TransacaoNaoEncontradaException.class, () -> this.pagamentoService.consultaPorIdRetornaDTO(ID));
        verify(this.transacaoRepository).findById((Integer) any());
    }

    /**
     * consultaPorId(Integer)
     */
    @Test
    void testConsultaPorId() {
        
    	Transacao resultadoEsperado = new TransacaoDataBuilder().cria(ID).get();
    	
        Optional<Transacao> optionalDe = Optional.of(resultadoEsperado);
        when(this.transacaoRepository.findById((Integer) any())).thenReturn(optionalDe);
        assertSame(resultadoEsperado, this.pagamentoService.consultaPorId(ID));
        verify(this.transacaoRepository).findById((Integer) any());
    }

    /**
     * consultaPorId(Integer)
     */
    @Test
    void testConsultaPorIdSeExcecaoLancadaTransacaoNaoEncontradaException() {
        when(this.transacaoRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(TransacaoNaoEncontradaException.class, () -> this.pagamentoService.consultaPorId(ID));
        verify(this.transacaoRepository).findById((Integer) any());
    }

    /**
     * solicitaPagamento(TransacaoDTO)
     */
    @Test
    void testSolicitaPagamento() {
    	
    	Transacao resultadoEsperado = new TransacaoDataBuilder().cria(ID).get();
        when(this.transacaoRepository.save((Transacao) any())).thenReturn(resultadoEsperado);
        
        TransacaoDTO transacaoDTO = new TransacaoDTODataBuilder().cria(ID).get();
        
        TransacaoDTO.Retorno resultadoUnderTest = this.pagamentoService.solicitaPagamento(transacaoDTO).getTransacao();
        
        assertEquals(resultadoEsperado.getCartao(), resultadoUnderTest.getCartao());
        verify(this.transacaoRepository).save((Transacao) any());
    }

    /**
     * solicitaPagamento(TransacaoDTO)
     */
    @Test
    void testSolicitaPagamentoSeExcecaoLancadaTransacaoAutorizadaException() {
    	
        TransacaoDTO transacaoDTO = new TransacaoDTODataBuilder().cria(ID).get();
        transacaoDTO.getTransacao().getDescricao().setStatus(StatusPagamentoEnum.AUTORIZADO.toString());
        
        assertThrows(TransacaoAutorizadaException.class, () -> this.pagamentoService.solicitaPagamento(transacaoDTO));
    }
    
    /**
     * solicitaPagamento(TransacaoDTO)
     */
    @Test
    void testSolicitaPagamentoSeExcecaoLancadaRuntimeException1() {
    	assertThrows(RuntimeException.class, () -> this.pagamentoService.solicitaPagamento(new TransacaoDTO()));
    }
    
    @Test
    void testSolicitaPagamentoSeExcecaoLancadaRuntimeException2() {
    	assertThrows(RuntimeException.class, () -> this.pagamentoService.solicitaPagamento(null));
    }
}

