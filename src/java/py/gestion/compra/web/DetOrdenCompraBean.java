/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.servicios.DetOrdenCompraDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class DetOrdenCompraBean extends BeanGenerico<DetOrdenCompra> {

    @EJB
    private DetOrdenCompraDAO ejb;

    @Override
    public AbstractDAO<DetOrdenCompra> getEjb() {
        return ejb;
    }

    @Override
    public DetOrdenCompra getNuevo() {
        return new DetOrdenCompra();
    }
}
