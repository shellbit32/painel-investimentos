package service;

import dao.ClienteDAO;
import dao.InvestimentoDAO;
import dao.ProdutoDAO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Cliente;
import model.Investimento;
import model.Produto;
import model.enums.TipoRiscoEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class PerfilRiscoService {

    @Inject
    private InvestimentoDAO investimentoDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ProdutoDAO produtoDAO;

    /**
     * Calcula e atualiza a pontuação de risco do cliente baseado em seu histórico de investimentos
     * @param clienteId ID do cliente
     * @return Pontuação calculada (0-100)
     */
    public Integer calcularPontuacaoRisco(Long clienteId) {
        // Busca investimentos dos últimos 12 meses
        LocalDateTime dataLimite = LocalDateTime.now().minusMonths(12);
        List<Investimento> investimentos = investimentoDAO.buscarPorClienteIdAposData(clienteId, dataLimite);

        // Cliente novo sem histórico = Conservador (pontuação 0)
        if (investimentos.isEmpty()) {
            return 0;
        }

        // 1. Calcular pontuação por volume (peso 40%)
        int pontuacaoVolume = calcularPontuacaoVolume(investimentos);

        // 2. Calcular pontuação por frequência (peso 30%)
        int pontuacaoFrequencia = calcularPontuacaoFrequencia(investimentos);

        // 3. Calcular pontuação por liquidez/rentabilidade (peso 30%)
        int pontuacaoLiquidez = calcularPontuacaoLiquidezRentabilidade(investimentos);

        // 4. Aplicar pesos e calcular pontuação final
        double pontuacaoFinal = (pontuacaoVolume * 0.4) +
                                (pontuacaoFrequencia * 0.3) +
                                (pontuacaoLiquidez * 0.3);

        int pontuacao = Math.min(100, (int) Math.round(pontuacaoFinal));

        // Atualiza a pontuação no banco
        Cliente cliente = clienteDAO.buscarPorId(clienteId);
        if (cliente != null) {
            cliente.setPontuacaoRisco(pontuacao);
            clienteDAO.atualizarPontuacao(cliente);
        }

        return pontuacao;
    }

    /**
     * Calcula pontuação baseada no volume total investido (0-100)
     * Escala:
     * - Até R$ 10.000: 0-25 pontos
     * - R$ 10.001 - R$ 50.000: 26-50 pontos
     * - R$ 50.001 - R$ 100.000: 51-75 pontos
     * - Acima de R$ 100.000: 76-100 pontos
     */
    private int calcularPontuacaoVolume(List<Investimento> investimentos) {
        BigDecimal volumeTotal = investimentos.stream()
            .map(Investimento::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        double valor = volumeTotal.doubleValue();

        if (valor <= 10000) {
            // 0 a 10k: escala linear de 0 a 25
            return (int) Math.round((valor / 10000) * 25);
        } else if (valor <= 50000) {
            // 10k a 50k: escala linear de 26 a 50
            return (int) Math.round(25 + ((valor - 10000) / 40000) * 25);
        } else if (valor <= 100000) {
            // 50k a 100k: escala linear de 51 a 75
            return (int) Math.round(50 + ((valor - 50000) / 50000) * 25);
        } else {
            // Acima de 100k: escala linear de 76 a 100 (cap em 200k)
            double pontos = 75 + ((Math.min(valor, 200000) - 100000) / 100000) * 25;
            return (int) Math.round(Math.min(100, pontos));
        }
    }

    /**
     * Calcula pontuação baseada na frequência de investimentos (0-100)
     * Escala:
     * - 1-3 investimentos: 0-25 pontos
     * - 4-8 investimentos: 26-50 pontos
     * - 9-15 investimentos: 51-75 pontos
     * - Mais de 15: 76-100 pontos
     */
    private int calcularPontuacaoFrequencia(List<Investimento> investimentos) {
        int quantidade = investimentos.size();

        if (quantidade <= 3) {
            // 1 a 3: escala linear de 0 a 25
            return (int) Math.round(((quantidade - 1) / 2.0) * 25);
        } else if (quantidade <= 8) {
            // 4 a 8: escala linear de 26 a 50
            return (int) Math.round(25 + ((quantidade - 3) / 5.0) * 25);
        } else if (quantidade <= 15) {
            // 9 a 15: escala linear de 51 a 75
            return (int) Math.round(50 + ((quantidade - 8) / 7.0) * 25);
        } else {
            // Acima de 15: escala linear de 76 a 100 (cap em 30)
            double pontos = 75 + ((Math.min(quantidade, 30) - 15) / 15.0) * 25;
            return (int) Math.round(Math.min(100, pontos));
        }
    }

    /**
     * Calcula pontuação baseada na preferência por liquidez vs rentabilidade (0-100)
     * Considera:
     * - Prazo médio dos investimentos
     * - Perfil de risco dos produtos escolhidos
     *
     * Maior liquidez (menor prazo + produtos conservadores) = menor pontuação
     * Maior rentabilidade (maior prazo + produtos arrojados) = maior pontuação
     */
    private int calcularPontuacaoLiquidezRentabilidade(List<Investimento> investimentos) {
        // Calcular prazo médio ponderado pelo valor
        BigDecimal valorTotal = investimentos.stream()
            .map(Investimento::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        double prazoMedioPonderado = investimentos.stream()
            .mapToDouble(inv -> {
                double peso = inv.getValor().divide(valorTotal, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
                return inv.getPrazoMeses() * peso;
            })
            .sum();

        // Pontuação base por prazo (0-70 pontos)
        int pontuacaoPrazo;
        if (prazoMedioPonderado < 6) {
            // Menos de 6 meses: 0-23 pontos
            pontuacaoPrazo = (int) Math.round((prazoMedioPonderado / 6) * 23);
        } else if (prazoMedioPonderado <= 12) {
            // 6 a 12 meses: 24-46 pontos
            pontuacaoPrazo = (int) Math.round(23 + ((prazoMedioPonderado - 6) / 6) * 23);
        } else {
            // Mais de 12 meses: 47-70 pontos (cap em 36 meses)
            double meses = Math.min(prazoMedioPonderado, 36);
            pontuacaoPrazo = (int) Math.round(46 + ((meses - 12) / 24) * 24);
        }

        // Bônus/penalidade por tipo de produto (±30 pontos)
        int ajusteProduto = calcularAjustePorTipoProduto(investimentos);

        int pontuacaoFinal = pontuacaoPrazo + ajusteProduto;
        return Math.max(0, Math.min(100, pontuacaoFinal));
    }

    /**
     * Calcula ajuste de pontuação baseado no perfil de risco dos produtos
     * Produtos conservadores: penalidade (reduz pontuação)
     * Produtos arrojados: bônus (aumenta pontuação)
     */
    private int calcularAjustePorTipoProduto(List<Investimento> investimentos) {
        // Buscar produtos associados aos investimentos
        List<Long> produtoIds = investimentos.stream()
            .map(Investimento::getProdutoId)
            .distinct()
            .collect(Collectors.toList());

        List<Produto> produtos = produtoDAO.buscarPorIds(produtoIds);

        if (produtos.isEmpty()) {
            return 0;
        }

        // Mapear investimentos com seus produtos
        Map<Long, Produto> produtoMap = produtos.stream()
            .collect(Collectors.toMap(Produto::getId, p -> p));

        // Calcular pontuação média de risco ponderada por valor
        BigDecimal valorTotal = investimentos.stream()
            .map(Investimento::getValor)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        double riscoMedioPonderado = investimentos.stream()
            .filter(inv -> produtoMap.containsKey(inv.getProdutoId()))
            .mapToDouble(inv -> {
                double peso = inv.getValor().divide(valorTotal, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
                Produto produto = produtoMap.get(inv.getProdutoId());
                int pontuacaoRisco = obterPontuacaoRisco(produto.getRisco());
                return pontuacaoRisco * peso;
            })
            .sum();

        // Converter para ajuste: -30 a +30
        // Risco médio 0 (muito conservador) = -30
        // Risco médio 50 (neutro) = 0
        // Risco médio 100 (muito agressivo) = +30
        return (int) Math.round((riscoMedioPonderado - 50) * 0.6);
    }

    /**
     * Converte enum de risco em pontuação numérica
     */
    private int obterPontuacaoRisco(TipoRiscoEnum risco) {
        return switch (risco) {
            case MUITO_BAIXO -> 0;
            case BAIXO -> 25;
            case MEDIO -> 50;
            case ALTO -> 75;
            case MUITO_ALTO -> 100;
        };
    }

}