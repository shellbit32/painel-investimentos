-- ========================================
-- Painel de Investimentos - Dados Iniciais
-- ========================================

-- ========================================
-- PRODUTOS DE INVESTIMENTO
-- ========================================

-- CDBs (Certificado de Depósito Bancário)
INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (1, 'CDB Banco Safra 2026', 'CDB', 12.5, 6, 24, 1000.00, 'BAIXO', 'BAIXA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (2, 'CDB Banco Inter Premium', 'CDB', 13.2, 12, 36, 5000.00, 'BAIXO', 'MEDIA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (3, 'CDB Nubank Liquidez Diária', 'CDB', 10.8, 1, 12, 100.00, 'BAIXO', 'ALTA', true);

-- LCIs (Letra de Crédito Imobiliário)
INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (4, 'LCI Caixa Econômica', 'LCI', 9.5, 12, 24, 10000.00, 'BAIXO', 'BAIXA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (5, 'LCI Banco do Brasil Premium', 'LCI', 10.2, 18, 36, 20000.00, 'BAIXO', 'BAIXA', true);

-- LCAs (Letra de Crédito do Agronegócio)
INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (6, 'LCA Santander Agro', 'LCA', 9.8, 12, 24, 15000.00, 'BAIXO', 'BAIXA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (7, 'LCA Itaú BBA', 'LCA', 10.5, 24, 48, 30000.00, 'BAIXO', 'BAIXA', true);

-- Tesouro Direto
INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (8, 'Tesouro Selic 2027', 'TESOURO_DIRETO', 11.75, 1, 60, 30.00, 'MUITO_BAIXO', 'ALTA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (9, 'Tesouro Prefixado 2029', 'TESOURO_DIRETO', 12.5, 12, 72, 30.00, 'BAIXO', 'MEDIA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (10, 'Tesouro IPCA+ 2035', 'TESOURO_DIRETO', 14.8, 24, 144, 30.00, 'MEDIO', 'MEDIA', true);

-- Fundos de Investimento
INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (11, 'Fundo DI XP Investimentos', 'FUNDO_DI', 11.0, 1, 999, 100.00, 'BAIXO', 'ALTA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (12, 'Fundo Multimercado BTG', 'FUNDO_MULTIMERCADO', 15.5, 6, 999, 1000.00, 'MEDIO', 'MEDIA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (13, 'Fundo Renda Fixa Itaú', 'FUNDO_RENDA_FIXA', 10.5, 1, 999, 500.00, 'BAIXO', 'ALTA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (14, 'Fundo Ações Brasil Verde', 'FUNDO_ACOES', 18.5, 12, 999, 5000.00, 'ALTO', 'MEDIA', true);

INSERT INTO produto (id, nome, tipo, rentabilidade_anual, prazo_minimo_meses, prazo_maximo_meses, valor_minimo, risco, liquidez, ativo)
VALUES (15, 'Fundo Imobiliário HGLG11', 'FUNDO_IMOBILIARIO', 13.2, 1, 999, 100.00, 'MEDIO', 'ALTA', true);

-- ========================================
-- CLIENTES
-- ========================================

-- Cliente 1: Perfil Conservador (35 pontos)
INSERT INTO cliente (id, pontuacao_risco)
VALUES (1, 35);

-- Cliente 2: Perfil Moderado (65 pontos)
INSERT INTO cliente (id, pontuacao_risco)
VALUES (2, 65);

-- Cliente 3: Perfil Agressivo (85 pontos)
INSERT INTO cliente (id, pontuacao_risco)
VALUES (3, 85);

-- Cliente 4: Perfil Conservador (25 pontos)
INSERT INTO cliente (id, pontuacao_risco)
VALUES (4, 25);

-- Cliente 5: Perfil Moderado (55 pontos)
INSERT INTO cliente (id, pontuacao_risco)
VALUES (5, 55);

-- ========================================
-- INVESTIMENTOS (Histórico de aplicações)
-- ========================================

-- Cliente 1 (Conservador) - Produtos de baixo risco
INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (1, 1, 4, 10000.00, 12, '2024-01-15 10:30:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (2, 1, 8, 5000.00, 24, '2024-02-10 14:20:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (3, 1, 3, 2000.00, 6, '2024-03-05 09:15:00');

-- Cliente 2 (Moderado) - Mix de produtos
INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (4, 2, 1, 8000.00, 12, '2024-01-20 11:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (5, 2, 9, 6000.00, 18, '2024-02-15 15:30:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (6, 2, 12, 10000.00, 12, '2024-03-10 10:45:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (7, 2, 13, 3000.00, 6, '2024-04-05 13:20:00');

-- Cliente 3 (Agressivo) - Produtos de maior risco e retorno
INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (8, 3, 14, 20000.00, 24, '2024-01-25 09:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (9, 3, 12, 15000.00, 18, '2024-02-20 11:30:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (10, 3, 15, 8000.00, 12, '2024-03-15 14:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (11, 3, 10, 12000.00, 36, '2024-04-10 10:00:00');

-- Cliente 4 (Conservador) - Foco em segurança
INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (12, 4, 8, 3000.00, 12, '2024-02-01 10:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (13, 4, 11, 5000.00, 6, '2024-03-01 11:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (14, 4, 3, 1500.00, 3, '2024-04-01 09:30:00');

-- Cliente 5 (Moderado) - Diversificação
INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (15, 5, 2, 7000.00, 12, '2024-02-05 14:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (16, 5, 6, 15000.00, 18, '2024-03-12 10:30:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (17, 5, 13, 4000.00, 6, '2024-04-08 15:00:00');

INSERT INTO investimento (id, cliente_id, produto_id, valor, prazo_meses, data_hora_investimento)
VALUES (18, 5, 15, 6000.00, 12, '2024-05-01 11:00:00');

-- ========================================
-- SIMULAÇÕES
-- ========================================

-- Simulações do Cliente 1 (Conservador)
INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (1, 1, 4, 10000.00, 12, 10950.00, 9.5, '2024-10-15 14:30:00');

INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (2, 1, 8, 5000.00, 24, 6175.00, 11.75, '2024-10-15 15:45:00');

-- Simulações do Cliente 2 (Moderado)
INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (3, 2, 1, 10000.00, 12, 11250.00, 12.5, '2024-10-20 10:20:00');

INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (4, 2, 12, 8000.00, 12, 9240.00, 15.5, '2024-10-20 11:00:00');

-- Simulações do Cliente 3 (Agressivo)
INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (5, 3, 14, 20000.00, 12, 23700.00, 18.5, '2024-10-22 16:00:00');

INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (6, 3, 10, 15000.00, 24, 19440.00, 14.8, '2024-10-22 16:30:00');

-- Simulações do Cliente 4 (Conservador)
INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (7, 4, 3, 1000.00, 6, 1054.00, 10.8, '2024-10-25 09:15:00');

-- Simulações do Cliente 5 (Moderado)
INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (8, 5, 6, 15000.00, 18, 17205.00, 9.8, '2024-10-30 10:00:00');

INSERT INTO simulacao (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
VALUES (9, 5, 15, 5000.00, 12, 5660.00, 13.2, '2024-10-30 10:30:00');

-- ========================================
-- TELEMETRIA
-- ========================================

-- Chamadas do serviço de simulação
INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (1, 'simular-investimento', 245, '2024-10-15 14:30:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (2, 'simular-investimento', 198, '2024-10-15 15:45:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (3, 'simular-investimento', 310, '2024-10-20 10:20:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (4, 'simular-investimento', 287, '2024-10-20 11:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (5, 'simular-investimento', 265, '2024-10-22 16:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (6, 'simular-investimento', 301, '2024-10-22 16:30:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (7, 'simular-investimento', 189, '2024-10-25 09:15:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (8, 'simular-investimento', 276, '2024-10-30 10:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (9, 'simular-investimento', 234, '2024-10-30 10:30:00');

-- Chamadas do serviço de perfil de risco
INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (10, 'perfil-risco', 152, '2024-10-16 10:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (11, 'perfil-risco', 180, '2024-10-21 11:30:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (12, 'perfil-risco', 165, '2024-10-23 14:00:00');

-- Chamadas do serviço de produtos recomendados
INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (13, 'produtos-recomendados', 95, '2024-10-17 09:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (14, 'produtos-recomendados', 102, '2024-10-19 10:30:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (15, 'produtos-recomendados', 88, '2024-10-24 15:00:00');

-- Chamadas do serviço de listagem de investimentos
INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (16, 'investimentos', 120, '2024-10-18 11:00:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (17, 'investimentos', 135, '2024-10-26 13:30:00');

INSERT INTO telemetria (id, nome_servico, tempo_resposta_ms, data_hora)
VALUES (18, 'investimentos', 115, '2024-10-28 16:00:00');
