/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.puntoventa.persisitencia.Diario;
import py.gestion.puntoventa.servicio.DiarioDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class DiarioBean extends BeanGenerico<Diario>{

    @EJB private DiarioDAO ejb;
    
    
    @Override
    public AbstractDAO<Diario> getEjb() {
        return ejb;
    }

    @Override
    public Diario getNuevo() {
        return new Diario();
    }

    
}
