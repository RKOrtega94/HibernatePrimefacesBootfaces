/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.PerfilDAO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Perfil;

/**
 *
 * @author RKOrtega
 */
@Named(value = "perfilController")
@SessionScoped
public class PerfilController implements Serializable {

    private Perfil perfil;
    private Perfil selected;
    private List<Perfil> perfiles;

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Perfil getSelected() {
        return selected;
    }

    public void setSelected(Perfil selected) {
        this.selected = selected;
    }

    public List<Perfil> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<Perfil> perfiles) {
        this.perfiles = perfiles;
    }

    @PostConstruct
    public void init() {
        perfil = new Perfil();
        selected = new Perfil();
    }

    /**
     * Creates a new instance of PerfilController
     */
    public PerfilController() {
        PerfilDAO perfilDAO = new PerfilDAO();
        perfiles = perfilDAO.findAll();
    }

    public void save() {
        PerfilDAO perfilDAO = new PerfilDAO();
        if (perfilDAO.save(perfil)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            perfiles = perfilDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        PerfilDAO perfilDAO = new PerfilDAO();
        if (perfilDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            perfiles = perfilDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        PerfilDAO perfilDAO = new PerfilDAO();
        if (perfilDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            perfiles = perfilDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
