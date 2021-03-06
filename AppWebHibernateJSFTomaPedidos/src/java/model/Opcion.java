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
 * Opcion generated by hbm2java
 */
@Entity
@Table(name = "opcion",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Opcion implements java.io.Serializable {

    private int opcionId;
    private Opcion opcion;
    private String opcionDescripcion;
    private String opcionLink;
    private String opcionIcon;
    private Date opcionFechaCreacion;
    private Date opcionFechaModificacion;
    private Character opcionEstado;
    private Set opcions = new HashSet(0);
    private Set opcionXperfils = new HashSet(0);

    public Opcion() {
    }

    public Opcion(int opcionId) {
        this.opcionId = opcionId;
    }

    public Opcion(int opcionId, Opcion opcion, String opcionDescripcion, String opcionLink, String opcionIcon, Date opcionFechaCreacion, Date opcionFechaModificacion, Character opcionEstado, Set opcions, Set opcionXperfils) {
        this.opcionId = opcionId;
        this.opcion = opcion;
        this.opcionDescripcion = opcionDescripcion;
        this.opcionLink = opcionLink;
        this.opcionIcon = opcionIcon;
        this.opcionFechaCreacion = opcionFechaCreacion;
        this.opcionFechaModificacion = opcionFechaModificacion;
        this.opcionEstado = opcionEstado;
        this.opcions = opcions;
        this.opcionXperfils = opcionXperfils;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opcionId", unique = true, insertable = false, updatable = false)
    public int getOpcionId() {
        return this.opcionId;
    }

    public void setOpcionId(int opcionId) {
        this.opcionId = opcionId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcionOpcionId")
    public Opcion getOpcion() {
        return this.opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    @Column(name = "opcionDescripcion", length = 150)
    public String getOpcionDescripcion() {
        return this.opcionDescripcion;
    }

    public void setOpcionDescripcion(String opcionDescripcion) {
        this.opcionDescripcion = opcionDescripcion;
    }

    @Column(name = "opcionLink", length = 150)
    public String getOpcionLink() {
        return this.opcionLink;
    }

    public void setOpcionLink(String opcionLink) {
        this.opcionLink = opcionLink;
    }

    @Column(name = "opcionIcon", length = 50)
    public String getOpcionIcon() {
        return this.opcionIcon;
    }

    public void setOpcionIcon(String opcionIcon) {
        this.opcionIcon = opcionIcon;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opcionFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getOpcionFechaCreacion() {
        return this.opcionFechaCreacion;
    }

    public void setOpcionFechaCreacion(Date opcionFechaCreacion) {
        this.opcionFechaCreacion = opcionFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opcionFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getOpcionFechaModificacion() {
        return this.opcionFechaModificacion;
    }

    public void setOpcionFechaModificacion(Date opcionFechaModificacion) {
        this.opcionFechaModificacion = opcionFechaModificacion;
    }

    @Column(name = "opcionEstado", length = 1, insertable = false)
    public Character getOpcionEstado() {
        return this.opcionEstado;
    }

    public void setOpcionEstado(Character opcionEstado) {
        this.opcionEstado = opcionEstado;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "opcion")
    public Set getOpcions() {
        return this.opcions;
    }

    public void setOpcions(Set opcions) {
        this.opcions = opcions;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "opcion")
    public Set getOpcionXperfils() {
        return this.opcionXperfils;
    }

    public void setOpcionXperfils(Set opcionXperfils) {
        this.opcionXperfils = opcionXperfils;
    }

    @PrePersist
    public void prepersist() {
        if (opcionEstado == null) {
            opcionEstado = 'A';
        }
        if (opcionFechaCreacion == null) {
            Date date = new Date();
            opcionFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
