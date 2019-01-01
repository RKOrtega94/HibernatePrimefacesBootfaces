/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CargoDAO;
import dao.EmpleadoDAO;
import dao.LocalDAO;
import dao.PerfilDAO;
import dao.UsuarioDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Cargo;
import model.Empleado;
import model.Local;
import model.Perfil;
import model.Usuario;

/**
 *
 * @author RKOrtega
 */
@Named(value = "empleadoController")
@ViewScoped
public class EmpleadoController implements Serializable{

    private Empleado empleado;
    private Empleado selected;
    private List<Empleado> empleados;
    private Local local;
    private int localId;
    private Cargo cargo;
    private int cargoId;
    private Usuario usuario;
    private List<Usuario> usuarios;
    private int perfilId;
    private Perfil perfil;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Empleado getSelected() {
        return selected;
    }

    public void setSelected(Empleado selected) {
        this.selected = selected;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
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

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(int perfilId) {
        this.perfilId = perfilId;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        selected = new Empleado();
        cargo = new Cargo();
        usuario = new Usuario();
        perfil = new Perfil();
    }

    /**
     * Creates a new instance of EmpleadoController
     */
    public EmpleadoController() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        empleados = empleadoDAO.findAll();
        usuarios = usuarioDAO.findAll();
    }

    public void save() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        PerfilDAO perfilDAO = new PerfilDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CargoDAO cargoDAO = new CargoDAO();
        LocalDAO localDAO = new LocalDAO();
        local = localDAO.findById(localId);
        cargo = cargoDAO.findById(cargoId);
        perfil = perfilDAO.findById(perfilId);
        if (cargo != null && local != null) {
            empleado.setLocal(local);
            empleado.setCargo(cargo);
            if (empleadoDAO.findByDni(empleado.getEmpleadoNumeroDocumento()) == null) {
                if (empleadoDAO.save(empleado)) {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                    usuario.setEmpleado(empleado);
                    usuario.setUsuarioNombre(empleado.getEmpleadoNumeroDocumento());
                    usuario.setUsuarioClave(empleado.getEmpleadoNumeroDocumento());
                    usuario.setPerfil(perfil);
                    if (usuarioDAO.save(usuario)) {
                        massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Usuario Registrado!");
                        FacesContext.getCurrentInstance().addMessage(null, massage);
                    } else {
                        massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error en la creacion del usuario");
                        FacesContext.getCurrentInstance().addMessage(null, massage);
                    }
                    empleados = empleadoDAO.findAll();
                } else {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                }
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "El numero de cédula ya existe!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        CargoDAO cargoDAO = new CargoDAO();
        LocalDAO localDAO = new LocalDAO();
        local = localDAO.findById(localId);
        cargo = cargoDAO.findById(cargoId);
        if (cargo != null && local != null) {
            selected.setLocal(local);
            selected.setCargo(cargo);
            if (empleadoDAO.update(selected)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                empleados = empleadoDAO.findAll();
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        if (empleadoDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            empleados = empleadoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
