package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.entities.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {

    public ClienteRepository() {}
}
