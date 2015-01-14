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
import py.gestion.proveedores.persistencia.DetPresupuestoProveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PresupuestoProveedorDetService extends AbstractDAO<DetPresupuestoProveedor> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public DetPresupuestoProveedor create(DetPresupuestoProveedor entity) {
        return abmService.create(entity);
    }

    @Override
    public DetPresupuestoProveedor edit(DetPresupuestoProveedor entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(DetPresupuestoProveedor entity) {
        abmService.delete(entity);
    }

    @Override
    public DetPresupuestoProveedor find(Object id) {
        return abmService.find(id, DetPresupuestoProveedor.class);
    }

    @Override
    public List<DetPresupuestoProveedor> findAll() {
        return abmService.findByNamedQuery(DetPresupuestoProveedor.TODOS);
    }

    @Override
    public List<DetPresupuestoProveedor> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
