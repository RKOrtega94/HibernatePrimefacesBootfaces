/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CargoDAO;
import dao.EmpleadoDAO;
import dao.LocalDAO;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cargo;
import model.Empleado;
import model.Local;

/**
 *
 * @author RKOrtega
 */
@Named(value = "empleadoController")
@ApplicationScoped
public class EmpleadoController {

    private Empleado empleado;
    private Empleado selected;
    private List<Empleado> empleados;
    private Local local;
    private int localId;
    private Cargo cargo;
    private int cargoId;

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

    @PostConstruct
    public void init() {
        empleado = new Empleado();
        selected = new Empleado();
        cargo = new Cargo();
    }

    /**
     * Creates a new instance of EmpleadoController
     */
    public EmpleadoController() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        empleados = empleadoDAO.findAll();
    }

    public void save() {
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();
        CargoDAO cargoDAO = new CargoDAO();
        LocalDAO localDAO = new LocalDAO();
        local = localDAO.findById(localId);
        cargo = cargoDAO.findById(cargoId);
        if (cargo != null && local != null) {
            empleado.setLocal(local);
            empleado.setCargo(cargo);
            if (empleadoDAO.save(empleado)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                empleados = empleadoDAO.findAll();
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
