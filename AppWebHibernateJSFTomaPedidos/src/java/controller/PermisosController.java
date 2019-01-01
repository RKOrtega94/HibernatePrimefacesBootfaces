/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.OpcionDAO;
import dao.OpcionPerfilDAO;
import dao.PerfilDAO;
import java.io.Serializable;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Opcion;
import model.OpcionXperfil;
import model.Perfil;

/**
 *
 * @author RKOrtega
 */
@Named(value = "permisosController")
@ViewScoped
public class PermisosController implements Serializable{

    private OpcionXperfil xperfil;
    private OpcionXperfil selected;
    private List<OpcionXperfil> xperfiles;
    private Opcion opcion;
    private Perfil perfil;
    private int opcionId;
    private int perfilId;

    public OpcionXperfil getXperfil() {
        return xperfil;
    }

    public void setXperfil(OpcionXperfil xperfil) {
        this.xperfil = xperfil;
    }

    public OpcionXperfil getSelected() {
        return selected;
    }

    public void setSelected(OpcionXperfil selected) {
        this.selected = selected;
    }

    public List<OpcionXperfil> getXperfiles() {
        return xperfiles;
    }

    public void setXperfiles(List<OpcionXperfil> xperfiles) {
        this.xperfiles = xperfiles;
    }

    public Opcion getOpcion() {
        return opcion;
    }

    public void setOpcion(Opcion opcion) {
        this.opcion = opcion;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getOpcionId() {
        return opcionId;
    }

    public void setOpcionId(int opcionId) {
        this.opcionId = opcionId;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    @PostConstruct
    public void init() {
        xperfil = new OpcionXperfil();
        selected = new OpcionXperfil();
        perfil = new Perfil();
        opcion = new Opcion();
    }

    /**
     * Creates a new instance of PermisosController
     */
    public PermisosController() {
        OpcionPerfilDAO opcionPerfilDAO = new OpcionPerfilDAO();
        xperfiles = opcionPerfilDAO.findAll();
    }

    public void save() {
        OpcionPerfilDAO opcionPerfilDAO = new OpcionPerfilDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        OpcionDAO opcionDAO = new OpcionDAO();
        perfil = perfilDAO.findById(perfilId);
        opcion = opcionDAO.findById(opcionId);
        if (perfil != null && opcion != null) {
            xperfil.setOpcion(opcion);
            xperfil.setPerfil(perfil);
            if (opcionPerfilDAO.save(xperfil)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                xperfiles = opcionPerfilDAO.findAll();
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
        OpcionPerfilDAO opcionPerfilDAO = new OpcionPerfilDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        OpcionDAO opcionDAO = new OpcionDAO();
        perfil = perfilDAO.findById(perfilId);
        opcion = opcionDAO.findById(opcionId);
        if (perfil != null && opcion != null) {
            selected.setOpcion(opcion);
            selected.setPerfil(perfil);
            if (opcionPerfilDAO.update(selected)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                xperfiles = opcionPerfilDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
    
    public void delete(){
        OpcionPerfilDAO opcionPerfilDAO = new OpcionPerfilDAO();
        if(opcionPerfilDAO.delete(selected)){
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            xperfiles = opcionPerfilDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!\nNo se puede eliminar");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
