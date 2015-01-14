/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.adm.persistencia.Localidad;
import py.gestion.adm.servicios.LocalidadDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class LocalidadBean extends BeanGenerico<Localidad> {

    @EJB
    private LocalidadDAO ejb;

    @Override
    public AbstractDAO<Localidad> getEjb() {
        return ejb;
    }

    @Override
    public Localidad getNuevo() {
        return new Localidad();
    }
    
    
}
