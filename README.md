# ToolsChallenge

Projeto desenvolvido para o Processo Seletivo Tools.

Utilizei SpringBoot e o banco de dados H2 (em memória - ver /src/main/resources).

Requisitos implementados, inclusive com o uso do padrão de projeto Singleton ("Gang of Four").

Aqui, cabe uma explicação: ao desenvolver a operação Pagamento, quis fazer uma forma de autorização, que, mesmo de uma forma simples, ajude a aproximar o projeto da vida real.

A forma de autorização encontrada foi gerar um boolean randômico - retornou true, autoriza o pagamento; false, não autoriza.

Porém, isso poderia atrapalhar o teste pelo postman, podendo gerar vários false em sequencia.

A solução foi implementar um contador pra limitar o false a uma ocorrência. Esse contador é controlado em uma classe Singleton (/src/main/java/com/example/pagamento/validador/SimuladorAutorizacaoPagamento.java).

Endpoints para teste pelo Postman:

1)

GET localhost:8080/api/pagamento/consultaTodos

2)

GET localhost:8080/api/pagamento/consultaPorId?id=100023901

3)

POST localhost:8080/api/pagamento/solicitaPagamento

a)

{
	"transacao": {
		"cartao": "777******1234",
		"descricao": {
			"valor": "50",
			"dataHora": "01/05/2021 18:30:00",
			"estabelecimento": "Supermercado"
			},
		"formaPagamento": {
			"tipo": "AVISTA",
			"parcelas": "1"
		}
	}
}

b) Obs: o body abaixo é uma variação, para a funcionalidade validaSeJaAutorizada() - /src/main/java/com/example/pagamento/service/PagamentoService.java

{
		"transacao": {
			"cartao": "333******1234",
			"descricao": {
				"valor": "50",
				"dataHora": "01/05/2021 18:30:00",
				"estabelecimento": "PetShop Mundo cão",
				"nsu": "1234567890",
				"codigoAutorizacao": "147258369",
				"status": "AUTORIZADO"
			},
			"formaPagamento": {
				"tipo": "AVISTA",
				"parcelas": "1"
			}
		}
	}


4)

GET localhost:8080/api/estorno/estornarTransacao?id=100023901

5)

localhost:8080/api/estorno/consultaPorId?id=100023901
