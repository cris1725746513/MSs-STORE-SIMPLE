package cl.pixysoft.sysreserva.domain.puertosSalida;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;

import java.util.List;

public interface ProductoPuertoSalida {
    public ProductoDto crearProducto_PuertoSalida(ProductoDto productoNew);

    public List<ProductoDto> obtenerTodosProducto_PuertoSalida(String filtro);

    public ProductoDto obtenerProducto_PuertoSalida(String nombre,
                                                    String detalle,
                                                    String codigo);

    public ProductoDto actualizarProducto_PuertoSalida(ProductoDto paisUpdate);

    public void eliminarProducto_PuertoSalida(long id);
}
