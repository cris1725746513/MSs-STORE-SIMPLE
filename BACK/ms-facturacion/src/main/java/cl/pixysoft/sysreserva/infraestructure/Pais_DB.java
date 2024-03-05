package cl.bicevida.sysreserva.infraestructure;

import cl.bicevida.sysreserva.domain.modelo.Pais_DTO;
import cl.bicevida.sysreserva.domain.modelo.Pais_Entity;
import cl.bicevida.sysreserva.domain.modelo.Pais_Mapper;
import cl.bicevida.sysreserva.domain.puertosSalida.Pais_PuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Pais_DB implements Pais_PuertoSalida {

    @Inject
    Pais_PanacheRepository panacheRepository;

    @Inject
    Pais_Mapper paisMapper;

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public Pais_DTO crearPais_PuertoSalida(Pais_DTO pais){
        pais.sysFechaMoficiacion = LocalDateTime.now();
        panacheRepository.persist(paisMapper.toEntity(pais));
        Pais_DTO pais_encontrado = paisMapper.toDTO(panacheRepository.findById(pais.codigoPais));
        return pais_encontrado;
        //panacheRepository.persist(paisMapper.toEntity(pais));
        //return panacheRepository.findById(pais.codigoPais);
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public List<Pais_DTO> obtenerTodosPais_PuertoSalida(){

        List<Pais_DTO> pais_DTO = panacheRepository.listAll().stream()
                .map(pais -> paisMapper.toDTO(pais))
                .collect(Collectors.toList());
        return pais_DTO;

        //return panacheRepository.listAll();
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public Pais_DTO obtenerPais_PuertoSalida(long id){

        Pais_DTO pais_encontrado = paisMapper.toDTO(panacheRepository.findById(id));
        return pais_encontrado;
        //return panacheRepository.findById(id);
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public Pais_DTO actualizarPais_PuertoSalida(long id, Pais_DTO data_pais) {

        Pais_DTO pais_temp = paisMapper.toDTO(panacheRepository.findById(id));
        pais_temp.nombre = data_pais.nombre;
        pais_temp.crmNombre = data_pais.crmNombre;
        pais_temp.ctNombre = data_pais.ctNombre;
        pais_temp.sysAccion = data_pais.sysAccion;
        pais_temp.sysAplicacion = data_pais.sysAplicacion;
        pais_temp.sysEliminado = data_pais.sysEliminado;
        pais_temp.sysFechaMoficiacion = LocalDateTime.now();
        pais_temp.sysId = data_pais.sysId;
        pais_temp.sysTipo = data_pais.sysTipo;
        pais_temp.sysUsuario = data_pais.sysUsuario;
        pais_temp.sysVersion = data_pais.sysVersion;

        panacheRepository.persist(paisMapper.toEntity(pais_temp));

        return paisMapper.toDTO(panacheRepository.findById(id));
    }

    @Override
    @Retry(maxRetries = 3, delay = 3000)
    @Transactional
    public void eliminarPais_PuertoSalida(long id){
        panacheRepository.deleteById(id);
    }


}
