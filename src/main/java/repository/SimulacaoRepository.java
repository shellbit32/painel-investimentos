package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Simulacao;

@ApplicationScoped
public class SimulacaoRepository implements PanacheRepository<Simulacao> {
}