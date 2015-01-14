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
import py.gestion.adm.persistencia.Estado;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProveedorTimbradoDAO extends AbstractDAO<ProveedorTimbrado> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ProveedorTimbrado create(ProveedorTimbrado entity) {
        abmService.create(entity);
        return entity;
    }

    @Override
    public ProveedorTimbrado edit(ProveedorTimbrado entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ProveedorTimbrado entity) {
        abmService.delete(entity);
    }

    @Override
    public ProveedorTimbrado find(Object id) {
        return abmService.find(id, ProveedorTimbrado.class);
    }

    @Override
    public List<ProveedorTimbrado> findAll() {
        return abmService.findByNamedQuery(ProveedorTimbrado.TODOS);
    }

    public List<ProveedorTimbrado> findAll(Proveedor proveedor) {
        return abmService.
                findByNamedQuery(ProveedorTimbrado.PROVEEDOR, QueryParameter.where("proveedor", proveedor).
                and("estado", Estado.ACTIVO).parameters());
    }

    @Override
    public List<ProveedorTimbrado> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
