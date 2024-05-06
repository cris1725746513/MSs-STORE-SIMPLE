package cl.pixysoft.sysreserva.infraestructure;


import cl.pixysoft.sysreserva.domain.modelo.dtos.FormasPagoDto;
import cl.pixysoft.sysreserva.domain.modelo.mappers.FormasPagoMapper;
import cl.pixysoft.sysreserva.domain.puertosSalida.FormaPagoPuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FormaPagoDB implements FormaPagoPuertoSalida {

    @Inject
    FormasPagosRepository repository;

    @Inject
    FormasPagoMapper mapper;

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public List<FormasPagoDto> obtenerTodosFormaPago_PuertoSalida() {
        List<FormasPagoDto> pagoDtos = repository.findAll().list()
                .stream().map(formasPago -> mapper.toDto(formasPago))
                .collect(Collectors.toList());
        return pagoDtos;
    }
}
