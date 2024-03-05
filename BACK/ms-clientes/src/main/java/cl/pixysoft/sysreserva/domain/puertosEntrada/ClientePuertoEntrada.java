package cl.pixysoft.sysreserva.domain.puertosEntrada;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;

import java.util.List;

public interface ClientePuertoEntrada {

    public ClienteDto crearCliente_PuertoEntrada(ClienteDto data_cliente);

    public List<ClienteDto> obtenerTodosClientes_PuertoEntrada();

    public ClienteDto obtenerCliente_PuertoEntrada(String dni);

    public ClienteDto actualizarCliente_PuertoEntrada(long id, ClienteDto cliente);

    public void eliminarCliente_PuertoEntrada(long id);
}
