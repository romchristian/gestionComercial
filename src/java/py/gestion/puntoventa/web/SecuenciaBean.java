/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.puntoventa.persisitencia.Secuencia;
import py.gestion.puntoventa.servicio.SecuenciaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class SecuenciaBean extends BeanGenerico<Secuencia>{

    @EJB private SecuenciaDAO ejb;
    
    
    @Override
    public AbstractDAO<Secuencia> getEjb() {
        return ejb;
    }

    @Override
    public Secuencia getNuevo() {
        return new Secuencia();
    }

    
}
