/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.servicios.CotizacionDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;


/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CotizacionBean extends BeanGenerico<Cotizacion> {

    
    
    @EJB
    private CotizacionDAO ejb;

    @Override
    public AbstractDAO<Cotizacion> getEjb() {
        return ejb;
    }

    @Override
    public Cotizacion getNuevo() {
        return new Cotizacion();
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAll(), true);
    }
    
    public SelectItem[] getItemsAvailableSelectOnePorMoneda(Moneda moneda) {
        return JsfUtil.getSelectItems(ejb.findUltimo(moneda), false);
    }
    
}
