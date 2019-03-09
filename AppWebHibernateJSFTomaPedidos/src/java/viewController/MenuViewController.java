/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.MenuDAO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Menu;

/**
 *
 * @author RKOrtega
 */
@Named(value = "menuViewController")
@ViewScoped
public class MenuViewController implements Serializable {

    private List<Menu> entrada;
    private List<Menu> fuertes;
    private List<Menu> postres;
    private List<Menu> bebidas;
    private Menu selected;

    public List<Menu> getEntrada() {
        return entrada;
    }

    public void setEntrada(List<Menu> entrada) {
        this.entrada = entrada;
    }

    public Menu getSelected() {
        return selected;
    }

    public void setSelected(Menu selected) {
        this.selected = selected;
    }

    @PostConstruct
    public void init() {
        selected = new Menu();
    }

    public List<Menu> getFuertes() {
        return fuertes;
    }

    public void setFuertes(List<Menu> fuertes) {
        this.fuertes = fuertes;
    }

    public List<Menu> getPostres() {
        return postres;
    }

    public void setPostres(List<Menu> postres) {
        this.postres = postres;
    }

    public List<Menu> getBebidas() {
        return bebidas;
    }

    public void setBebidas(List<Menu> bebidas) {
        this.bebidas = bebidas;
    }

    /**
     * Creates a new instance of MenuViewController
     */
    public MenuViewController() {
        MenuDAO menuDAO = new MenuDAO();
        entrada = menuDAO.findEntradas();
        fuertes = menuDAO.findPlatoFuerte();
        postres = menuDAO.findPostre();
        bebidas = menuDAO.findBebida();
    }

}
