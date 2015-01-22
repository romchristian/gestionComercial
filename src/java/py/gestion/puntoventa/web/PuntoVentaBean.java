/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.gestion.contabilidad.persistencia.MetodoPago;
import py.gestion.puntoventa.persisitencia.PuntoVenta;
import py.gestion.puntoventa.servicio.PuntoVentaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class PuntoVentaBean extends BeanGenerico<PuntoVenta>{

    @EJB private PuntoVentaDAO ejb;
    private MetodoPago metodoSeleccionado;

    public MetodoPago getMetodoSeleccionado() {
        return metodoSeleccionado;
    }

    public void setMetodoSeleccionado(MetodoPago metodoSeleccionado) {
        this.metodoSeleccionado = metodoSeleccionado;
    }
   
    public void agregaMetodoPago(){
        getActual().addMetodoPago(metodoSeleccionado);
    }
    
    public void remueveMetodoPago(MetodoPago m){
        getActual().removeMetodoPago(m);
    }
    
    @Override
    public AbstractDAO<PuntoVenta> getEjb() {
        return ejb;
    }

    @Override
    public PuntoVenta getNuevo() {
        return new PuntoVenta();
    }

    
}
