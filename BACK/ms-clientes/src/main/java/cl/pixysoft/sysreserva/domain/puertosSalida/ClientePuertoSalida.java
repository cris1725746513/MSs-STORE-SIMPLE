package cl.pixysoft.sysreserva.domain.puertosSalida;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;

import java.util.List;

public interface ClientePuertoSalida {

    public ClienteDto crearCliente_PuertoSalida(ClienteDto cliente);

    public ClienteDto obtenercliente_PuertoSalida(String dni);

    public List<ClienteDto> obtenerTodosCliente_PuertoSalida();
    public ClienteDto actualizarCliente_PuertoSalida(long id, ClienteDto data_cliente);

    public void eliminarCliente_PuertoSalida(long id);
}
