/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.bancos.servicios.TransaccionBancariaDAO;
import py.gestion.bancos.persistencia.TransaccionBancaria;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class TransaccionBancariaBean extends BeanGenerico<TransaccionBancaria>{

    @EJB private TransaccionBancariaDAO ejb;
    
    
    @Override
    public AbstractDAO<TransaccionBancaria> getEjb() {
        return ejb;
    }

    @Override
    public TransaccionBancaria getNuevo() {
        return new TransaccionBancaria();
    }

    
}
