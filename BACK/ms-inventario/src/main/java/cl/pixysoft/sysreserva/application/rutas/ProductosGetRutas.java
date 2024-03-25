package cl.pixysoft.sysreserva.application.rutas;

import cl.pixysoft.sysreserva.application.controladores.ProductosControlador;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.puertosSalida.ProductoPuertoSalida;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
@Path("v1/inventario/reader/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ProductosGetRutas {
    @Inject
    ProductoPuertoSalida puertoSalida;

    @GET
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackTodosLosProductos")
    public Response todosLosfiltro(){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        return Response.status(200).entity(controlador.
                obtenerTodosProducto_PuertoEntrada()).build();
    }
    public Response fallbackTodosLosProductos() {
        return Response.status(503).build();
    }


    @GET
    @Path("/{codigo}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackObtenerProducto")
    public Response obtenerProducto(@PathParam("codigo")
                                    String codigo){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        ProductoDto encontrado = controlador.
                obtenerProducto_PuertoEntrada(codigo);
        if(encontrado != null) {
            return Response.status(200).entity(encontrado).build();
        }
        else{
            return Response.status(404).build();
        }

    }
    public Response fallbackObtenerProducto(@PathParam("codigo")
                                            String codigo){
        return Response.status(503).build();
    }


    @GET
    @Path("/categorias")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackTodosLasCategorias")
    public Response todasLasCategorias(){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        return Response.status(200).entity(controlador.obtenerTodosCategorias_PuertoEntrada()).build();
    }
    public Response fallbackTodosLasCategorias() {
        return Response.status(503).build();
    }
}


