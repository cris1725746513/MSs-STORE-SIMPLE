package cl.pixysoft.sysreserva.domain.modelo.dtos;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Schema(
        description = "(Descripción del esquema de datos) payload PAIS",
        name = "PAIS (nombre del esquema)"
)
@RegisterForReflection
public class Pais_DTO {
    @Schema(required = true, implementation = long.class, example = "56")
    @JsonbProperty("codigo_pais")
    public long codigoPais;
    @Schema(required = false, implementation = String.class, example = "56", nullable = true)
    @JsonbProperty("codigo_nemotecnico")
    public String codigoNemotecnico;
    @Schema(required = true, implementation = String.class, example = "CHILE", nullable = false)
    @JsonbProperty("nombre")
    public String nombre;
    @Schema(required = false, implementation = String.class, example = "56", nullable = true, maxLength = 16)
    @JsonbProperty("codigo_telefonico")
    public String codigoTelefonico;
    @Schema(required = true, implementation = int.class, example = "0", nullable = false)
    @JsonbProperty("sys_id")
    public int sysId;
    @Schema(required = true, implementation = String.class, example = "INIT", nullable = false)
    @JsonbProperty("usuario")
    public String sysUsuario;
    @Schema(required = true, implementation = String.class, example = "INIT", nullable = false)
    @JsonbProperty("aplicacion")
    public String sysAplicacion;
    @Schema(required = true, implementation = String.class, example = "INSERT", nullable = false)
    @JsonbProperty("accion")
    public String sysAccion;
    @Schema(required = true, implementation = int.class, example = "0", nullable = false)
    @JsonbProperty("version")
    public int sysVersion;
    @Schema(implementation = Date.class, example = "2023-06-07 15:45:29.791", required = true)
    //@JsonbDateFormat(value = "yyyy-MM-dd")
    @JsonbProperty("fecha_modificacion")
    public LocalDateTime sysFechaMoficiacion;
    @Schema(required = false, implementation = String.class, example = "PAIS", nullable = false)
    @JsonbProperty("tipo")
    public String sysTipo;
    @Schema(required = true, implementation = int.class, example = "0", nullable = false)
    @JsonbProperty("eliminado")
    public int sysEliminado;
    @Schema(required = false, implementation = String.class, example = "Sin Clasificar", nullable = true)
    @JsonbProperty("nombre_crm")
    public String crmNombre;
    @Schema(required = false, implementation = String.class, example = "CHILE", nullable = true)
    @JsonbProperty("nombre_ct")
    public String ctNombre;

    @Schema(required = false, implementation = String.class, example = "Texto libre de ejemplo", nullable = true)
    @JsonbProperty("texto_propio_ejemplo_DTO")
    public String textoAgregadoDTO;
}
