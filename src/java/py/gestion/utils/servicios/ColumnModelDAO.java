/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.utils.persistencia.ColumnModel;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ColumnModelDAO extends AbstractDAO<ColumnModel> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ColumnModel create(ColumnModel entity) {
        return abmService.create(entity);
    }

    @Override
    public ColumnModel edit(ColumnModel entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ColumnModel entity) {
        abmService.delete(entity);
    }

    @Override
    public ColumnModel find(Object id) {
        return abmService.find(id, ColumnModel.class);
    }

    @Override
    public List<ColumnModel> findAll() {
        return abmService.findByNamedQuery(ColumnModel.TODOS);
    }

    public List<ColumnModel> findAll(String clase, boolean visible) {
        return abmService.findByNamedQuery(ColumnModel.POR_CLASE_VISIBLE, QueryParameter.where("clase", clase).and("visible", visible).parameters());
    }

    public List<ColumnModel> findAll(String clase) {
        return abmService.findByNamedQuery(ColumnModel.POR_CLASE, QueryParameter.where("clase", clase).parameters());
    }

    @Override
    public List<ColumnModel> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
    
    
}
