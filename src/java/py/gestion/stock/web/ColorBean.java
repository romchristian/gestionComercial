/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.servicios.ColorDAO;
import py.gestion.stock.persistencia.Color;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class ColorBean extends BeanGenerico<Color>{

    @EJB private ColorDAO ejb;
    
    
    @Override
    public AbstractDAO<Color> getEjb() {
        return ejb;
    }

    @Override
    public Color getNuevo() {
        return new Color();
    }

    
}
