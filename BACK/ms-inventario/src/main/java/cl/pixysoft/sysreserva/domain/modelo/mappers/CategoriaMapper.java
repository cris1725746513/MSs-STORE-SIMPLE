package cl.pixysoft.sysreserva.domain.modelo.mappers;

import cl.pixysoft.sysreserva.domain.modelo.dtos.CategoriaDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Categoria;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface CategoriaMapper {
    Categoria toEntity(CategoriaDto categoriaDto);

    CategoriaDto toDto(Categoria categoria);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Categoria partialUpdate(CategoriaDto categoriaDto, @MappingTarget Categoria categoria);
}