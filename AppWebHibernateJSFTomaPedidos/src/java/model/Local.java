package model;
// Generated 27-dic-2018 18:11:38 by Hibernate Tools 4.3.1

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Local generated by hbm2java
 */
@Entity
@Table(name = "local",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Local implements java.io.Serializable {

    private int localId;
    private Empresa empresa;
    private String localNombre;
    private String localDireccion;
    private String localTelefono;
    private Date localFechaCreacion;
    private Date localFechaModificacion;
    private Character localEstado;
    private Set inventarios = new HashSet(0);
    private Set empleados = new HashSet(0);

    public Local() {
    }

    public Local(int localId, Empresa empresa) {
        this.localId = localId;
        this.empresa = empresa;
    }

    public Local(int localId, Empresa empresa, String localNombre, String localDireccion, String localTelefono, Date localFechaCreacion, Date localFechaModificacion, Character localEstado, Set inventarios, Set empleados) {
        this.localId = localId;
        this.empresa = empresa;
        this.localNombre = localNombre;
        this.localDireccion = localDireccion;
        this.localTelefono = localTelefono;
        this.localFechaCreacion = localFechaCreacion;
        this.localFechaModificacion = localFechaModificacion;
        this.localEstado = localEstado;
        this.inventarios = inventarios;
        this.empleados = empleados;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "localId", unique = true, insertable = false, updatable = false)
    public int getLocalId() {
        return this.localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresaId", nullable = false)
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Column(name = "localNombre", length = 200)
    public String getLocalNombre() {
        return this.localNombre;
    }

    public void setLocalNombre(String localNombre) {
        this.localNombre = localNombre;
    }

    @Column(name = "localDireccion", length = 200)
    public String getLocalDireccion() {
        return this.localDireccion;
    }

    public void setLocalDireccion(String localDireccion) {
        this.localDireccion = localDireccion;
    }

    @Column(name = "localTelefono", length = 50)
    public String getLocalTelefono() {
        return this.localTelefono;
    }

    public void setLocalTelefono(String localTelefono) {
        this.localTelefono = localTelefono;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "localFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getLocalFechaCreacion() {
        return this.localFechaCreacion;
    }

    public void setLocalFechaCreacion(Date localFechaCreacion) {
        this.localFechaCreacion = localFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "localFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getLocalFechaModificacion() {
        return this.localFechaModificacion;
    }

    public void setLocalFechaModificacion(Date localFechaModificacion) {
        this.localFechaModificacion = localFechaModificacion;
    }

    @Column(name = "localEstado", length = 1, insertable = false)
    public Character getLocalEstado() {
        return this.localEstado;
    }

    public void setLocalEstado(Character localEstado) {
        this.localEstado = localEstado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "local")
    public Set getInventarios() {
        return this.inventarios;
    }

    public void setInventarios(Set inventarios) {
        this.inventarios = inventarios;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "local")
    public Set getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(Set empleados) {
        this.empleados = empleados;
    }

    @PrePersist
    public void prepersist() {
        if (localEstado == null) {
            localEstado = 'A';
        }
        if (localFechaCreacion == null) {
            Date date = new Date();
            localFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
