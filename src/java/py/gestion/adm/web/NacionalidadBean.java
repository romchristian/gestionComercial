/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.servicios.NacionalidadDAO;
import py.gestion.adm.persistencia.Nacionalidad;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class NacionalidadBean extends BeanGenerico<Nacionalidad> {

    @EJB
    private NacionalidadDAO ejb;

    @Override
    public AbstractDAO<Nacionalidad> getEjb() {
        return ejb;
    }

    @Override
    public Nacionalidad getNuevo() {
        return new Nacionalidad();
    }
}
