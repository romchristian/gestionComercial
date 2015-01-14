/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.proveedores.persistencia.PresupuestoProveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PresupuestoProveedorService extends AbstractDAO<PresupuestoProveedor> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public PresupuestoProveedor create(PresupuestoProveedor entity) {
        return abmService.create(entity);
    }

    @Override
    public PresupuestoProveedor edit(PresupuestoProveedor entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(PresupuestoProveedor entity) {
        abmService.delete(entity);
    }

    @Override
    public PresupuestoProveedor find(Object id) {
        return abmService.find(id, PresupuestoProveedor.class);
    }

    @Override
    public List<PresupuestoProveedor> findAll() {
        return abmService.findByNamedQuery(PresupuestoProveedor.TODOS);
    }

    @Override
    public List<PresupuestoProveedor> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
