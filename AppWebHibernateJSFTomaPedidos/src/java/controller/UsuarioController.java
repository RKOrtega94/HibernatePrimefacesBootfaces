/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpleadoDAO;
import dao.PerfilDAO;
import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Empleado;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author RKOrtega
 */
@Named(value = "usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario selected;
    private List<Usuario> usuarios;
    private String claveTemporal;
    private Empleado empleado;
    private int empleadoId;
    private Perfil perfil;
    private int perfilId;

    public Usuario getSelected() {
        return selected;
    }

    public void setSelected(Usuario selected) {
        this.selected = selected;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String getClaveTemporal() {
        return claveTemporal;
    }

    public void setClaveTemporal(String claveTemporal) {
        this.claveTemporal = claveTemporal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    @PostConstruct
    public void init() {
        selected = new Usuario();
    }

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarios = usuarioDAO.findAll();
    }

    public void restablecer() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        int numeroRandom = (int) (Math.random() * 10000000) + 1;
        claveTemporal = Integer.toString(numeroRandom);
        selected.setUsuarioClave(claveTemporal);
        selected.setUsuarioEstado('A');
        if (usuarioDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            usuarios = usuarioDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
