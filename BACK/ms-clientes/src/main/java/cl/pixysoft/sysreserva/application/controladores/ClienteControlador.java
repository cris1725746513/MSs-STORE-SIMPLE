package cl.pixysoft.sysreserva.application.controladores;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;
import cl.pixysoft.sysreserva.domain.puertosEntrada.ClientePuertoEntrada;
import cl.pixysoft.sysreserva.domain.puertosSalida.ClientePuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ClienteControlador implements ClientePuertoEntrada {

    private ClientePuertoSalida puertoSalida;


    public ClienteControlador(ClientePuertoSalida puertoSalida) {
        this.puertoSalida = puertoSalida;
    }

    @Override
    public ClienteDto crearCliente_PuertoEntrada(ClienteDto data_cliente) {
        return puertoSalida.crearCliente_PuertoSalida(data_cliente);
    }

    @Override
    public List<ClienteDto> obtenerTodosClientes_PuertoEntrada() {
        return puertoSalida.obtenerTodosCliente_PuertoSalida();
    }

    @Override
    public ClienteDto obtenerCliente_PuertoEntrada(String dni) {
        return puertoSalida.obtenercliente_PuertoSalida(dni);
    }

    @Override
    public ClienteDto actualizarCliente_PuertoEntrada(long id, ClienteDto cliente) {
        return puertoSalida.actualizarCliente_PuertoSalida(id,cliente);
    }

    @Override
    public void eliminarCliente_PuertoEntrada(long id) {
            puertoSalida.eliminarCliente_PuertoSalida(id);
    }
}
