/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.gestion.compra.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.compra.persistencia.RemisionProveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RemisionProveedorDAO extends AbstractDAO<RemisionProveedor> {
    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;

    @Override
    public RemisionProveedor create(RemisionProveedor entity) {
        return abmService.create(entity);
    }

    @Override
    public RemisionProveedor edit(RemisionProveedor entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(RemisionProveedor entity) {
        abmService.delete(entity);
    }

    @Override
    public RemisionProveedor find(Object id) {
        return abmService.find(id, RemisionProveedor.class);
    }

    @Override
    public List<RemisionProveedor> findAll() {
        return abmService.findByNamedQuery(RemisionProveedor.TODOS);
    }
    
    
    @Override
    public List<RemisionProveedor> findAll(String consulta, QueryParameter parametros) {
        return abmService.findByQuery(consulta, parametros.parameters());
    }
}
