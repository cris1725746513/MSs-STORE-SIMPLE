package cl.bicevida.sysreserva.application.rutas;

import cl.bicevida.sysreserva.application.controladores.*;
import cl.bicevida.sysreserva.domain.modelo.Pais_DTO;
import cl.bicevida.sysreserva.domain.modelo.Pais_Entity;
import cl.bicevida.sysreserva.domain.puertosSalida.*;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("v1/sysreserva/facturacion/paises")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class Pais_Rutas {

    @Inject
    Pais_PuertoSalida pais_puertoSalida;

    @GET
    @Operation(
            summary = "Listado con todos los registros en la tabla",
            description = "Devuelve lista con todos los registros en la tabla, excepto campos con valor nulo"
    )
    @APIResponses(
            value = { @APIResponse(responseCode = "200", description = "OK"),
                    @APIResponse(responseCode = "400", description = "No encontrado")}
    )
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackTodosLosPaises")
    public Response todosLosPaises(){
        Pais_Controlador controlador = new Pais_Controlador(pais_puertoSalida);
        return Response.status(200).entity(controlador.obtenerTodosPais_PuertoEntrada()).build();
    }
    public Response fallbackTodosLosPaises() {
        return Response.status(503).build();
    }

    @GET
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackObtenerPais")
    public Response obtenerPais(@PathParam("id") long id){
        Pais_Controlador controlador = new Pais_Controlador(pais_puertoSalida);
        Pais_DTO pais_encontrado = controlador.obtenerPais_PuertoEntrada(id);
        if(pais_encontrado != null) {
            return Response.status(200).entity(pais_encontrado).build();
        }
        else{
            return Response.status(404).build();
        }

    }
    public Response fallbackObtenerPais(@PathParam("{id}") long id){
        return Response.status(503).build();
    }

    @POST
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackCrearPais")
    public Response crearPais(Pais_DTO data_pais){
        Pais_Controlador controlador = new Pais_Controlador(pais_puertoSalida);
        return Response.status(201).entity(controlador.crearPais_PuertoEntrada(data_pais)).build();
    }
    public Response fallbackCrearPais(Pais_DTO data_pais){
        return Response.status(503).build();
    }

    @PUT
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackActualizarPais")
    public Response actualizarPais(@PathParam("id") long id, Pais_DTO pais){
        Pais_Controlador controlador = new Pais_Controlador(pais_puertoSalida);
        return Response.status(201).entity(controlador.actualizarPais_PuertoEntrada(id, pais)).build();
    }
    public Response fallbackActualizarPais(long id, Pais_DTO pais){
        return Response.status(503).build();
    }

    @DELETE
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackEliminarPais")
    public Response eliminarPais(@PathParam("id") long id){
        Pais_Controlador controlador = new Pais_Controlador(pais_puertoSalida);
        controlador.eliminarPais_PuertoEntrada(id);
        return Response.status(201).build();
    }
    public Response fallbackEliminarPais(@PathParam("{id}") long id){
        return Response.status(503).build();
    }

}
