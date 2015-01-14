/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.adm.servicios.ImpuestoIVADAO;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class ImpuestoIVABean extends BeanGenerico<ImpuestoIVA>{

    @EJB private ImpuestoIVADAO ejb;
    
    
    @Override
    public AbstractDAO<ImpuestoIVA> getEjb() {
        return ejb;
    }

    @Override
    public ImpuestoIVA getNuevo() {
        return new ImpuestoIVA();
    }
}
