package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import model.Investimento;

import java.time.LocalDateTime;
import java.util.List;

/*
 DAO utilizando native query
 */
@ApplicationScoped
public class InvestimentoDAO {

    @Inject
    private EntityManager entityManager;

    public List<Investimento> buscarPorClienteIdAposData(Long clienteId, LocalDateTime dataLimite) {
        String query = """
            SELECT * FROM investimento
            WHERE cliente_id = :clienteId
            AND data_hora_investimento >= :dataLimite
            ORDER BY data_hora_investimento DESC
            """;

        return entityManager.createNativeQuery(query, Investimento.class)
                .setParameter("clienteId", clienteId)
                .setParameter("dataLimite", dataLimite)
                .getResultList();
    }
}