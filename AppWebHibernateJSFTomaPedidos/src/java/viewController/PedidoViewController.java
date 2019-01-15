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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Cabecerafactura;
import model.Cliente;
import model.Detallefactura;
import model.Menu;
import model.Pedido;
import model.Usuario;
import org.primefaces.PrimeFaces;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import sessionController.UsuarioSessionController;

/**
 *
 * @author RKOrtega
 */
@ManagedBean(name = "pedidoViewController")
@ViewScoped
public class PedidoViewController implements Serializable {

    private Pedido pedido;
    private List<Pedido> pedidos;
    private Menu menu;
    private int menuId;
    private int pedidoId;
    private int cantidad;
    private Cliente cliente;
    private Detallefactura detallefactura;
    private Cabecerafactura cabecerafactura;
    private Usuario usuario;
    private int usuarioId;
    private List<String> claveFactura;
    private List<String> facturaPendiente;
    private String claveFacturaInt;
    private List<Cabecerafactura> facturas;
    private Cabecerafactura selected;
    private List<Cabecerafactura> ultima;

    @ManagedProperty(value = "#{usuarioSessionController}")
    private UsuarioSessionController usuarioSessionController = new UsuarioSessionController();

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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Detallefactura getDetallefactura() {
        return detallefactura;
    }

    public void setDetallefactura(Detallefactura detallefactura) {
        this.detallefactura = detallefactura;
    }

    public Cabecerafactura getCabecerafactura() {
        return cabecerafactura;
    }

    public void setCabecerafactura(Cabecerafactura cabecerafactura) {
        this.cabecerafactura = cabecerafactura;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setUsuarioSessionController(UsuarioSessionController usuarioSessionController) {
        this.usuarioSessionController = usuarioSessionController;
    }

    public List<String> getClaveFactura() {
        return claveFactura;
    }

    public void setClaveFactura(List<String> claveFactura) {
        this.claveFactura = claveFactura;
    }

    public String getClaveFacturaInt() {
        return claveFacturaInt;
    }

    public void setClaveFacturaInt(String claveFacturaInt) {
        this.claveFacturaInt = claveFacturaInt;
    }

    public List<Cabecerafactura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Cabecerafactura> facturas) {
        this.facturas = facturas;
    }

    public Cabecerafactura getSelected() {
        return selected;
    }

    public void setSelected(Cabecerafactura selected) {
        this.selected = selected;
    }

    public List<String> getFacturaPendiente() {
        return facturaPendiente;
    }

    public void setFacturaPendiente(List<String> facturaPendiente) {
        this.facturaPendiente = facturaPendiente;
    }

    public List<Cabecerafactura> getUltima() {
        return ultima;
    }

    public void setUltima(List<Cabecerafactura> ultima) {
        this.ultima = ultima;
    }

    @PostConstruct
    public void init() {
        pedido = new Pedido();
        menu = new Menu();
        cliente = new Cliente();
        detallefactura = new Detallefactura();
        pedidos = new ArrayList<>();
        usuario = new Usuario();
        selected = new Cabecerafactura();
    }

    /**
     * Creates a new instance of PedidoViewController
     */
    public PedidoViewController() {
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        facturas = facturaDAO.findPendiente();
        facturaPendiente = facturaDAO.countPendientes();
        claveFactura = facturaDAO.count();
        ultima = facturaDAO.ultimaFactura();
    }

    public void listFactura() {
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        facturas = facturaDAO.findPendiente();
        facturaPendiente = facturaDAO.countPendientes();
        claveFactura = facturaDAO.count();
    }

    public void addPedido() {
        MenuDAO menuDAO = new MenuDAO();
        menu = menuDAO.findById(menuId);
        List<Pedido> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add(new Pedido(pedidoId + 1, menuId, menu.getMenuNombre(), menu.getMenuValor(), cantidad));
        }
        pedidos.add(list.get(0));
        FacesMessage msg = new FacesMessage("Pedido agregado", menu.getMenuNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Pedido modificado", ((Pedido) event.getObject()).getMenu());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Modificación cancelada", ((Pedido) event.getObject()).getMenu());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        if (newValue != null && !newValue.equals(oldValue)) {
            DataTable table = (DataTable) event.getSource();
            int index = (int) event.getRowIndex();
            pedido = (Pedido) table.getRowData();
            pedidos.set(index, pedido);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido modificado", "Anteriguo: " + oldValue + ", Nuevo:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void ordenar() {
        CabeceraFacturaDAO facturaDAO = new CabeceraFacturaDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        DetallefacturaDAO detallefacturaDAO = new DetallefacturaDAO();
        cliente = clienteDAO.findByDni("9999999999");
        if (cliente != null && pedidos.size() > 0) {
            Double descuento = 0.0;
            if (facturaDAO.save(cliente, usuarioSessionController.getUsuario(), descuento)) {
                cabecerafactura = facturaDAO.findFactura(usuarioSessionController.getUsuario(), cliente);
                for (Pedido p : pedidos) {
                    MenuDAO menuDAO = new MenuDAO();
                    menu = menuDAO.findById(p.getMenuId());
                    cantidad = p.getCantidad();
                    if (detallefacturaDAO.save(cabecerafactura, menu, cantidad)) {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado!", menu.getMenuNombre());
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                }
                cabecerafactura.setCabecerafacturaEstado('P');
                if (facturaDAO.update(cabecerafactura)) {
                    ultima = facturaDAO.ultimaFactura();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado!", "Factura creada: " + ultima.toString());
                    PrimeFaces.current().dialog().showMessageDynamic(msg);
                    facturas = facturaDAO.findPendiente();
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error en la creación de la factura!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Ha ocurrido un error!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error!", "Debe ingresar almenos un producto!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
