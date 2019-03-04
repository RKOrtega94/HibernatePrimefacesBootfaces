/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.CabeceraFacturaDAO;
import dao.DetallefacturaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.Cabecerafactura;
import model.Detallefactura;
import sessionController.UsuarioSessionController;

/**
 *
 * @author RKOrtega
 */
@ManagedBean(name = "facturaViewController")
@SessionScoped
public class FacturaViewController implements Serializable {

    //Declaración de variables
    private Cabecerafactura cabecera;
    private Cabecerafactura cabeceraSelected;
    private List<Cabecerafactura> cabeceras;
    private List<Cabecerafactura> pendientes;
    private Detallefactura detalle;
    private Detallefactura detalleSelected;
    private List<Detallefactura> detalles;

    //Instancia de la sesión
    @ManagedProperty(value = "#{usuarioSessionController}")
    private UsuarioSessionController sessionController;

    //Post-constructor
    @PostConstruct
    public void init() {
        cabecera = new Cabecerafactura();
        cabeceraSelected = new Cabecerafactura();
        detalle = new Detallefactura();
        detalleSelected = new Detallefactura();
    }

    //Getter y Setter
    public Cabecerafactura getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecerafactura cabecera) {
        this.cabecera = cabecera;
    }

    public Cabecerafactura getCabeceraSelected() {
        return cabeceraSelected;
    }

    public void setCabeceraSelected(Cabecerafactura cabeceraSelected) {
        this.cabeceraSelected = cabeceraSelected;
    }

    public List<Cabecerafactura> getCabeceras() {
        return cabeceras;
    }

    public void setCabeceras(List<Cabecerafactura> cabeceras) {
        this.cabeceras = cabeceras;
    }

    public List<Cabecerafactura> getPendientes() {
        return pendientes;
    }

    public void setPendientes(List<Cabecerafactura> pendientes) {
        this.pendientes = pendientes;
    }

    public Detallefactura getDetalle() {
        return detalle;
    }

    public void setDetalle(Detallefactura detalle) {
        this.detalle = detalle;
    }

    public Detallefactura getDetalleSelected() {
        return detalleSelected;
    }

    public void setDetalleSelected(Detallefactura detalleSelected) {
        this.detalleSelected = detalleSelected;
    }

    public List<Detallefactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<Detallefactura> detalles) {
        this.detalles = detalles;
    }

    public void setSessionController(UsuarioSessionController sessionController) {
        this.sessionController = sessionController;
    }

    //Constructor
    public FacturaViewController() {
        //Instancia DAO
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        //Listar facturas pendientes
        pendientes = facturaDAO.findPendiente();
    }

    //Actualizar la lista de detalles de la cabecera seleccionada
    public void updateSelected() {
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        detalles = detallefacturaDAO.findDetalle(cabeceraSelected);
    }

    //Redireccionar a la edición de la factura
    public void doRedirectToEdit() {
        //Instancias
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        //Llamar al método doRedirect de la sesión
        sessionController.doRedirect("/appmenu/app/factura/index.jsf");
        //Setear a la cabecera con la que ha sido seleccionada
        cabecera = cabeceraSelected;
        //Seleccionar los detalles que pertenecen a la cabecera
        detalles = detallefacturaDAO.findDetalle(cabecera);
    }
}
