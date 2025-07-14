
  
  
  INSERT INTO Descricao (valor,	dataHora,estabelecimento,nsu,codigoAutorizacao,	status)  VALUES
  ('50', '01/05/2021 18:30:00','PetShop Mundo cão','1234567890','147258369','AUTORIZADO'),
  ('40', '01/05/2022 15:20:00','PetShop Mundo cão','1234567890','123456789','NEGADO'),
  ('100', '06/06/2022 14:30:00','PetShop Mundo cão','1234567890','456789123','AUTORIZADO');
  
  
  INSERT INTO FormaPagamento (tipo,parcelas) VALUES
  ('AVISTA', '1'),
  ('PARCELADO LOJA', '6'),
  ('PARCELADO EMISSOR', '8');

  INSERT INTO Transacao (id, cartao,DescricaoId,FormaPagamentoId) VALUES
  (100023901,'333******1234',1,1),
  (100023902,'444******4567',2,2),
  (100023903,'555******6789',3,3);
  
