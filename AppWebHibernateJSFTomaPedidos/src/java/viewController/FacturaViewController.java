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
import org.primefaces.event.RowEditEvent;
import sessionController.UsuarioSessionController;
import util.MessagesUtil;
import util.SumaFactura;

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
    private Double subtotal = 0.0;

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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    //Constructor
    public FacturaViewController() {
        //Instancia DAO
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        //Listar facturas pendientes
        pendientes = facturaDAO.findPendiente();
    }

    //Listener on submit
    public void onSubmit() {
        subtotal = 0.0;
        SumaFactura sumaFactura = new SumaFactura();
        detalles.forEach((d) -> {
            subtotal = subtotal + sumaFactura.suma(d.getMenu().getMenuValor().doubleValue(), d.getDetallefacturaCantidad());
        });
    }
    
    //Actualizar la lista de detalles de la cabecera seleccionada
    public void updateSelected() {
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        detalles = detallefacturaDAO.findDetalle(cabeceraSelected);
        subtotal = 0.0;
        detalles.forEach((d) -> {
            subtotal = subtotal + (d.getMenu().getMenuValor().doubleValue() * d.getDetallefacturaCantidad());
        });
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

    //Editar factura
    public void editFactura(RowEditEvent event) {
        //Instancias
        SumaFactura sumaFactura = new SumaFactura();
        MessagesUtil message = new MessagesUtil();
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        try {
            //Variables
            Object newObject = event.getObject();
            //Setar el detalle con el objeto seleccionado
            detalleSelected = (Detallefactura) newObject;
            //Validar si se modifica la orden
            if (detallefacturaDAO.update(detalleSelected)) {
                subtotal = 0.0;
                message.infoMessage("La cantidad del pedido " + detalleSelected.getMenu().getMenuNombre() + " ha sido modificado satisfactoriamente!");
                detalles.forEach((d) -> {
                    subtotal = subtotal + (d.getMenu().getMenuValor().doubleValue() * d.getDetallefacturaCantidad());
                });
            } else {
                message.errorMessage("No se ha podido modificar el pedido!");
            }
        } catch (Exception e) {
            message.fatalMessage(e.toString());
        }
    }
}
