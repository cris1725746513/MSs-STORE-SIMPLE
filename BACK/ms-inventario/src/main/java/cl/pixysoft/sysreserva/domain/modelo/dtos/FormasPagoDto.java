package cl.pixysoft.sysreserva.domain.modelo.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.FormasPago}
 */


@Schema(
        description = "Datos de la entidad Forma de Pagp",
        name = "Forma de Pagp"
)
@RegisterForReflection
public class FormasPagoDto {

    @Schema(required = true, implementation = Integer.class, example = "56")
    @JsonbProperty("id")
    public Integer id;

    @Schema(required = true, implementation = String.class)
    @JsonbProperty("descripcion")
    public String label;


    @Schema(required = true, implementation = String.class)
    @JsonbProperty("valor")
    public String valorSri;

    
}