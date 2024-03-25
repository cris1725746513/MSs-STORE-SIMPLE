package cl.pixysoft.sysreserva.application.rutas;


import cl.pixysoft.sysreserva.application.controladores.ProductosControlador;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ProductoDto;
import cl.pixysoft.sysreserva.domain.puertosSalida.ProductoPuertoSalida;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotEmpty;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

import java.util.Objects;

@Path("v1/inventario/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ProductosRutas {


    @Inject
    ProductoPuertoSalida puertoSalida;


    @POST
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackCrearProducto")
    public Response crearProducto(ProductoDto data){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        ProductoDto dto = controlador.
                crearProducto_PuertoEntrada(data);
        if(Objects.nonNull(dto)){
            return Response.status(201).entity(dto).build();
        }else {
            JsonObject json = Json.createObjectBuilder()
                    .add("Error", "Error al crear producto")
                    .build();
            return Response.status(404).entity(json).build();
        }

    }
    public Response fallbackCrearProducto(ProductoDto productoDto){

        return Response.status(503).build();
    }



    @PUT
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackActualizarProducto")
    public Response actualizarProducto( ProductoDto productoDto){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        ProductoDto dto = controlador.
                actualizarProducto_PuertoEntrada(productoDto);
        if(Objects.nonNull(dto)){
            return Response.status(201).entity(dto).build();
        }else {
            JsonObject json = Json.createObjectBuilder()
                    .add("Error", "Producto no encontrado")
                    .build();
            return Response.status(404).entity(json).build();
        }

    }
    public Response fallbackActualizarProducto(ProductoDto productoDto){

        return Response.status(503).build();
    }



    @DELETE
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackEliminarProducto")
    public Response eliminarProducto(@PathParam("id") long id){
        ProductosControlador controlador = new ProductosControlador(puertoSalida);
        controlador.eliminarProducto_PuertoEntrada(id);
        return Response.status(201).build();
    }
    public Response fallbackEliminarProducto(@PathParam("{id}") long id){
        return Response.status(503).build();
    }

}
