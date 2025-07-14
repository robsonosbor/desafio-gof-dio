package com.example.pagamento.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.pagamento.entity.Transacao;
import com.example.pagamento.enums.StatusPagamentoEnum;
import com.example.pagamento.exception.TransacaoCanceladaException;
import com.example.pagamento.exception.TransacaoNaoEncontradaException;
import com.example.pagamento.model.TransacaoDTO;
import com.example.pagamento.repository.TransacaoRepository;
import com.example.pagamento.service.testDataBuilder.TransacaoDataBuilder;

@ContextConfiguration(classes = {EstornoService.class})
@ExtendWith(SpringExtension.class)
class EstornoServiceTest {
	
    @Autowired
    private EstornoService estornoService;

    @MockBean
    private PagamentoService pagamentoService;

    @MockBean
    private TransacaoRepository transacaoRepository;
    
    private static final Integer ID = 1;

    /**
     * consultaPorIdRetornaDTO(Integer)
     */
    @Test
    void testConsultaPorIdRetornaDTO() {
    	
    	Transacao resultadoEsperado = new TransacaoDataBuilder().cria(ID).get();
    	
        when(this.pagamentoService.consultaPorId((Integer) any())).thenReturn(resultadoEsperado);
        
        TransacaoDTO.Retorno resultadoUnderTest = this.estornoService.consultaPorIdRetornaDTO(ID).getTransacao();
        
        assertSame(resultadoEsperado.getFormaPagamento(), resultadoUnderTest.getFormaPagamento());
        assertSame(resultadoEsperado.getDescricao(), resultadoUnderTest.getDescricao());
        verify(this.pagamentoService).consultaPorId((Integer) any());
    }

    /**
     * consultaPorIdRetornaDTO(Integer)
     */
    @Test
    void testSeExcecaoLancadaTransacaoNaoEncontradaException() {
        when(this.pagamentoService.consultaPorId((Integer) any())).thenThrow(new TransacaoNaoEncontradaException("Erro"));
        assertThrows(TransacaoNaoEncontradaException.class, () -> this.estornoService.consultaPorIdRetornaDTO(ID));
        verify(this.pagamentoService).consultaPorId((Integer) any());
    }

    /**
     * estornarTransacao(Integer)}
     */
    @Test
    void testEstornarTransacao() {
        
        Transacao transacao = new TransacaoDataBuilder().cria(ID).get();

        when(this.transacaoRepository.save((Transacao) any())).thenReturn(transacao);

        when(this.pagamentoService.consultaPorId((Integer) any())).thenReturn(transacao);

        TransacaoDTO.Retorno resultadoUnderTest = this.estornoService.estornarTransacao(ID).getTransacao();
        
        assertEquals(StatusPagamentoEnum.CANCELADO.toString(), resultadoUnderTest.getDescricao().getStatus());
        verify(this.transacaoRepository).save((Transacao) any());
        verify(this.pagamentoService).consultaPorId((Integer) any());
    }

    /**
     * estornarTransacao(Integer)
     */
    @Test
    void testSeExcecaoLancadaTransacaoCanceladaException() {

    	Transacao transacao = new TransacaoDataBuilder()
    			.criaEscolhendoStatus(ID, StatusPagamentoEnum.CANCELADO.toString()).get();
    	
    	when(this.pagamentoService.consultaPorId((Integer) any())).thenReturn(transacao);

    	assertThrows(TransacaoCanceladaException.class, 
        		() -> this.estornoService.estornarTransacao(ID));
        verify(this.pagamentoService).consultaPorId((Integer) any());
    }
}

