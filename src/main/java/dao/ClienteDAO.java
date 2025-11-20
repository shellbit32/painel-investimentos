package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Cliente;

/*
 DAO utilizando native query
 */
@ApplicationScoped
public class ClienteDAO {

    @Inject
    private EntityManager entityManager;

    public Cliente buscarPorId(Long clienteId) {
        String query = """
            SELECT * FROM cliente
            WHERE id = :id
            """;

        try {
            return (Cliente) entityManager.createNativeQuery(query, Cliente.class)
                    .setParameter("id", clienteId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void atualizarPontuacao(Cliente cliente) {
        String query = """
            UPDATE cliente
            SET pontuacao_risco = :pontuacaoRisco
            WHERE id = :id
            """;

        entityManager.createNativeQuery(query)
                .setParameter("pontuacaoRisco", cliente.getPontuacaoRisco())
                .setParameter("id", cliente.getId())
                .executeUpdate();
    }
}