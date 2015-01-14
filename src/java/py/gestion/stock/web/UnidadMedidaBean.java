/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.stock.servicios.UnidadMedidaDAO;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class UnidadMedidaBean extends BeanGenerico<UnidadMedida>{

    @EJB private UnidadMedidaDAO ejb;
    
    
    @Override
    public AbstractDAO<UnidadMedida> getEjb() {
        return ejb;
    }

    @Override
    public UnidadMedida getNuevo() {
        return new UnidadMedida();
    }

    
}
