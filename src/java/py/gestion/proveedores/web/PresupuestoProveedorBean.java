/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.proveedores.servicios.PresupuestoProveedorDetService;
import py.gestion.proveedores.servicios.PresupuestoProveedorService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.proveedores.persistencia.PresupuestoProveedor;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class PresupuestoProveedorBean extends BeanGenerico<PresupuestoProveedor>{

    @EJB private PresupuestoProveedorService ejb;
    @EJB private PresupuestoProveedorDetService detDAO;
    
    
    @Override
    public AbstractDAO<PresupuestoProveedor> getEjb() {
        return ejb;
    }

    @Override
    public PresupuestoProveedor getNuevo() {
        return new PresupuestoProveedor();
    }

    
    
    

    
}
