package cl.pixysoft.sysreserva.application.controladores;

import cl.pixysoft.sysreserva.domain.modelo.dtos.FormasPagoDto;
import cl.pixysoft.sysreserva.domain.puertosEntrada.FormaPagoPuertoEntrada;
import cl.pixysoft.sysreserva.domain.puertosSalida.FormaPagoPuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class FormaPagoControlador implements FormaPagoPuertoEntrada {

    private FormaPagoPuertoSalida salida;

    public FormaPagoControlador(FormaPagoPuertoSalida salida) {
        this.salida = salida;
    }

    @Override
    public List<FormasPagoDto> obtenerTodosFormaPago_PuertoEntrada() {
        return this.salida.obtenerTodosFormaPago_PuertoSalida();
    }
}
