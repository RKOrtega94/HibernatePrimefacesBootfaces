package model;
// Generated 27-dic-2018 18:11:38 by Hibernate Tools 4.3.1

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Inventario generated by hbm2java
 */
@Entity
@Table(name = "inventario",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Inventario implements java.io.Serializable {

    private int inventarioId;
    private Local local;
    private Producto producto;
    private Double inventarioCantidad;
    private Character inventarioMovimiento;
    private Date inventarioFechaCreacion;
    private Date inventarioFechaModificacion;
    private Character inventarioEstado;

    public Inventario() {
    }

    public Inventario(int inventarioId, Local local, Producto producto) {
        this.inventarioId = inventarioId;
        this.local = local;
        this.producto = producto;
    }

    public Inventario(int inventarioId, Local local, Producto producto, Double inventarioCantidad, Character inventarioMovimiento, Date inventarioFechaCreacion, Date inventarioFechaModificacion, Character inventarioEstado) {
        this.inventarioId = inventarioId;
        this.local = local;
        this.producto = producto;
        this.inventarioCantidad = inventarioCantidad;
        this.inventarioMovimiento = inventarioMovimiento;
        this.inventarioFechaCreacion = inventarioFechaCreacion;
        this.inventarioFechaModificacion = inventarioFechaModificacion;
        this.inventarioEstado = inventarioEstado;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventarioId", unique = true, insertable = false, updatable = false)
    public int getInventarioId() {
        return this.inventarioId;
    }

    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localId", nullable = false)
    public Local getLocal() {
        return this.local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productoId", nullable = false)
    public Producto getProducto() {
        return this.producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name = "inventarioCantidad", precision = 53, scale = 0)
    public Double getInventarioCantidad() {
        return this.inventarioCantidad;
    }

    public void setInventarioCantidad(Double inventarioCantidad) {
        this.inventarioCantidad = inventarioCantidad;
    }

    @Column(name = "inventarioMovimiento", length = 1)
    public Character getInventarioMovimiento() {
        return this.inventarioMovimiento;
    }

    public void setInventarioMovimiento(Character inventarioMovimiento) {
        this.inventarioMovimiento = inventarioMovimiento;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inventarioFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getInventarioFechaCreacion() {
        return this.inventarioFechaCreacion;
    }

    public void setInventarioFechaCreacion(Date inventarioFechaCreacion) {
        this.inventarioFechaCreacion = inventarioFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "inventarioFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getInventarioFechaModificacion() {
        return this.inventarioFechaModificacion;
    }

    public void setInventarioFechaModificacion(Date inventarioFechaModificacion) {
        this.inventarioFechaModificacion = inventarioFechaModificacion;
    }

    @Column(name = "inventarioEstado", length = 1, insertable = false)
    public Character getInventarioEstado() {
        return this.inventarioEstado;
    }

    public void setInventarioEstado(Character inventarioEstado) {
        this.inventarioEstado = inventarioEstado;
    }

    @PrePersist
    public void prepersis() {
        if (inventarioEstado == null) {
            inventarioEstado = 'A';
        }
        if (inventarioFechaCreacion == null) {
            Date date = new Date();
            inventarioFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
