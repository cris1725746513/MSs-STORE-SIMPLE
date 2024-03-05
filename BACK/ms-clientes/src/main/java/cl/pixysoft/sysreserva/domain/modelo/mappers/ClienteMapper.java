package cl.pixysoft.sysreserva.domain.modelo.mappers;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Cliente;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface ClienteMapper {
    Cliente toEntity(ClienteDto clienteDto);

    ClienteDto toDto(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cliente partialUpdate(ClienteDto clienteDto, @MappingTarget Cliente cliente);
}