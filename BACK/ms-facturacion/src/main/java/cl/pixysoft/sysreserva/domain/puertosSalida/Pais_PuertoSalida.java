package cl.bicevida.sysreserva.domain.puertosSalida;

import cl.bicevida.sysreserva.domain.modelo.Pais_DTO;
import cl.bicevida.sysreserva.domain.modelo.Pais_Entity;

import java.util.List;

public interface Pais_PuertoSalida {

    public Pais_DTO crearPais_PuertoSalida(Pais_DTO pais);

    public Pais_DTO obtenerPais_PuertoSalida(long id);

    public List<Pais_DTO> obtenerTodosPais_PuertoSalida();
    public Pais_DTO actualizarPais_PuertoSalida(long id, Pais_DTO data_pais);

    public void eliminarPais_PuertoSalida(long id);
}
