
  
  
  INSERT INTO Descricao (valor,	dataHora,estabelecimento,nsu,codigoAutorizacao,	status)  VALUES
  ('50', '14/07/2025 18:30:00','Mercadão das Frutas','1234567890','147258369','AUTORIZADO'),
  ('40', '10/07/2025 15:20:00','Mercadão das Frutas','1234567890','123456789','NEGADO'),
  ('100', '30/06/2025 14:30:00','Mercadão das Frutas','1234567890','456789123','AUTORIZADO');
  
  
  INSERT INTO FormaPagamento (tipo,parcelas) VALUES
  ('AVISTA', '1'),
  ('PARCELADO LOJA', '6'),
  ('PARCELADO EMISSOR', '8');

  INSERT INTO Transacao (id, cartao,DescricaoId,FormaPagamentoId) VALUES
  (100023901,'333******1234',1,1),
  (100023902,'444******4567',2,2),
  (100023903,'555******6789',3,3);
  
