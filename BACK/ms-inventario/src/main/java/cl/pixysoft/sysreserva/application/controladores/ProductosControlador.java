package cl.pixysoft.sysreserva.application.controladores;

import cl.pixysoft.sysreserva.domain.modelo.dtos.CategoriaDto;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.puertosEntrada.ProductoPuertoEntrada;
import cl.pixysoft.sysreserva.domain.puertosSalida.ProductoPuertoSalida;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductosControlador  implements ProductoPuertoEntrada {

    private ProductoPuertoSalida puertoSalida;

    public ProductosControlador(ProductoPuertoSalida puertoSalida) {
        this.puertoSalida = puertoSalida;
    }

    @Override
    public ProductoDto crearProducto_PuertoEntrada(ProductoDto productoNew) {
        return this.puertoSalida.crearProducto_PuertoSalida(productoNew);
    }

    @Override
    public List<ProductoDto> obtenerTodosProducto_PuertoEntrada() {
        return this.puertoSalida.obtenerTodosProducto_PuertoSalida();
    }

    @Override
    public List<CategoriaDto> obtenerTodosCategorias_PuertoEntrada() {
        return this.puertoSalida.obtenerTodosCategoria_PuertoSalida();
    }

    @Override
    public ProductoDto obtenerProducto_PuertoEntrada(String codigo) {
        return this.puertoSalida.obtenerProducto_PuertoSalida(codigo);
    }

    @Override
    public ProductoDto actualizarProducto_PuertoEntrada(ProductoDto paisUpdate) {
        return this.puertoSalida.actualizarProducto_PuertoSalida(paisUpdate);
    }

    @Override
    public void eliminarProducto_PuertoEntrada(long id) {
            this.puertoSalida.eliminarProducto_PuertoSalida(id);
    }
}
