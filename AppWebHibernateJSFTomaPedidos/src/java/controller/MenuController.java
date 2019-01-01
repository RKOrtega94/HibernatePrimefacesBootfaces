/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.EmpresaDAO;
import dao.MenuDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import model.Empresa;
import model.Menu;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author RKOrtega
 */
@Named(value = "menuController")
@ApplicationScoped
public class MenuController {

    private Menu menu;
    private Menu selected;
    private List<Menu> menus;
    private int empresaId;
    private Empresa empresa;
    private UploadedFile file;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getSelected() {
        return selected;
    }

    public void setSelected(Menu selected) {
        this.selected = selected;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    @PostConstruct
    public void init() {
        menu = new Menu();
        selected = new Menu();
        empresa = new Empresa();
    }

    /**
     * Creates a new instance of MenuController
     */
    public MenuController() {
        MenuDAO menuDAO = new MenuDAO();
        menus = menuDAO.findAll();
    }

    public void save() throws IOException {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        MenuDAO menuDAO = new MenuDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String rootpath = servletContext.getRealPath("/");
        File fileImage = new File(rootpath + "upload" + File.separator + "temp" + File.separator + file.getFileName());
        InputStream inputStream = file.getInputstream();
        if (SaveImage(inputStream, fileImage)) {
            empresa = empresaDAO.findById(empresaId);
            if (empresa != null) {
                menu.setEmpresa(empresa);
                menu.setMenuFoto("/upload/temp/" + file.getFileName());
                if (menuDAO.save(menu)) {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato registrado correctamente!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                    menus = menuDAO.findAll();
                } else {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                }
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "No se ha procesado el archivo!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void update() throws IOException {
        EmpresaDAO empresaDAO = new EmpresaDAO();
        MenuDAO menuDAO = new MenuDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        String rootpath = servletContext.getRealPath("/");
        File fileImage = new File(rootpath + "upload" + File.separator + "temp" + File.separator + file.getFileName());
        InputStream inputStream = file.getInputstream();
        if (SaveImage(inputStream, fileImage)) {
            empresa = empresaDAO.findById(empresaId);
            if (empresa != null) {
                selected.setEmpresa(empresa);
                selected.setMenuFoto("/upload/temp/" + file.getFileName());
                if (menuDAO.update(selected)) {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato modificado correctamente!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                    menus = menuDAO.findAll();
                } else {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                }
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
            }
        }
    }

    public void delete() {
        MenuDAO menuDAO = new MenuDAO();
        if (menuDAO.delete(selected)) {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Dato eliminado correctamente!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
            menus = menuDAO.findAll();
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ha ocurrido un error!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public boolean SaveImage(InputStream inputStream, File ImageFile) {
        boolean result = true;
        try {
            OutputStream outputStream = new FileOutputStream(ImageFile);
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            result = false;
        }
        return result;
    }
}
