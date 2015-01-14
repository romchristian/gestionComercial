/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import py.gestion.seguridad.servicios.UsuarioDAO;
import py.gestion.seguridad.persistencia.Usuario;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends BeanGenerico<Usuario> {

    @EJB
    private UsuarioDAO ejb;

    @Override
    public AbstractDAO<Usuario> getEjb() {
        return ejb;
    }

    @Override
    public Usuario getNuevo() {
        return new Usuario();
    }

    
    
    
}
