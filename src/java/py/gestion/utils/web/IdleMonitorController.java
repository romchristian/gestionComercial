/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.seguridad.web.Autenticador;

/**
 *
 * @author christian
 */
//@Named
//@SessionScoped
public class IdleMonitorController implements Serializable {
    //@Inject
    private Autenticador autenticador;
    
    public void idleListener() {
        try {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Your session is closed", "You have been idle for at least 5 seconds"));

            //invalidate session  
            autenticador.logout();
        } catch (IOException ex) {
            Logger.getLogger(IdleMonitorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void activeListener() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Welcome Back", "That's a long coffee break!"));
    }
}
