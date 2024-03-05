package cl.bicevida.sysreserva.application.controladores;

import cl.bicevida.sysreserva.domain.modelo.Pais_DTO;
import cl.bicevida.sysreserva.domain.modelo.Pais_Entity;
import cl.bicevida.sysreserva.domain.puertosEntrada.Pais_PuertoEntrada;
import cl.bicevida.sysreserva.domain.puertosSalida.Pais_PuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class Pais_Controlador implements Pais_PuertoEntrada {

    private Pais_PuertoSalida pais_puertoSalida;

    public Pais_Controlador(Pais_PuertoSalida Pais_puertoSalida) {
        this.pais_puertoSalida = Pais_puertoSalida;
    }

    @Override
    public Pais_DTO crearPais_PuertoEntrada(Pais_DTO data_pais){
        return pais_puertoSalida.crearPais_PuertoSalida(data_pais);
    }

    @Override
    public List<Pais_DTO> obtenerTodosPais_PuertoEntrada(){
        return pais_puertoSalida.obtenerTodosPais_PuertoSalida();
    }

    @Override
    public Pais_DTO obtenerPais_PuertoEntrada(long id){
        return pais_puertoSalida.obtenerPais_PuertoSalida(id);
    }

    public Pais_DTO actualizarPais_PuertoEntrada(long id, Pais_DTO pais){
        return pais_puertoSalida.actualizarPais_PuertoSalida(id, pais);
    }

    @Override
    public void eliminarPais_PuertoEntrada(long id){
        pais_puertoSalida.eliminarPais_PuertoSalida(id);
    }

}
