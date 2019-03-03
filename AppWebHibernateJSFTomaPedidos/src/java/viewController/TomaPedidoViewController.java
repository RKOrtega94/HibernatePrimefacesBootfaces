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
import org.primefaces.event.CellEditEvent;
import sessionController.UsuarioSessionController;
import util.MessagesUtil;
import util.SumaFactura;

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
    private Double subtotal = 0.0;

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

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    //Agregar pedido a la lista
    public void addPedido() {
        subtotal = 0.0;
        MenuDAO menuDAO = new MenuDAO();
        MessagesUtil message = new MessagesUtil();
        SumaFactura sumaFactura = new SumaFactura();
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
        //Suma el subtotal
        for (Pedido p : pedidos) {
            subtotal = subtotal + sumaFactura.suma(p.getMenu().getMenuValor().doubleValue(), p.getCantidad());
        }
    }
    
    //On row edit
    public void onRowEdit(){
        
    }
    
    //On row cancel

    //Editar orden
    public void onCellEdit(CellEditEvent event) {
        SumaFactura sumaFactura = new SumaFactura();
        MessagesUtil message = new MessagesUtil();
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            message.infoMessage("Pedido modificado!");
        } else {
            message.errorMessage("Ha ocurrido un error!");
        }
        //Suma el subtotal
        for (Pedido p : pedidos) {
            subtotal = subtotal + sumaFactura.suma(p.getMenu().getMenuValor().doubleValue(), p.getCantidad());
        }
    }

    //Guardar pedido
    public void save() {
        MessagesUtil message = new MessagesUtil();
        CabeceraFacturaDAO cabeceraFacturaDAO = new CabeceraFacturaDAO();
        message.infoMessage("Hola " + sessionController.getUsuario().getEmpleado().getEmpleadoPrimernombre());
    }
}
