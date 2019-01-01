/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OpcionDAO;
import java.io.Serializable;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Opcion;

/**
 *
 * @author RKOrtega
 */
@Named(value = "opcionController")
@ViewScoped
public class OpcionController implements Serializable{

    private Opcion opcion;
    private Opcion selected;
    private List<Opcion> opciones;
    private int nivel = 1;
    private int opcionId;
    private String visibility = "none";

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public Opcion getSelected() {
        return selected;
    }

    public void setSelected(Opcion selected) {
        this.selected = selected;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public int getOpcionId() {
        return opcionId;
    }

    public void setOpcionId(int opcionId) {
        this.opcionId = opcionId;
    }

    @PostConstruct
    public void init() {
        opcion = new Opcion();
        selected = new Opcion();
    }

    /**
     * Creates a new instance of OpcionController
     */
    public OpcionController() {
        OpcionDAO opcionDAO = new OpcionDAO();
        opciones = opcionDAO.findAll();
    }

    public void onChangeNivel() {
        if (nivel == 1) {
            visibility = "none";
        } else if (nivel == 2) {
            visibility = "compact";
        }
    }

    public void save() {
        OpcionDAO opcionDAO = new OpcionDAO();
        selected = opcionDAO.findById(opcionId);
        if (selected != null) {
            opcion.setOpcion(selected);
            if (opcionDAO.save(opcion)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                opciones = opcionDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            if (opcionDAO.save(opcion)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                opciones = opcionDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        }
        selected = null;
    }

    public void update() {
        OpcionDAO opcionDAO = new OpcionDAO();
        if (opcionDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            opciones = opcionDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        OpcionDAO opcionDAO = new OpcionDAO();
        if (opcionDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            opciones = opcionDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
