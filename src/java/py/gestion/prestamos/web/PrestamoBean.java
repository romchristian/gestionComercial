/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.prestamos.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.prestamos.servicios.PrestamoDAO;
import py.gestion.prestamos.persistencia.Prestamo;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class PrestamoBean extends BeanGenerico<Prestamo>{

    @EJB private PrestamoDAO ejb;    
    
    @Override
    public AbstractDAO<Prestamo> getEjb() {
        return ejb;
    }

    @Override
    public Prestamo getNuevo() {
        return new Prestamo();
    }

    public String calcular(){
        getActual().setSistema(null);
        getActual().setDetalles(null);
        getActual().getDetalles();
        return null;
    }
    
    public void desembolsa(Prestamo prestamo){
        ejb.desembolsa(prestamo);
    }

}
