package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Simulacao;

import java.util.List;

/*
 DAO utilizando native query
 */
@ApplicationScoped
public class SimulacaoDAO {

    @Inject
    private EntityManager entityManager;

    public void inserir(Simulacao simulacao) {
        Long nextId = obterProximoId();

        String query = """
            INSERT INTO simulacao
            (id, cliente_id, produto_id, valor_investido, prazo_meses, valor_final, rentabilidade_efetiva, data_simulacao)
            VALUES (:id, :clienteId, :produtoId, :valorInvestido, :prazoMeses, :valorFinal, :rentabilidadeEfetiva, :dataSimulacao)
            """;

        entityManager.createNativeQuery(query)
                .setParameter("id", nextId)
                .setParameter("clienteId", simulacao.getClienteId())
                .setParameter("produtoId", simulacao.getProdutoId())
                .setParameter("valorInvestido", simulacao.getValorInvestido())
                .setParameter("prazoMeses", simulacao.getPrazoMeses())
                .setParameter("valorFinal", simulacao.getValorFinal())
                .setParameter("rentabilidadeEfetiva", simulacao.getRentabilidadeEfetiva())
                .setParameter("dataSimulacao", simulacao.getDataSimulacao())
                .executeUpdate();

        simulacao.setId(nextId);
    }

    private Long obterProximoId() {
        String selectSeqQuery = "SELECT next_val FROM simulacao_SEQ";
        Long seqValue = ((Number) entityManager.createNativeQuery(selectSeqQuery).getSingleResult()).longValue();

        String selectMaxQuery = "SELECT COALESCE(MAX(id), 0) FROM simulacao";
        Long maxId = ((Number) entityManager.createNativeQuery(selectMaxQuery).getSingleResult()).longValue();

        Long nextId = Math.max(seqValue, maxId + 1);

        String updateQuery = "UPDATE simulacao_SEQ SET next_val = :nextVal";
        entityManager.createNativeQuery(updateQuery)
                .setParameter("nextVal", nextId + 1)
                .executeUpdate();

        return nextId;
    }

    public List<Object[]> buscarHistoricoSimulacoes() {
        String query = """
            SELECT
                s.id,
                s.cliente_id,
                p.nome,
                CAST(s.valor_investido AS REAL),
                CAST(s.valor_final AS REAL),
                s.prazo_meses,
                s.data_simulacao
            FROM simulacao s
            INNER JOIN produto p ON s.produto_id = p.id
            ORDER BY s.data_simulacao DESC
            """;

        return entityManager.createNativeQuery(query).getResultList();
    }
}