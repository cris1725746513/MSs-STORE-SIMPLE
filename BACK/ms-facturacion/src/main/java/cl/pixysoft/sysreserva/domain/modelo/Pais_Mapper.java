package cl.bicevida.sysreserva.domain.modelo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface Pais_Mapper {

    //Pais_Mapper INSTANCE = Mappers.getMapper(Pais_Mapper.class);

    //@Mapping()
    Pais_DTO toDTO(Pais_Entity paisEntity);

    //@Mapping()
    Pais_Entity toEntity(Pais_DTO paisDTO);
}
