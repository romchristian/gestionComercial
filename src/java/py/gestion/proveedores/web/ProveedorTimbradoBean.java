/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.web;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;
import py.gestion.proveedores.servicios.ProveedorTimbradoDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class ProveedorTimbradoBean extends BeanGenerico<ProveedorTimbrado> {

    @EJB
    private ProveedorTimbradoDAO ejb;

    @Override
    public AbstractDAO<ProveedorTimbrado> getEjb() {
        return ejb;
    }

    @Override
    public ProveedorTimbrado getNuevo() {
        return new ProveedorTimbrado();
    }
}
