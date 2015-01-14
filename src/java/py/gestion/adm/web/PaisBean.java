/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.adm.persistencia.Pais;
import py.gestion.adm.servicios.PaisDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class PaisBean extends BeanGenerico<Pais> {

    @EJB
    private PaisDAO ejb;

    @Override
    public AbstractDAO<Pais> getEjb() {
        return ejb;
    }

    @Override
    public Pais getNuevo() {
        return new Pais();
    }
}
