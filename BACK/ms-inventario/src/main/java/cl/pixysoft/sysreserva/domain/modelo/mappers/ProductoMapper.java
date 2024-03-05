package cl.pixysoft.sysreserva.domain.modelo.mappers;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Producto;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.CDI)
public interface ProductoMapper {
    Producto toEntity(ProductoDto productoDto);

    ProductoDto toDto(Producto producto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Producto partialUpdate(ProductoDto productoDto, @MappingTarget Producto producto);
}