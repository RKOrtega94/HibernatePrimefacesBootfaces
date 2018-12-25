/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpresaDAO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Empresa;

/**
 *
 * @author RKOrtega
 */
@Named(value = "empresaController")
@ApplicationScoped
public class EmpresaController implements Serializable {

    private Empresa empresa;
    private Empresa selected;
    private List<Empresa> empresas;

    @PostConstruct
    public void init() {
        empresa = new Empresa();
        selected = new Empresa();
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public Empresa getSelected() {
        return selected;
    }

    public void setSelected(Empresa selected) {
        this.selected = selected;
    }

    /**
     * Creates a new instance of EmpresaController
     */
    public EmpresaController() {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresas = empresaDAO.findAll();
    }

    public void save() {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        if (empresaDAO.save(empresa)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            empresas = empresaDAO.findAll();
            empresa = null;
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        if (empresaDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            empresas = empresaDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        if (empresaDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            empresas = empresaDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
