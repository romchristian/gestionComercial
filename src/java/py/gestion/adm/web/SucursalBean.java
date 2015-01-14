/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.servicios.SucursalDAO;
import py.gestion.adm.persistencia.Sucursal;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class SucursalBean extends BeanGenerico<Sucursal>{

    @EJB private SucursalDAO ejb;
    
    
    @Override
    public AbstractDAO<Sucursal> getEjb() {
        return ejb;
    }

    @Override
    public Sucursal getNuevo() {
        return new Sucursal();
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAllPorEmpresa(),false);
    }
    
    

    
}
