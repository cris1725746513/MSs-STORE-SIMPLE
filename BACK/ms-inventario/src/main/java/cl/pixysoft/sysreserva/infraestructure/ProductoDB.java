package cl.pixysoft.sysreserva.infraestructure;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.modelo.entities.Producto;
import cl.pixysoft.sysreserva.domain.modelo.mappers.ProductoMapper;
import cl.pixysoft.sysreserva.domain.puertosSalida.ProductoPuertoSalida;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

public class ProductoDB implements ProductoPuertoSalida {

    @Inject
    ProductoRepository repository;

    @Inject
    ProductoMapper mapper;


    @Override
    public ProductoDto crearProducto_PuertoSalida(ProductoDto productoNew) {
        return null;
    }

    @Override
    public List<ProductoDto> obtenerTodosProducto_PuertoSalida(String filtro) {
        return null;
    }

    @Override
    public ProductoDto obtenerProducto_PuertoSalida(String nombre, String detalle, String codigo) {
       repository.
        return null;
    }

    @Override
    public ProductoDto actualizarProducto_PuertoSalida(ProductoDto paisUpdate) {

        return null;
    }

    @Override
    public void eliminarProducto_PuertoSalida(long id) {
        Producto producto = repository.findById(id);
        if(Objects.nonNull(producto)){
            repository.deleteById(id);
        }
    }
}
