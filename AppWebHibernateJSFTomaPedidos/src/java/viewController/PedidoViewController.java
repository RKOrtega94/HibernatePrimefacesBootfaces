/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import model.Menu;
import model.Pedido;

/**
 *
 * @author RKOrtega
 */
@Named(value = "pedidoViewController")
@ViewScoped
public class PedidoViewController implements Serializable {

    private Pedido selected;
    private Menu menu;
    private int menuId;
    private Pedido pedido;
    private List<Pedido> pedidos;
    private int pedidoId;
    private int cantidad;

    public Pedido getSelected() {
        return selected;
    }

    public void setSelected(Pedido selected) {
        this.selected = selected;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @PostConstruct
    public void init() {
        pedido = new Pedido();
        selected = new Pedido();
        menu = new Menu();
        pedidos = new ArrayList<>();
    }

    /**
     * Creates a new instance of PedidoViewController
     */
    public PedidoViewController() {
    }

    public List<Pedido> createPedido(int size) {
        List<Pedido> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(new Pedido(pedidoId, menuId, menu.getMenuNombre(), menu.getMenuValor(), cantidad));
        }
        return list;
    }

    public void addPedido() {
        PedidoViewController pvc = new PedidoViewController();
        Pedido pedidoAdd = pvc.createPedido(1).get(0);
        pedidos.add(pedidoAdd);
        FacesMessage msg = new FacesMessage("Pedido agregado", pedidoAdd.getMenu());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
