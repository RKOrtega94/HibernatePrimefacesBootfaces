/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewController;

import dao.CabeceraFacturaDAO;
import dao.ClienteDAO;
import dao.DetallefacturaDAO;
import dao.MenuDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import model.Cabecerafactura;
import model.Detallefactura;
import model.Menu;
import model.Pedido;
import model.Usuario;
import org.primefaces.event.RowEditEvent;
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

    //Declaración de variables
    private Pedido pedido;
    private List<Pedido> pedidos;
    private Menu menu;
    private Cabecerafactura cabecerafactura;
    private Detallefactura detallefactura;
    private int menuId;
    private int cantidad;
    private Usuario usuario;
    private List<Integer> idMenu;
    private Double subtotal = 0.0;
    private String format = "%.2f";

    //Instancia de la sesión
    @ManagedProperty(value = "#{usuarioSessionController}")
    private UsuarioSessionController sessionController;

    //Post-constructor
    @PostConstruct
    public void init() {
        pedido = new Pedido();
        menu = new Menu();
    }

    //Constructor
    public TomaPedidoViewController() {
        this.pedidos = new ArrayList<>();
        this.idMenu = new ArrayList<>();
    }

    //Getters y setters
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Cabecerafactura getCabecerafactura() {
        return cabecerafactura;
    }

    public void setCabecerafactura(Cabecerafactura cabecerafactura) {
        this.cabecerafactura = cabecerafactura;
    }

    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    //Agregar pedido a la lista
    public void addPedido() {
        subtotal = 0.0;
        MenuDAO menuDAO = new MenuDAO();
        MessagesUtil message = new MessagesUtil();
        SumaFactura sumaFactura = new SumaFactura();
        menu = menuDAO.findById(menuId);
        //Verificar que el menu no esté dentro de la lista
        if (idMenu.indexOf(menuId) >= 0 || cantidad == 0) {
            message.warnMessage("No se puede agregar pedidos duplicados o sin una cantidad!");
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

    //Listener on submit
    public void onSubmit() {
        subtotal = 0.0;
        SumaFactura sumaFactura = new SumaFactura();
        for (Pedido p : pedidos) {
            subtotal = subtotal + sumaFactura.suma(p.getMenu().getMenuValor().doubleValue(), p.getCantidad());
        }
    }

    //On row edit
    public void onRowEdit(RowEditEvent event) {
        //Instancias
        SumaFactura sumaFactura = new SumaFactura();
        MessagesUtil message = new MessagesUtil();
        //Setear el index del obgeto
        int idIndex = idMenu.indexOf(((Pedido) event.getObject()).getMenu().getMenuId());
        //Valida si el index existe en la lista
        if (idIndex >= 0) {
            subtotal = 0.0;
            //Setear el nuevo obgeto en el index que corresponde
            pedidos.set(idIndex, new Pedido(((Pedido) event.getObject()).getMenu(), ((Pedido) event.getObject()).getCantidad()));
            message.infoMessage("Pedido de " + ((Pedido) event.getObject()).getMenu().getMenuNombre() + " ha sido modificado!");
            //Suma el subtotal
            for (Pedido p : pedidos) {
                subtotal = subtotal + sumaFactura.suma(p.getMenu().getMenuValor().doubleValue(), p.getCantidad());
            }
        } else {
            message.errorMessage("Ha ocurrido un error!");
        }
    }

    //Guardar pedido
    public void save() {
        MessagesUtil message = new MessagesUtil();
        CabeceraFacturaDAO cabeceraFacturaDAO = new CabeceraFacturaDAO();
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        try {
            //Validar que exista almenos un producto en la lista
            if (pedidos.size() < 1) {
                message.warnMessage("Debe ingresar al menos un producto!");
            } else {
                //Generar la factura
                if (cabeceraFacturaDAO.save(clienteDAO.findByDni("9999999999"), sessionController.getUsuario(), 0.0)) {
                    //Almacenar la factura generada
                    cabeceraFacturaDAO.ultimaFactura().forEach((c) -> {
                        cabecerafactura = c;
                    });
                    //Bucle para guardar el detalle de la factura
                    pedidos.forEach((p) -> {
                        //Agregar los elementos de la lista en el detalle
                        if (detallefacturaDAO.save(cabecerafactura, p.getMenu(), p.getCantidad())) {
                            message.infoMessage(p.getMenu().getMenuNombre() + "Ha sido agregado!");
                        } else {
                            message.errorMessage("Ha ocurrido un error al agregar " + p.getMenu().getMenuNombre() + " a la factura");
                        }
                    });
                    sessionController.doRedirect("/appmenu/app/editarpedido/index.jsf");
                } else {
                    message.errorMessage("Ha ocurrido un error!");
                }
            }
        } catch (Exception e) {
            message.fatalMessage(e.toString());
        }
    }
}
