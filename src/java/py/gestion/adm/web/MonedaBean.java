/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import py.gestion.adm.servicios.MonedaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class MonedaBean extends BeanGenerico<Moneda> {

    @EJB
    private MonedaDAO ejb;

    @Override
    public AbstractDAO<Moneda> getEjb() {
        return ejb;
    }

    @Override
    public Moneda getNuevo() {
        return new Moneda();
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAllActivo(),false);
    }
    
    
}
