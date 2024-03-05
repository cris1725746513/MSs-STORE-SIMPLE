package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Cliente;
import cl.pixysoft.sysreserva.domain.modelo.mappers.ClienteMapper;
import cl.pixysoft.sysreserva.domain.puertosSalida.ClientePuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ClienteDB implements ClientePuertoSalida {


    @Inject
    ClienteRepository panacheRepository;

    @Inject
    ClienteMapper mapper;

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ClienteDto crearCliente_PuertoSalida(ClienteDto cliente) {
        Cliente clienteEntity = mapper.toEntity(cliente);
        panacheRepository.persist(clienteEntity);
        return cliente;
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ClienteDto obtenercliente_PuertoSalida(String dni) {
        Cliente cliente = panacheRepository.find("identificacion",dni).firstResult();
        return mapper.toDto(cliente);
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public List<ClienteDto> obtenerTodosCliente_PuertoSalida() {
        List<ClienteDto>  clientesDto= new ArrayList<>();
        List<Cliente> clientesEntity = panacheRepository
                .findAll().list();
        clientesEntity.forEach(cliente ->
                clientesDto.add(mapper.toDto(cliente)));
        return clientesDto;
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public ClienteDto actualizarCliente_PuertoSalida(long id, ClienteDto data_cliente) {
        Cliente cliente  = panacheRepository.find("id",id)
                .firstResult();
        if(Objects.nonNull(cliente)){
                cliente.setCorreo(data_cliente.correo);
                cliente.setDireccion(data_cliente.direccion);
                cliente.setIdentificacion(data_cliente.identificacion);
                cliente.setNombres(data_cliente.nombres);
                cliente.setTelefono(data_cliente.telefono);
                cliente.setTipoIdentificacion(data_cliente.tipoIdentificacion);
                return mapper.toDto(cliente);
        }else {
            return mapper.toDto(null);
        }
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public void eliminarCliente_PuertoSalida(long id) {
        Cliente cliente = panacheRepository.findById(id);
        if(Objects.nonNull(cliente)){
            panacheRepository.deleteById(id);
        }

    }
}
