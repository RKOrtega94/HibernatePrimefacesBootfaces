/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ClienteDAO;
import dao.EmpresaDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cliente;
import model.Empresa;

/**
 *
 * @author RKOrtega
 */
@Named(value = "clienteController")
@ApplicationScoped
public class ClienteController {

    private Cliente cliente;
    private Cliente selected;
    private List<Cliente> clientes;
    private Empresa empresa;
    private int empresaId;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
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
        cliente = new Cliente();
        selected = new Cliente();
        empresa = new Empresa();
    }

    /**
     * Creates a new instance of ClienteController
     */
    public ClienteController() {
        ClienteDAO clienteDAO = new ClienteDAO();
        clientes = clienteDAO.findAll();
    }

    public void save() {
        ClienteDAO clienteDAO = new ClienteDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.findById(empresaId);
        if (empresa != null) {
            cliente.setEmpresa(empresa);
            if (clienteDAO.save(cliente)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                clientes = clienteDAO.findAll();
                cliente = null;
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                cliente = null;
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            cliente = null;
        }
    }

    public void update() {
        ClienteDAO clienteDAO = new ClienteDAO();
        EmpresaDAO empresaDAO = new EmpresaDAO();
        empresa = empresaDAO.findById(empresaId);
        if (empresa != null) {
            selected.setEmpresa(empresa);
            if (clienteDAO.update(selected)) {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                clientes = clienteDAO.findAll();
                selected = null;
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                selected = null;
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            selected = null;
        }
    }

    public void delete() {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (clienteDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            clientes = clienteDAO.findAll();
            selected = null;
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            selected = null;
        }
    }
}
