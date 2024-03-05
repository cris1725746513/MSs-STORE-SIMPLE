package cl.pixysoft.sysreserva.domain.modelo.dtos;

import cl.pixysoft.sysreserva.validator.ValidDNI;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.Cliente}
 */

@Schema(
        description = "(Descripción del esquema de datos) Clientes",
        name = "Clientes"
)
@RegisterForReflection
public class ClienteDto  {

    @Schema(required = true, implementation = Integer.class, example = "56", nullable = true)
    @JsonbProperty("id")
    public int id;

    @Schema(required = true, implementation = String.class, example = "Cliente", nullable = true)
    @JsonbProperty("nombres")
    public String nombres;


    @Schema(required = true, implementation = BigDecimal.class, example = "1234567890", nullable = true)
    @JsonbProperty("identificacion")
    public String identificacion;

    @Schema(required = true, implementation = String.class, example = "tipoIdentificacion", nullable = true)
    @JsonbProperty("tipoIdentificacion")
    public char tipoIdentificacion;

    @Schema(required = true, implementation = String.class, example = "direccion")
    @JsonbProperty("direccion")
    public String direccion;

    @Schema(required = true, implementation = String.class, example = "2345673")
    @JsonbProperty("telefono")
    public String telefono;

    @Schema(required = true, implementation = String.class, example = "ejemplo@ejem.com")
    @JsonbProperty("email")
    public String correo;

}