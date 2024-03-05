package cl.pixysoft.sysreserva.domain.modelo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "clientes", schema = "customers")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente", nullable = false)
    private int id;

    @NotNull
    @Column(name = "nombres", nullable = false, length = Integer.MAX_VALUE)
    private String nombres;

    @NotNull
    @Column(name = "identificacion", nullable = false)
    private String identificacion;

    @NotNull
    @Column(name = "tipo_identificacion", nullable = false, length = Integer.MAX_VALUE)
    private char tipoIdentificacion;

    @Column(name = "direccion", length = Integer.MAX_VALUE)
    private String direccion;

    @Column(name = "telefono", length = Integer.MAX_VALUE)
    private String telefono;

    @Column(name = "correo", length = Integer.MAX_VALUE)
    private String correo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public char getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(char tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}