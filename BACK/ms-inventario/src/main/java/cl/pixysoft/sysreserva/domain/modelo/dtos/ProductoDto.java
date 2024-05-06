package cl.pixysoft.sysreserva.domain.modelo.dtos;

import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.io.Serializable;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.Producto}
 */


@Schema(
        description = "Datos de la entidad Productos",
        name = "Productos"
)
@RegisterForReflection
public class ProductoDto  {

    @Schema(required = true, implementation = Integer.class, example = "56")
    @JsonbProperty("id")
    public Integer id;

    @Schema(required = true, implementation = String.class)
    @JsonbProperty("nombre")
    public String nombre;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("detalle")
    public String detalle;

    @Schema(required = true, implementation = Double.class)
    @JsonbProperty("pvp")
    public Double pvp;

    @Schema(required = true, implementation = Boolean.class)
    @JsonbProperty("iva")
    public Boolean iva;
    @Schema(required = true, implementation = Integer.class)
    @JsonbProperty("existente")
    public int existente;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("codigo")
    public String codigo;
    @Schema(required = true, implementation = String.class)
    @JsonbProperty("proveedor")
    public String proveedor;
    @Schema(required = true, implementation = Double.class)
    @JsonbProperty("costoProveedor")
    public Double costoProveedor;

    @Schema(required = true, implementation = CategoriaDto.class)
    @JsonbProperty("categoria")
    public CategoriaDto idCategoria;


    @Schema(implementation = String.class )
    @JsonbProperty("imagen")
    public  String imagen;


}