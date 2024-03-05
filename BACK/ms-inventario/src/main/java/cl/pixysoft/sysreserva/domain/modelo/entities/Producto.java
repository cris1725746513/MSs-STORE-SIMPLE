package cl.pixysoft.sysreserva.domain.modelo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "productos", schema = "inventario")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "nombre", nullable = false, length = Integer.MAX_VALUE)
    private String nombre;

    @NotNull
    @Column(name = "detalle", nullable = false, length = Integer.MAX_VALUE)
    private String detalle;

    @NotNull
    @Column(name = "pvp", nullable = false)
    private Double pvp;

    @NotNull
    @Column(name = "iva", nullable = false)
    private Boolean iva = false;

    @NotNull
    @Column(name = "existente", nullable = false)
    private Long existente;

    @NotNull
    @Column(name = "codigo", nullable = false, length = Integer.MAX_VALUE)
    private String codigo;

    @NotNull
    @Column(name = "proveedor", nullable = false, length = Integer.MAX_VALUE)
    private String proveedor;

    @NotNull
    @Column(name = "costo_proveedor", nullable = false)
    private Double costoProveedor;

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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public Boolean getIva() {
        return iva;
    }

    public void setIva(Boolean iva) {
        this.iva = iva;
    }

    public Long getExistente() {
        return existente;
    }

    public void setExistente(Long existente) {
        this.existente = existente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Double getCostoProveedor() {
        return costoProveedor;
    }

    public void setCostoProveedor(Double costoProveedor) {
        this.costoProveedor = costoProveedor;
    }

}