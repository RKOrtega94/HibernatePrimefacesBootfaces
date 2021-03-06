package model;
// Generated 27-dic-2018 18:11:38 by Hibernate Tools 4.3.1

import java.math.BigDecimal;
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
 * Menu generated by hbm2java
 */
@Entity
@Table(name = "menu",
        schema = "dbo",
        catalog = "restoitsco"
)
public class Menu implements java.io.Serializable {

    private int menuId;
    private Empresa empresa;
    private String menuNombre;
    private String menuDescriocion;
    private Character menuTipo;
    private BigDecimal menuValor;
    private Date menuFechaCreacion;
    private Date menuFechaModificacion;
    private Character menuEstado;
    private String menuFoto;
    private Set detallefacturas = new HashSet(0);

    public Menu() {
    }

    public Menu(int menuId, Empresa empresa) {
        this.menuId = menuId;
        this.empresa = empresa;
    }

    public Menu(int menuId, Empresa empresa, String menuNombre, String menuDescriocion, Character menuTipo, BigDecimal menuValor, Date menuFechaCreacion, Date menuFechaModificacion, Character menuEstado, String menuFoto, Set detallefacturas) {
        this.menuId = menuId;
        this.empresa = empresa;
        this.menuNombre = menuNombre;
        this.menuDescriocion = menuDescriocion;
        this.menuTipo = menuTipo;
        this.menuValor = menuValor;
        this.menuFechaCreacion = menuFechaCreacion;
        this.menuFechaModificacion = menuFechaModificacion;
        this.menuEstado = menuEstado;
        this.menuFoto = menuFoto;
        this.detallefacturas = detallefacturas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menuId", unique = true, insertable = false, updatable = false)
    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresaId", nullable = false)
    public Empresa getEmpresa() {
        return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Column(name = "menuNombre", length = 50)
    public String getMenuNombre() {
        return this.menuNombre;
    }

    public void setMenuNombre(String menuNombre) {
        this.menuNombre = menuNombre;
    }

    @Column(name = "menuDescriocion", length = 250)
    public String getMenuDescriocion() {
        return this.menuDescriocion;
    }

    public void setMenuDescriocion(String menuDescriocion) {
        this.menuDescriocion = menuDescriocion;
    }

    @Column(name = "menuTipo", length = 1)
    public Character getMenuTipo() {
        return this.menuTipo;
    }

    public void setMenuTipo(Character menuTipo) {
        this.menuTipo = menuTipo;
    }

    @Column(name = "menuValor", scale = 4)
    public BigDecimal getMenuValor() {
        return this.menuValor;
    }

    public void setMenuValor(BigDecimal menuValor) {
        this.menuValor = menuValor;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "menuFechaCreacion", length = 23, insertable = false, updatable = false)
    public Date getMenuFechaCreacion() {
        return this.menuFechaCreacion;
    }

    public void setMenuFechaCreacion(Date menuFechaCreacion) {
        this.menuFechaCreacion = menuFechaCreacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "menuFechaModificacion", length = 23, insertable = false, updatable = false)
    public Date getMenuFechaModificacion() {
        return this.menuFechaModificacion;
    }

    public void setMenuFechaModificacion(Date menuFechaModificacion) {
        this.menuFechaModificacion = menuFechaModificacion;
    }

    @Column(name = "menuEstado", length = 1, insertable = false)
    public Character getMenuEstado() {
        return this.menuEstado;
    }

    public void setMenuEstado(Character menuEstado) {
        this.menuEstado = menuEstado;
    }

    @Column(name = "menuFoto", length = 500)
    public String getMenuFoto() {
        return this.menuFoto;
    }

    public void setMenuFoto(String menuFoto) {
        this.menuFoto = menuFoto;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
    public Set getDetallefacturas() {
        return this.detallefacturas;
    }

    public void setDetallefacturas(Set detallefacturas) {
        this.detallefacturas = detallefacturas;
    }

    @PrePersist
    public void prepersist() {
        if (menuEstado == null) {
            menuEstado = 'A';
        }
        if (menuFechaCreacion == null) {
            Date date = new Date();
            menuFechaCreacion = (new Timestamp(date.getTime()));
        }
    }
}
