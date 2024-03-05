package cl.pixysoft.sysreserva.domain.modelo.entities;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="PAIS", schema = "PERSONAS")
public class Pais_Entity {

    @Id
    @Column(name = "CODIGO_PAIS")
    public long codigoPais;
    @Column(name = "CODIGO_NEMOTECNICO")
    public String codigoNemotecnico;
    @Column(name = "NOMBRE")
    public String nombre;
    @Column(name = "CODIGO_TELEFONICO")
    public String codigoTelefonico;

    @Column(name = "SYS_ID")
    public int sysId;
    @Column(name = "SYS_USUARIO")
    public String sysUsuario;
    @Column(name = "SYS_APLICACION")
    public String sysAplicacion;
    @Column(name = "SYS_ACCION")
    public String sysAccion;
    @Column(name = "SYS_VERSION")
    public int sysVersion;
    @Column(name = "SYS_FECHA_MODIFICACION")
    public LocalDateTime sysFechaMoficiacion;
    @Column(name = "SYS_TIPO")
    public String sysTipo;
    @Column(name = "SYS_ELIMINADO")
    public int sysEliminado;
    @Column(name = "CRM_NOMBRE")
    public String crmNombre;
    @Column(name = "CT_NOMBRE")
    public String ctNombre;

    public Pais_Entity(){}

    public Pais_Entity(long CODIGO_PAIS, String NOMBRE){
        this.codigoPais = CODIGO_PAIS;
        this.nombre = NOMBRE;
        this.sysId = 0;
        this.sysUsuario = "INIT";
        this.sysAplicacion = "INIT";
        this.sysAccion = "INSERT";
        this.sysVersion = 0;
        this.sysFechaMoficiacion = LocalDateTime.now();
        this.sysEliminado = 0;
    }

}
