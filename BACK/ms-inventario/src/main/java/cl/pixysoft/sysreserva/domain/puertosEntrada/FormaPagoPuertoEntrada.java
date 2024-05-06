package cl.pixysoft.sysreserva.domain.puertosEntrada;

import cl.pixysoft.sysreserva.domain.modelo.dtos.FormasPagoDto;

import java.util.List;

public interface FormaPagoPuertoEntrada {

    public List<FormasPagoDto> obtenerTodosFormaPago_PuertoEntrada();
}
