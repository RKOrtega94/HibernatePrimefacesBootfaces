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
 * Perfil generated by hbm2java
 */
@Entity
@Table(name = "perfil",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Perfil implements java.io.Serializable {

    private int perfilId;
    private String perfilDescripcion;
    private Date perfilFechaCreacion;
    private Date perfilFechaModificacion;
    private Character perfilEstado;
    private Set opcionXperfils = new HashSet(0);
    private Set usuarios = new HashSet(0);

    public Perfil() {
    }

    public Perfil(int perfilId) {
        this.perfilId = perfilId;
    }

    public Perfil(int perfilId, String perfilDescripcion, Date perfilFechaCreacion, Date perfilFechaModificacion, Character perfilEstado, Set opcionXperfils, Set usuarios) {
        this.perfilId = perfilId;
        this.perfilDescripcion = perfilDescripcion;
        this.perfilFechaCreacion = perfilFechaCreacion;
        this.perfilFechaModificacion = perfilFechaModificacion;
        this.perfilEstado = perfilEstado;
        this.opcionXperfils = opcionXperfils;
        this.usuarios = usuarios;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfilId", unique = true, nullable = false)
    public int getPerfilId() {
        return this.perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    @Column(name = "perfilDescripcion", length = 100)
    public String getPerfilDescripcion() {
        return this.perfilDescripcion;
    }

    public void setPerfilDescripcion(String perfilDescripcion) {
        this.perfilDescripcion = perfilDescripcion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "perfilFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getPerfilFechaCreacion() {
        return this.perfilFechaCreacion;
    }

    public void setPerfilFechaCreacion(Date perfilFechaCreacion) {
        this.perfilFechaCreacion = perfilFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "perfilFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getPerfilFechaModificacion() {
        return this.perfilFechaModificacion;
    }

    public void setPerfilFechaModificacion(Date perfilFechaModificacion) {
        this.perfilFechaModificacion = perfilFechaModificacion;
    }

    @Column(name = "perfilEstado", length = 1, insertable = false)
    public Character getPerfilEstado() {
        return this.perfilEstado;
    }

    public void setPerfilEstado(Character perfilEstado) {
        this.perfilEstado = perfilEstado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
    public Set getOpcionXperfils() {
        return this.opcionXperfils;
    }

    public void setOpcionXperfils(Set opcionXperfils) {
        this.opcionXperfils = opcionXperfils;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "perfil")
    public Set getUsuarios() {
        return this.usuarios;
    }

    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

    @PrePersist
    public void prepersist() {
        if (perfilEstado == null) {
            perfilEstado = 'A';
        }
        if (perfilFechaCreacion == null) {
            Date date = new Date();
            perfilFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
