/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.stock.servicios.FamiliaDAO;
import py.gestion.stock.persistencia.Familia;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class FamiliaBean extends BeanGenerico<Familia>{

    @EJB private FamiliaDAO ejb;
    
    
    @Override
    public AbstractDAO<Familia> getEjb() {
        return ejb;
    }

    @Override
    public Familia getNuevo() {
        return new Familia();
    }

    
}
