package cl.pixysoft.sysreserva.domain.modelo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.OffsetTime;

@Entity
@Table(name = "documentos_electronicos")
public class DocumentosElectronico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "tipo_doc", nullable = false)
    private Integer tipoDoc;

    @NotNull
    @Column(name = "xml_ruta", nullable = false, length = Integer.MAX_VALUE)
    private String xmlRuta;

    @NotNull
    @Column(name = "clave_acceso", nullable = false, length = Integer.MAX_VALUE)
    private String claveAcceso;

    @NotNull
    @Column(name = "cliente", nullable = false)
    private Integer cliente;

    @Column(name = "autorizacion", length = Integer.MAX_VALUE)
    private String autorizacion;

    @Column(name = "estado_sri")
    private Integer estadoSri;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store")
    private Store store;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private OffsetTime fechaRegistro;

    @NotNull
    @Column(name = "fecha_update", nullable = false)
    private OffsetTime fechaUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(Integer tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getXmlRuta() {
        return xmlRuta;
    }

    public void setXmlRuta(String xmlRuta) {
        this.xmlRuta = xmlRuta;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getAutorizacion() {
        return autorizacion;
    }

    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public Integer getEstadoSri() {
        return estadoSri;
    }

    public void setEstadoSri(Integer estadoSri) {
        this.estadoSri = estadoSri;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public OffsetTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(OffsetTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public OffsetTime getFechaUpdate() {
        return fechaUpdate;
    }

    public void setFechaUpdate(OffsetTime fechaUpdate) {
        this.fechaUpdate = fechaUpdate;
    }

}