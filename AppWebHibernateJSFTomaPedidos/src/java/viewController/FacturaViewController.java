/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.CabeceraFacturaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import model.Cabecerafactura;
import model.Detallefactura;

/**
 *
 * @author RKOrtega
 */
@ManagedBean(name = "facturaViewController")
@ViewScoped
public class FacturaViewController implements Serializable{

    private Cabecerafactura cabecera;
    private Cabecerafactura cabeceraSelected;
    private List<Cabecerafactura> cabeceras;
    private Detallefactura detalle;
    private Detallefactura detalleSelected;
    private List<Detallefactura> detalles;

    @PostConstruct
    public void init() {
        cabecera = new Cabecerafactura();
        cabeceraSelected = new Cabecerafactura();
        detalle = new Detallefactura();
        detalleSelected = new Detallefactura();
    }

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

    /**
     * Creates a new instance of FacturaViewController
     */
    public FacturaViewController() {
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        cabeceras = facturaDAO.findPendiente();
    }
    
    

}
