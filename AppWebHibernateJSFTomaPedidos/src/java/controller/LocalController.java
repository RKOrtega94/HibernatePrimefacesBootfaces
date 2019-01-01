/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpresaDAO;
import dao.LocalDAO;
import java.io.Serializable;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Empresa;
import model.Local;

/**
 *
 * @author RKOrtega
 */
@Named(value = "localController")
@ViewScoped
public class LocalController implements Serializable{

    private Local local;
    private Local selected;
    private List<Local> locales;
    private Empresa empresa;
    private int empresaId;

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public Local getSelected() {
        return selected;
    }

    public void setSelected(Local selected) {
        this.selected = selected;
    }

    public List<Local> getLocales() {
        return locales;
    }

    public void setLocales(List<Local> locales) {
        this.locales = locales;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    @PostConstruct
    public void init() {
        local = new Local();
        selected = new Local();
    }

    /**
     * Creates a new instance of LocalController
     */
    public LocalController() {
        LocalDAO localDAO = new LocalDAO();
        locales = localDAO.findAll();
    }

    public void save() {
        LocalDAO localDAO = new LocalDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.findById(empresaId);
        if (empresa != null) {
            local.setEmpresa(empresa);
            if (localDAO.save(local)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                locales = localDAO.findAll();
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
        LocalDAO localDAO = new LocalDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.findById(empresaId);
        if (empresa != null) {
            selected.setEmpresa(empresa);
            if (localDAO.update(selected)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                locales = localDAO.findAll();
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
        LocalDAO localDAO = new LocalDAO();
        if (localDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            locales = localDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!\nNo se puede eliminar");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
