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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Empresa generated by hbm2java
 */
@Entity
@Table(name = "empresa",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Empresa implements java.io.Serializable {
    
    private int empresaId;
    private String empresaRazonSocial;
    private String empresaRuc;
    private Date empresaFechaCreacion;
    private Date empresaFechaModificacion;
    private Character empresaEstado;
    private Set clientes = new HashSet(0);
    private Set menus = new HashSet(0);
    private Set locals = new HashSet(0);
    
    public Empresa() {
    }
    
    public Empresa(int empresaId) {
        this.empresaId = empresaId;
    }
    
    public Empresa(int empresaId, String empresaRazonSocial, String empresaRuc, Date empresaFechaCreacion, Date empresaFechaModificacion, Character empresaEstado, Set clientes, Set menus, Set locals) {
        this.empresaId = empresaId;
        this.empresaRazonSocial = empresaRazonSocial;
        this.empresaRuc = empresaRuc;
        this.empresaFechaCreacion = empresaFechaCreacion;
        this.empresaFechaModificacion = empresaFechaModificacion;
        this.empresaEstado = empresaEstado;
        this.clientes = clientes;
        this.menus = menus;
        this.locals = locals;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresaId", unique = true, insertable = false, updatable = false)
    public int getEmpresaId() {
        return this.empresaId;
    }
    
    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }
    
    @Column(name = "empresaRazonSocial", length = 250)
    public String getEmpresaRazonSocial() {
        return this.empresaRazonSocial;
    }
    
    public void setEmpresaRazonSocial(String empresaRazonSocial) {
        this.empresaRazonSocial = empresaRazonSocial;
    }
    
    @Column(name = "empresaRuc", length = 15, unique = true)
    public String getEmpresaRuc() {
        return this.empresaRuc;
    }
    
    public void setEmpresaRuc(String empresaRuc) {
        this.empresaRuc = empresaRuc;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "empresaFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getEmpresaFechaCreacion() {
        return this.empresaFechaCreacion;
    }
    
    public void setEmpresaFechaCreacion(Date empresaFechaCreacion) {
        this.empresaFechaCreacion = empresaFechaCreacion;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "empresaFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getEmpresaFechaModificacion() {
        return this.empresaFechaModificacion;
    }
    
    public void setEmpresaFechaModificacion(Date empresaFechaModificacion) {
        this.empresaFechaModificacion = empresaFechaModificacion;
    }
    
    @Column(name = "empresaEstado", length = 1, insertable = false)
    public Character getEmpresaEstado() {
        return this.empresaEstado;
    }
    
    public void setEmpresaEstado(Character empresaEstado) {
        this.empresaEstado = empresaEstado;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    public Set getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set clientes) {
        this.clientes = clientes;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    public Set getMenus() {
        return this.menus;
    }
    
    public void setMenus(Set menus) {
        this.menus = menus;
    }
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "empresa")
    public Set getLocals() {
        return this.locals;
    }
    
    public void setLocals(Set locals) {
        this.locals = locals;
    }
    
    @PrePersist
    public void prepersist() {
        if (empresaEstado == null) {
            empresaEstado = 'A';
        }
        if (empresaFechaCreacion == null) {
            Date date = new Date();
            empresaFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
