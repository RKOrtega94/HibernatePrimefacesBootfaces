package model;
// Generated 11-dic-2018 10:09:37 by Hibernate Tools 4.3.1

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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Cabecerafactura generated by hbm2java
 */
@Entity
@Table(name = "cabecerafactura",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Cabecerafactura implements java.io.Serializable {

    private int cabecerafacturaId;
    private Cliente cliente;
    private Usuario usuario;
    private Double cabecerafacturaValor;
    private Date cabecerafacturaFechaCreacion;
    private Date cabecerafacturaFechaModificacion;
    private Character cabecerafacturaEstado;
    private Set detallefacturas = new HashSet(0);

    public Cabecerafactura() {
    }

    public Cabecerafactura(int cabecerafacturaId, Cliente cliente, Usuario usuario) {
        this.cabecerafacturaId = cabecerafacturaId;
        this.cliente = cliente;
        this.usuario = usuario;
    }

    public Cabecerafactura(int cabecerafacturaId, Cliente cliente, Usuario usuario, Double cabecerafacturaValor, Date cabecerafacturaFechaCreacion, Date cabecerafacturaFechaModificacion, Character cabecerafacturaEstado, Set detallefacturas) {
        this.cabecerafacturaId = cabecerafacturaId;
        this.cliente = cliente;
        this.usuario = usuario;
        this.cabecerafacturaValor = cabecerafacturaValor;
        this.cabecerafacturaFechaCreacion = cabecerafacturaFechaCreacion;
        this.cabecerafacturaFechaModificacion = cabecerafacturaFechaModificacion;
        this.cabecerafacturaEstado = cabecerafacturaEstado;
        this.detallefacturas = detallefacturas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cabecerafacturaId", unique = true, insertable = false, updatable = false)
    public int getCabecerafacturaId() {
        return this.cabecerafacturaId;
    }

    public void setCabecerafacturaId(int cabecerafacturaId) {
        this.cabecerafacturaId = cabecerafacturaId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteId", nullable = false)
    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuarioId", nullable = false)
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "cabecerafacturaValor", precision = 53, scale = 0)
    public Double getCabecerafacturaValor() {
        return this.cabecerafacturaValor;
    }

    public void setCabecerafacturaValor(Double cabecerafacturaValor) {
        this.cabecerafacturaValor = cabecerafacturaValor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cabecerafacturaFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getCabecerafacturaFechaCreacion() {
        return this.cabecerafacturaFechaCreacion;
    }

    public void setCabecerafacturaFechaCreacion(Date cabecerafacturaFechaCreacion) {
        this.cabecerafacturaFechaCreacion = cabecerafacturaFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cabecerafacturaFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getCabecerafacturaFechaModificacion() {
        return this.cabecerafacturaFechaModificacion;
    }

    public void setCabecerafacturaFechaModificacion(Date cabecerafacturaFechaModificacion) {
        this.cabecerafacturaFechaModificacion = cabecerafacturaFechaModificacion;
    }

    @Column(name = "cabecerafacturaEstado", length = 1, insertable = false)
    public Character getCabecerafacturaEstado() {
        return this.cabecerafacturaEstado;
    }

    public void setCabecerafacturaEstado(Character cabecerafacturaEstado) {
        this.cabecerafacturaEstado = cabecerafacturaEstado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cabecerafactura")
    public Set getDetallefacturas() {
        return this.detallefacturas;
    }

    public void setDetallefacturas(Set detallefacturas) {
        this.detallefacturas = detallefacturas;
    }

}
