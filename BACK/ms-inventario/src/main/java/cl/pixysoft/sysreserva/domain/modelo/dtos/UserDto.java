package cl.pixysoft.sysreserva.domain.modelo.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.User}
 */

@Schema(
        description = "Datos de la entidad Usuarios",
        name = "Productos"
)
@RegisterForReflection
public class UserDto  {

    @Schema(required = true, implementation = Integer.class, example = "56")
    @JsonbProperty("id")
    public Integer id;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("nombre")
    public String nombres;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("user")
    public String user;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("pass")
    public String pass;
    @Schema(required = true, implementation = Integer.class)
    @JsonbProperty("rol")
    public Integer nivel;

    @Schema(required = true, implementation = Boolean.class)
    @JsonbProperty("activo")
    public Boolean estado;


    
}