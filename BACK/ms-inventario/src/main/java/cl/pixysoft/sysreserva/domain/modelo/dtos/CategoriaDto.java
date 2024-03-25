package cl.pixysoft.sysreserva.domain.modelo.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.Categoria}
 */
@Schema(
        description = "Datos de la entidad Categoria de productos",
        name = "Categoria"
)
@RegisterForReflection
public class CategoriaDto{

    @Schema(required = true, implementation = Integer.class, example = "56")
    @JsonbProperty("id")
    public Integer id;
    @Schema(required = true, implementation = String.class, example = "Alimento")
    @JsonbProperty("nombre")
    public String nombre;
    @Schema(required = true, implementation = Boolean.class, example = "true")
    @JsonbProperty("estado")
    public Boolean estado;
    @Schema(required = true, implementation = Integer.class, example = "1")
    @JsonbProperty("sistema")
    public Integer sistema;

}