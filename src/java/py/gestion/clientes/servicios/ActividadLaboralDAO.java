/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.clientes.persistencia.ActividadLaboral;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ActividadLaboralDAO extends AbstractDAO<ActividadLaboral> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ActividadLaboral create(ActividadLaboral entity) {
        return abmService.create(entity);
    }

    @Override
    public ActividadLaboral edit(ActividadLaboral entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ActividadLaboral entity) {
        abmService.delete(entity);
    }

    @Override
    public ActividadLaboral find(Object id) {
        return abmService.find(id, ActividadLaboral.class);
    }

    @Override
    public List<ActividadLaboral> findAll() {
        return abmService.findByNamedQuery(ActividadLaboral.TODOS);
    }

    @Override
    public List<ActividadLaboral> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
