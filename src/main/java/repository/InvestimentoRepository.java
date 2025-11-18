package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Investimento;

@ApplicationScoped
public class InvestimentoRepository implements PanacheRepository<Investimento> {
}