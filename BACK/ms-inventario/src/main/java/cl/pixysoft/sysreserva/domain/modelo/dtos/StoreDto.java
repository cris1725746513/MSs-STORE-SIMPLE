package cl.pixysoft.sysreserva.domain.modelo.dtos;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link cl.pixysoft.sysreserva.domain.modelo.entities.Store}
 */
public class StoreDto implements Serializable {
    private final Integer id;
    @NotNull
    private final String nombre;
    @NotNull
    private final String ruc;
    @NotNull
    private final String email;
    @NotNull
    private final String direccion;
    @NotNull
    private final String telefono;
    @NotNull
    private final String repLegal;
    @NotNull
    private final String firma;
    private final String logo;
    @NotNull
    private final Boolean estado;
    @NotNull
    private final Boolean contabilidad;
    @NotNull
    private final String passFirma;

    public StoreDto(Integer id, String nombre, String ruc, String email, String direccion, String telefono, String repLegal, String firma, String logo, Boolean estado, Boolean contabilidad, String passFirma) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.repLegal = repLegal;
        this.firma = firma;
        this.logo = logo;
        this.estado = estado;
        this.contabilidad = contabilidad;
        this.passFirma = passFirma;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public String getEmail() {
        return email;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRepLegal() {
        return repLegal;
    }

    public String getFirma() {
        return firma;
    }

    public String getLogo() {
        return logo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public Boolean getContabilidad() {
        return contabilidad;
    }

    public String getPassFirma() {
        return passFirma;
    }
}