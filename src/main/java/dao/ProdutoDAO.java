package dao;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import model.Produto;

import java.math.BigDecimal;
import java.util.List;

/*
 DAO utilizando native query
 */
@ApplicationScoped
public class ProdutoDAO {

    @Inject
    private EntityManager entityManager;

    /*
    No caso deste projeto, o produto adequado seria o primeiro produto que se encaixar
    e que tenha a maior rentabilidade.
     */
    public Produto buscarProdutoAdequado(String tipoProduto, BigDecimal valor, Integer prazoMeses) {
        String query = """
            SELECT * FROM produto
            WHERE tipo = :tipo
            AND valor_minimo <= :valor
            AND prazo_minimo_meses <= :prazo
            AND prazo_maximo_meses >= :prazo
            AND ativo = true
            ORDER BY rentabilidade_anual DESC
            LIMIT 1
            """;

        try {
            return (Produto) entityManager.createNativeQuery(query, Produto.class)
                    .setParameter("tipo", tipoProduto)
                    .setParameter("valor", valor)
                    .setParameter("prazo", prazoMeses)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Produto> buscarPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return List.of();
        }

        String query = """
            SELECT * FROM produto
            WHERE id IN (:ids)
            """;

        return entityManager.createNativeQuery(query, Produto.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}