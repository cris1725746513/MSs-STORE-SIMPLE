package cl.pixysoft.sysreserva.domain.puertosEntrada;

import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;

import java.util.List;

public interface ProductoPuertoEntrada {

    public ProductoDto crearProducto_PuertoEntrada(ProductoDto productoNew);

    public List<ProductoDto> obtenerTodosProducto_PuertoEntrada(String filtro);

    public ProductoDto obtenerProducto_PuertoEntrada(String nombre,
                                                     String detalle,
                                                     String codigo);

    public ProductoDto actualizarProducto_PuertoEntrada(ProductoDto paisUpdate);

    public void eliminarProducto_PuertoEntrada(long id);

}
