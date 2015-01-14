/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UnidadMedidaDAO extends AbstractDAO<UnidadMedida> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public UnidadMedida create(UnidadMedida entity) {
        return abmService.create(entity);
    }

    @Override
    public UnidadMedida edit(UnidadMedida entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(UnidadMedida entity) {
        abmService.delete(entity);
    }

    @Override
    public UnidadMedida find(Object id) {
        return abmService.find(id, UnidadMedida.class);
    }

    @Override
    public List<UnidadMedida> findAll() {
        return abmService.findByNamedQuery(UnidadMedida.TODOS);
    }

    @Override
    public List<UnidadMedida> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
