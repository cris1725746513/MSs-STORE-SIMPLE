package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.entities.Producto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto>{
}
