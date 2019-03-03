/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.CabeceraFacturaDAO;
import dao.MenuDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.Menu;
import model.Pedido;
import model.Usuario;
import sessionController.UsuarioSessionController;
import util.MessagesUtil;

/**
 *
 * @author Usuario
 */
@ManagedBean(name = "tomaPedidoViewController")
@ViewScoped
public class TomaPedidoViewController implements Serializable {

    private Pedido pedido;
    private List<Pedido> pedidos;
    private Menu menu;
    private int menuId;
    private int cantidad;
    private Usuario usuario;
    private List<Integer> idMenu;

    @ManagedProperty(value = "#{usuarioSessionController}")
    private UsuarioSessionController sessionController;

    /**
     * Creates a new instance of TomaPedidoViewController
     */
    @PostConstruct
    public void init() {
        pedido = new Pedido();
        menu = new Menu();
    }

    public TomaPedidoViewController() {
        this.pedidos = new ArrayList<>();
        this.idMenu = new ArrayList<>();
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

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSessionController(UsuarioSessionController sessionController) {
        this.sessionController = sessionController;
    }

    public List<Integer> getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(List<Integer> idMenu) {
        this.idMenu = idMenu;
    }

    //Agregar pedido a la lista
    public void addPedido() {
        MenuDAO menuDAO = new MenuDAO();
        MessagesUtil message = new MessagesUtil();
        menu = menuDAO.findById(menuId);
        //Verificar que el menu no esté dentro de la lista
        if (idMenu.indexOf(menuId) >= 0) {
            message.warnMessage("El pedido ya se encuentra en la lista!");
        } else {
            //Guardar Id del menu en la lista
            idMenu.add(menuId);
            if (menu != null) {
                try {
                    //Validar que se agregue el producto
                    if (pedidos.add(new Pedido(menu, cantidad))) {
                        message.infoMessage("Pedido agregado!");
                    } else {
                        message.errorMessage("Ha ocurrido un error!");
                    }
                } catch (Exception e) {
                    message.fatalMessage(e.toString());
                }
            }
        }
    }

    //Guardar pedido
    public void save() {
        MessagesUtil message = new MessagesUtil();
        message.infoMessage("Hola" + sessionController.getUsuario().getEmpleado().getEmpleadoPrimernombre());
    }
}
