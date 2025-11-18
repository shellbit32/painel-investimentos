package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Telemetria;

@ApplicationScoped
public class TelemetriaRepository implements PanacheRepository<Telemetria> {
}