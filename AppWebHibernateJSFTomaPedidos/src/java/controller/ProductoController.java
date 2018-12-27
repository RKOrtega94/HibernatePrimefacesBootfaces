/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductoDAO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Producto;

/**
 *
 * @author RKOrtega
 */
@Named(value = "productoController")
@ApplicationScoped
public class ProductoController {

    private Producto producto;
    private Producto selected;
    private List<Producto> productos;

    @PostConstruct
    public void init() {
        producto = new Producto();
        selected = new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Producto getSelected() {
        return selected;
    }

    public void setSelected(Producto selected) {
        this.selected = selected;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * Creates a new instance of ProductoController
     */
    public ProductoController() {
        ProductoDAO productoDAO = new ProductoDAO();
        productos = productoDAO.findAll();
    }

    public void sabe() {
        ProductoDAO productoDAO = new ProductoDAO();
        if (productoDAO.save(producto)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            productos = productoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        ProductoDAO productoDAO = new ProductoDAO();
        if (productoDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            productos = productoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        ProductoDAO productoDAO = new ProductoDAO();
        if (productoDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            productos = productoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!\nNo se puede eliminar");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
