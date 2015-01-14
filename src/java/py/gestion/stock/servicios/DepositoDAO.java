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
import py.gestion.adm.persistencia.Estado;
import py.gestion.stock.persistencia.Deposito;
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
public class DepositoDAO extends AbstractDAO<Deposito> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Deposito create(Deposito entity) {
        return abmService.create(entity);
    }

    @Override
    public Deposito edit(Deposito entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Deposito entity) {
        abmService.delete(entity);
    }

    @Override
    public Deposito find(Object id) {
        return abmService.find(id, Deposito.class);
    }

    @Override
    public List<Deposito> findAll() {
        return abmService.findByNamedQuery(Deposito.TODOS);
    }

    public List<Deposito> findAllActivos() {
        return abmService.findByNamedQuery(Deposito.ACTIVOS, where("estado", Estado.ACTIVO).parameters());
    }

    @Override
    public List<Deposito> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
