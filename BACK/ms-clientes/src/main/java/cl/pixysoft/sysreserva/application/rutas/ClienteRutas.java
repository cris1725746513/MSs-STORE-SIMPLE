package cl.pixysoft.sysreserva.application.rutas;


import cl.pixysoft.sysreserva.application.controladores.ClienteControlador;
import cl.pixysoft.sysreserva.domain.modelo.dtos.ClienteDto;
import cl.pixysoft.sysreserva.domain.puertosSalida.ClientePuertoSalida;
import cl.pixysoft.sysreserva.utils.Validadores;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import java.util.Objects;

@Path("v1/sysreserva/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class ClienteRutas {

    @Inject
    ClientePuertoSalida puertoSalida;

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
    @Fallback(fallbackMethod = "fallbackTodosLosCliente")
    public Response todosLosCliente(){
        ClienteControlador controlador = new ClienteControlador(puertoSalida);
        return Response.status(200).entity(controlador.obtenerTodosClientes_PuertoEntrada()).build();
    }
    public Response fallbackTodosLosCliente() {
        return Response.status(503).build();
    }

    @GET
    @Path("/{dni}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackObtenerCliente")
    public Response obtenerCliente(@PathParam("dni") String dni){
        ClienteControlador controlador = new ClienteControlador(puertoSalida);
        ClienteDto clienteEncontrado = controlador.obtenerCliente_PuertoEntrada(dni);
        if(clienteEncontrado != null) {
            return Response.status(200).entity(clienteEncontrado).build();
        }
        else{
            return Response.status(404).build();
        }

    }
    public Response fallbackObtenerCliente(@PathParam("{dni}") String dni){
        return Response.status(503).build();
    }

    @POST
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackCrearCliente")
    public Response crearCliente(ClienteDto data){

        if(Validadores.VerificarCedula(data.identificacion)){

            ClienteControlador controlador = new ClienteControlador(puertoSalida);
            ClienteDto dto = controlador
                    .crearCliente_PuertoEntrada(data);
            if (Objects.nonNull(dto)){
                return Response.status(201).entity(dto).build();
            }else {
                JsonObject json = Json.createObjectBuilder()
                        .add("Error", "Cliente no creado")
                        .build();
                return Response.status(400).entity(json.toString()).build();
            }

        }else{
            JsonObject json = Json.createObjectBuilder()
                    .add("Error", "identificacion no valida")
                    .build();
            return Response.status(400).entity(json.toString()).build();
        }

    }
    public Response fallbackCrearCliente(ClienteDto data){
        return Response.status(503).build();
    }

    @PUT
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackActualizarCliente")
    public Response actualizarCliente(@PathParam("id") long id, ClienteDto dto){
        if(Validadores.VerificarCedula(dto.identificacion)){
            ClienteControlador controlador = new ClienteControlador(puertoSalida);
            ClienteDto dtoRespon = controlador
                    .actualizarCliente_PuertoEntrada(id, dto);
            if (Objects.nonNull(dtoRespon)){
                return Response.status(201).entity(dtoRespon).build();
            }else {
                JsonObject json = Json.createObjectBuilder()
                        .add("Error", "Cliente no creado")
                        .build();
                return Response.status(400).entity(json.toString()).build();
            }

        }else{
            JsonObject json = Json.createObjectBuilder()
                    .add("Error", "identificacion no valida")
                    .build();
            return Response.status(400).entity(json.toString()).build();
        }
    }
    public Response fallbackActualizarCliente(long id, ClienteDto dto){
        return Response.status(503).build();
    }

    @DELETE
    @Path("/{id}")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbackEliminarCliente")
    public Response eliminarCliente(@PathParam("id") long id){
        ClienteControlador controlador = new ClienteControlador(puertoSalida);
        controlador.eliminarCliente_PuertoEntrada(id);
        return Response.status(201).build();
    }
    public Response fallbackEliminarCliente(@PathParam("{id}") long id){

        return Response.status(503).build();
    }

}
