/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.puntoventa.persisitencia.ValorMoneda;
import py.gestion.puntoventa.servicio.ValorMonedaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class ValorMonedaBean extends BeanGenerico<ValorMoneda>{

    @EJB private ValorMonedaDAO ejb;
    
    
    @Override
    public AbstractDAO<ValorMoneda> getEjb() {
        return ejb;
    }

    @Override
    public ValorMoneda getNuevo() {
        return new ValorMoneda();
    }

    
}
