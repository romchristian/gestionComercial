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
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DetOrdenCompraDAO extends AbstractDAO<DetOrdenCompra> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public DetOrdenCompra create(DetOrdenCompra entity) {
        return abmService.create(entity);
    }

    @Override
    public DetOrdenCompra edit(DetOrdenCompra entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(DetOrdenCompra entity) {
        abmService.delete(entity);
    }

    @Override
    public DetOrdenCompra find(Object id) {
        return abmService.find(id, DetOrdenCompra.class);
    }

    @Override
    public List<DetOrdenCompra> findAll() {
        return abmService.findByNamedQuery(DetOrdenCompra.TODOS);
    }

    @Override
    public List<DetOrdenCompra> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
