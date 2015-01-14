/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.gestion.adm.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.adm.persistencia.ConfRed;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ConfRedDAO extends AbstractDAO<ConfRed> {
    
    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;
    
    public ConfRed findPorNombre(String nombre){
    return abmService.findByNamedQuerySingleResult(ConfRed.POR_NOMBRE,
                where("nombre", nombre).parameters());
    }

    @Override
    public ConfRed create(ConfRed entity) {
        return abmService.create(entity);
    }

    @Override
    public ConfRed edit(ConfRed entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ConfRed entity) {
        abmService.delete(entity);
    }

    @Override
    public ConfRed find(Object id) {
        return abmService.find(id, ConfRed.class);
    }

    @Override
    public List<ConfRed> findAll() {
        return abmService.findByNamedQuery(ConfRed.TODOS);
    }

    @Override
    public List<ConfRed> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
