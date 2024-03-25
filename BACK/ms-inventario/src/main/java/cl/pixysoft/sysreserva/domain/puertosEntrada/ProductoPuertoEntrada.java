package cl.pixysoft.sysreserva.domain.puertosEntrada;

import cl.pixysoft.sysreserva.domain.modelo.dtos.CategoriaDto;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;

import java.util.List;

public interface ProductoPuertoEntrada {

    public ProductoDto crearProducto_PuertoEntrada(ProductoDto productoNew);

    public List<ProductoDto> obtenerTodosProducto_PuertoEntrada();

    public List<CategoriaDto> obtenerTodosCategorias_PuertoEntrada();

    public ProductoDto obtenerProducto_PuertoEntrada(String codigo);

    public ProductoDto actualizarProducto_PuertoEntrada(ProductoDto paisUpdate);

    public void eliminarProducto_PuertoEntrada(long id);

}
