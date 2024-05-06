package cl.pixysoft.sysreserva.domain.modelo.mappers;

import cl.pixysoft.sysreserva.domain.modelo.dtos.FormasPagoDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.FormasPago;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface FormasPagoMapper {
    FormasPago toEntity(FormasPagoDto formasPagoDto);

    FormasPagoDto toDto(FormasPago formasPago);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FormasPago partialUpdate(FormasPagoDto formasPagoDto, @MappingTarget FormasPago formasPago);
}