package cl.bicevida.sysreserva.domain.puertosEntrada;

import cl.bicevida.sysreserva.domain.modelo.Pais_DTO;
import cl.bicevida.sysreserva.domain.modelo.Pais_Entity;

import java.util.List;

public interface Pais_PuertoEntrada {

    public Pais_DTO crearPais_PuertoEntrada(Pais_DTO data_pais);

    public List<Pais_DTO> obtenerTodosPais_PuertoEntrada();

    public Pais_DTO obtenerPais_PuertoEntrada(long id);

    public Pais_DTO actualizarPais_PuertoEntrada(long id, Pais_DTO pais);

    public void eliminarPais_PuertoEntrada(long id);
}
