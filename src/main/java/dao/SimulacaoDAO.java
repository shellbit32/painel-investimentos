package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Simulacao;

/*
 DAO utilizando native query
 */
@ApplicationScoped
public class SimulacaoDAO {

    @Inject
    EntityManager entityManager;

    public void inserir(Simulacao simulacao) {
        String query = """
            INSERT INTO simulacao
            (cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
            VALUES (:clienteId, :produtoId, :valorInvestido, :prazoMeses, :valorFinal, :rentabilidadeEfetiva, :dataSimulacao)
            """;

        entityManager.createNativeQuery(query)
                .setParameter("clienteId", simulacao.getClienteId())
                .setParameter("produtoId", simulacao.getProdutoId())
                .setParameter("valorInvestido", simulacao.getValorInvestido())
                .setParameter("prazoMeses", simulacao.getPrazoMeses())
                .setParameter("valorFinal", simulacao.getValorFinal())
                .setParameter("rentabilidadeEfetiva", simulacao.getRentabilidadeEfetiva())
                .setParameter("dataSimulacao", simulacao.getDataSimulacao())
                .executeUpdate();
    }
}