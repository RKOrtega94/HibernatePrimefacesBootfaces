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
    private int pedidoId = 1;

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
    

    @PostConstruct
    public void init() {
        pedido = new Pedido();
        selected = new Pedido();
        menu = new Menu();
    }

    /**
     * Creates a new instance of PedidoViewController
     */
    public PedidoViewController() {
    }

    public void addPedido() {
        MenuDAO menuDAO = new MenuDAO();
        menu = menuDAO.findById(menuId);
        if(menu != null){
            pedido.setId(pedidoId);
            pedido.setMenuId(menu.getMenuId());
            pedido.setMenu(menu.getMenuNombre());
            pedido.setValor(menu.getMenuValor());
            if(pedido != null){
                pedidos.add(pedido);
            }
            pedidoId++;
        }
    }
}
