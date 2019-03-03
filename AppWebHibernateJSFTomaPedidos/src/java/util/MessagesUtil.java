package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
public class MessagesUtil {

    public void infoMessage(String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito!", detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void warnMessage(String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia!", detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorMessage(String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void fatalMessage(String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!", detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
