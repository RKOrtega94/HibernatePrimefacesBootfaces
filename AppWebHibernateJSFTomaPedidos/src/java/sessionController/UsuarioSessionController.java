/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionController;

import dao.OpcionPerfilDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.OpcionXperfil;
import model.Usuario;

/**
 *
 * @author RKOrtega
 */
@Named(value = "usuarioSessionController")
@SessionScoped
public class UsuarioSessionController implements Serializable {

    private String username;
    private String password;
    private Usuario usuario;
    private boolean isLogin = false;
    private int intento = 1;
    private List<OpcionXperfil> opciones;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public int getIntento() {
        return intento;
    }

    public void setIntento(int intento) {
        this.intento = intento;
    }

    public List<OpcionXperfil> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<OpcionXperfil> opciones) {
        this.opciones = opciones;
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    /**
     * Creates a new instance of UsuarioSessionController
     */
    public UsuarioSessionController() {
    }

    public void verifyLogin() {
        if (!this.isLogin) {
            doRedirect("/appmenu");
        }
    }

    public void login() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        OpcionPerfilDAO opcionPerfilDAO = new OpcionPerfilDAO();
        usuario = usuarioDAO.findByUsername(username);
        if (usuario != null) {
            if (intento != 3) {
                if (usuario.getUsuarioClave().equals(password)) {
                    if (usuario.getUsuarioEstado() == 'A') {
                        isLogin = true;
                        doRedirect("/appmenu/home.jsf");
                        opciones = opcionPerfilDAO.findByPerfil(usuario.getPerfil());
                    } else {
                        FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", usuario.getEmpleado().getEmpleadoPrimernombre() + " su cuenta se encuentra bloqueada!");
                        FacesContext.getCurrentInstance().addMessage(null, massage);
                    }
                } else {
                    FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", usuario.getEmpleado().getEmpleadoPrimernombre() + " la clave es incorrecta!");
                    FacesContext.getCurrentInstance().addMessage(null, massage);
                    intento++;
                }
            } else {
                FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", usuario.getEmpleado().getEmpleadoPrimernombre() + " su cuenta se encuentra bloqueada!");
                FacesContext.getCurrentInstance().addMessage(null, massage);
                usuario.setUsuarioEstado('I');
            }
        } else {
            FacesMessage massage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Usuario incorrecto!");
            FacesContext.getCurrentInstance().addMessage(null, massage);
        }
    }

    public void logout() {
        isLogin = false;
    }

    public void doRedirect(String url) {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.getExternalContext().redirect(url);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
