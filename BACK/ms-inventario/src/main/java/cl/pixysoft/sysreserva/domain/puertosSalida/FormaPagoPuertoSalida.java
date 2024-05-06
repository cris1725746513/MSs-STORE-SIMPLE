package cl.pixysoft.sysreserva.domain.puertosSalida;

import cl.pixysoft.sysreserva.domain.modelo.dtos.FormasPagoDto;

import java.util.List;

public interface FormaPagoPuertoSalida {

     public List<FormasPagoDto> obtenerTodosFormaPago_PuertoSalida();
}
