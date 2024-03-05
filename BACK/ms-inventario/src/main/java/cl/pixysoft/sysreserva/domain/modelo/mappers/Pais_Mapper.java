package cl.pixysoft.sysreserva.domain.modelo.mappers;

import cl.pixysoft.sysreserva.domain.modelo.dtos.Pais_DTO;
import cl.pixysoft.sysreserva.domain.modelo.entities.Pais_Entity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Pais_Mapper {

    //Pais_Mapper INSTANCE = Mappers.getMapper(Pais_Mapper.class);

    //@Mapping()
    Pais_DTO toDTO(Pais_Entity paisEntity);

    //@Mapping()
    Pais_Entity toEntity(Pais_DTO paisDTO);
}
