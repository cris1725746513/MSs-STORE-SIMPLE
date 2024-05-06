package cl.pixysoft.sysreserva.application.rutas;


import cl.pixysoft.sysreserva.application.controladores.FormaPagoControlador;
import cl.pixysoft.sysreserva.domain.puertosSalida.FormaPagoPuertoSalida;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;

@Path("v1/inventario/catalogo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional(Transactional.TxType.SUPPORTS)
public class CatalogoRutas {

    @Inject
    FormaPagoPuertoSalida salida;


    @GET
    @Path("/formaPago")
    @Retry(maxRetries = 3, delay = 3000)
    @Fallback(fallbackMethod = "fallbacktodasLasFormasPago")
    public Response todasLasFormasPago(){
        FormaPagoControlador controlador = new FormaPagoControlador(salida);
        return Response.status(200).entity(controlador.obtenerTodosFormaPago_PuertoEntrada()).build();
    }
    public Response fallbacktodasLasFormasPago() {
        return Response.status(503).build();
    }

}
