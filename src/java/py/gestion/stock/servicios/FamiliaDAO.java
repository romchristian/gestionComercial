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
import py.gestion.stock.persistencia.Familia;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FamiliaDAO extends AbstractDAO<Familia> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Familia create(Familia entity) {
        return abmService.create(entity);
    }

    @Override
    public Familia edit(Familia entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Familia entity) {
        abmService.delete(entity);
    }

    @Override
    public Familia find(Object id) {
        return abmService.find(id, Familia.class);
    }

    @Override
    public List<Familia> findAll() {
        return abmService.findByNamedQuery(Familia.TODOS);
    }

    @Override
    public List<Familia> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
