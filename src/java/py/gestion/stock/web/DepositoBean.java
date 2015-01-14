/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import py.gestion.stock.servicios.DepositoDAO;
import py.gestion.stock.persistencia.Deposito;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class DepositoBean extends BeanGenerico<Deposito>{

    @EJB private DepositoDAO ejb;
    
    
    @Override
    public AbstractDAO<Deposito> getEjb() {
        return ejb;
    }

    @Override
    public Deposito getNuevo() {
        return new Deposito();
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAllActivos(), true);
    }
    
    

    
}
