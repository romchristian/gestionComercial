/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import py.gestion.seguridad.servicios.UsuarioDAO;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author cromero
 */
@Stateless
@Named
public class Autenticador implements Serializable {

    @Inject
    private Credencial credencial;
    private boolean logeado;
    @EJB
    private UsuarioDAO usuarioDAO;

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }

    public void login() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        String projectPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        try {

            request.login(credencial.getUsuario().getUsuario(), credencial.getUsuario().getPlainClave());
            credencial.setUsuario(usuarioDAO.find(credencial.getUsuario().getUsuario()));
            JsfUtil.addSuccessMessage("¡Bienvenido " + request.getUserPrincipal() + "!");
            context.getExternalContext().redirect(projectPath+"/puntoventa/sesionTPV/listado.xhtml");

        } catch (ServletException e) {
            JsfUtil.addErrorMessage("Fallo el logeo");
            try {
                context.getExternalContext().redirect(projectPath+"/error.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Autenticador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String logout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.getSession(false).invalidate();
            request.logout();
            String projectPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
            FacesContext.getCurrentInstance().getExternalContext().redirect(projectPath+"/login.xhtml");
        } catch (ServletException e) {
            context.addMessage(null, new FacesMessage("Logout failed."));
        }
        return null;
    }
}
