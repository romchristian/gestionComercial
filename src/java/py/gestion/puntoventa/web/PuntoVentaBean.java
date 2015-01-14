/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.puntoventa.persisitencia.PuntoVenta;
import py.gestion.puntoventa.servicio.PuntoVentaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class PuntoVentaBean extends BeanGenerico<PuntoVenta>{

    @EJB private PuntoVentaDAO ejb;
    
    
    @Override
    public AbstractDAO<PuntoVenta> getEjb() {
        return ejb;
    }

    @Override
    public PuntoVenta getNuevo() {
        return new PuntoVenta();
    }

    
}
