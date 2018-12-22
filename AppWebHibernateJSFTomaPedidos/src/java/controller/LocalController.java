/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import model.Empresa;
import model.Local;

/**
 *
 * @author RKOrtega
 */
@Named(value = "localController")
@SessionScoped
public class LocalController implements Serializable {

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

    /**
     * Creates a new instance of LocalController
     */
    public LocalController() {
    }

}
