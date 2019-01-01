/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.InventarioDAO;
import dao.LocalDAO;
import dao.ProductoDAO;
import dao.TempInventarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Inventario;
import model.Local;
import model.Producto;
import model.TempInventario;

/**
 *
 * @author RKOrtega
 */
@Named(value = "inventarioController")
@ViewScoped
public class InventarioController implements Serializable{

    private Inventario inventario;
    private Inventario selected;
    private Producto producto;
    private int productoId;
    private Local local;
    private int localId;
    private List<Inventario> inventarios;
    private List<TempInventario> inventarioTotal;

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Inventario getSelected() {
        return selected;
    }

    public void setSelected(Inventario selected) {
        this.selected = selected;
    }

    public List<Inventario> getInventarios() {
        return inventarios;
    }

    public void setInventarios(List<Inventario> inventarios) {
        this.inventarios = inventarios;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public int getLocalId() {
        return localId;
    }

    public void setLocalId(int localId) {
        this.localId = localId;
    }

    public List<TempInventario> getInventarioTotal() {
        return inventarioTotal;
    }

    public void setInventarioTotal(List<TempInventario> inventarioTotal) {
        this.inventarioTotal = inventarioTotal;
    }

    @PostConstruct
    public void init() {
        inventario = new Inventario();
        selected = new Inventario();
        producto = new Producto();
        local = new Local();
    }

    /**
     * Creates a new instance of InventarioController
     */
    public InventarioController() {
        InventarioDAO inventarioDAO = new InventarioDAO();
        TempInventarioDAO tempInventarioDAO = new TempInventarioDAO();
        inventarios = inventarioDAO.findAll();
        inventarioTotal = tempInventarioDAO.findAll();
    }

    public void save() {
        InventarioDAO inventarioDAO = new InventarioDAO();
        TempInventarioDAO tempInventarioDAO = new TempInventarioDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        LocalDAO localDAO = new LocalDAO();
        producto = productoDAO.findById(productoId);
        local = localDAO.findById(localId);
        if (producto != null && local != null) {
            inventario.setLocal(local);
            inventario.setProducto(producto);
            if (inventarioDAO.save(inventario)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                inventarios = inventarioDAO.findAll();
                inventarioTotal = tempInventarioDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        InventarioDAO inventarioDAO = new InventarioDAO();
        TempInventarioDAO tempInventarioDAO = new TempInventarioDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        LocalDAO localDAO = new LocalDAO();
        producto = productoDAO.findById(productoId);
        local = localDAO.findById(localId);
        if (producto != null && local != null) {
            selected.setLocal(local);
            selected.setProducto(producto);
            if (inventarioDAO.update(selected)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                inventarios = inventarioDAO.findAll();
                inventarioTotal = tempInventarioDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        InventarioDAO inventarioDAO = new InventarioDAO();
        TempInventarioDAO tempInventarioDAO = new TempInventarioDAO();
        if (inventarioDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            inventarios = inventarioDAO.findAll();
            inventarioTotal = tempInventarioDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!\nNo se puede eliminar");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
