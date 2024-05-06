package cl.pixysoft.sysreserva.domain.modelo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    @NotNull
    @Column(name = "ruc", nullable = false, length = Integer.MAX_VALUE)
    private String ruc;

    @NotNull
    @Column(name = "email", nullable = false, length = Integer.MAX_VALUE)
    private String email;

    @NotNull
    @Column(name = "direccion", nullable = false, length = Integer.MAX_VALUE)
    private String direccion;

    @NotNull
    @Column(name = "telefono", nullable = false, length = Integer.MAX_VALUE)
    private String telefono;

    @NotNull
    @Column(name = "rep_legal", nullable = false, length = Integer.MAX_VALUE)
    private String repLegal;

    @NotNull
    @Column(name = "firma", nullable = false, length = Integer.MAX_VALUE)
    private String firma;

    @Column(name = "logo", length = Integer.MAX_VALUE)
    private String logo;

    @NotNull
    @Column(name = "estado", nullable = false)
    private Boolean estado = false;

    @NotNull
    @Column(name = "contabilidad", nullable = false)
    private Boolean contabilidad = false;

    @NotNull
    @Column(name = "pass_firma", nullable = false, length = Integer.MAX_VALUE)
    private String passFirma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRepLegal() {
        return repLegal;
    }

    public void setRepLegal(String repLegal) {
        this.repLegal = repLegal;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getContabilidad() {
        return contabilidad;
    }

    public void setContabilidad(Boolean contabilidad) {
        this.contabilidad = contabilidad;
    }

    public String getPassFirma() {
        return passFirma;
    }

    public void setPassFirma(String passFirma) {
        this.passFirma = passFirma;
    }

}