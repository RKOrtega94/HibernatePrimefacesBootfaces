package controller;

import dao.CargoDAO;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cargo;

/**
 *
 * @author RKOrtega
 */
@Named(value = "cargoController")
@ApplicationScoped
public class CargoController implements Serializable {

    private Cargo cargo;
    private Cargo selected;
    private List<Cargo> cargos;

    @PostConstruct
    public void init() {
        cargo = new Cargo();
        selected = new Cargo();
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Cargo getSelected() {
        return selected;
    }

    public void setSelected(Cargo selected) {
        this.selected = selected;
    }

    public List<Cargo> getCargos() {
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    /**
     * Creates a new instance of CargoController
     */
    public CargoController() {
        CargoDAO cargoDAO = new CargoDAO();
        cargos = cargoDAO.findAll();
    }

    public void save() {
        CargoDAO cargoDAO = new CargoDAO();
        if (cargoDAO.save(cargo)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            cargos = cargoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() {
        CargoDAO cargoDAO = new CargoDAO();
        if (cargoDAO.update(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            cargos = cargoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void delete() {
        CargoDAO cargoDAO = new CargoDAO();
        if (cargoDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            cargos = cargoDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }
}
