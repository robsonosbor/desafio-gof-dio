package com.example.pagamento.validador;

import java.util.Random;

public class SimuladorAutorizacaoPagamento {
	
	private static SimuladorAutorizacaoPagamento instancia = null;
	private static int contador = 0;
	private static Random random = new Random();
	
    private SimuladorAutorizacaoPagamento() {
    }
    
    //Design Pattern (Gang of Four): Singleton
    public static SimuladorAutorizacaoPagamento getInstancia() {
        if (instancia == null) {
            instancia = new SimuladorAutorizacaoPagamento();
        }
        return instancia;
    }
    
    public static Integer integerAleatorio() {
		
		return 100000 + random.nextInt(900000);
	}
	
	public static Boolean simulaAutorizacao() {

		Boolean aleatorio = random.nextBoolean();
		
		if (aleatorio == false) {
			SimuladorAutorizacaoPagamento.contador++;			
		}
		
		if (SimuladorAutorizacaoPagamento.contador > 1) {
			aleatorio = true;
		}
		
		return aleatorio;
	}
}
