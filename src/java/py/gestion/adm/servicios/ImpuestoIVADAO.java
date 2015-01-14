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
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ImpuestoIVADAO extends AbstractDAO<ImpuestoIVA> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ImpuestoIVA create(ImpuestoIVA entity) {
        return abmService.create(entity);
    }

    @Override
    public ImpuestoIVA edit(ImpuestoIVA entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ImpuestoIVA entity) {
        abmService.delete(entity);
    }

    @Override
    public ImpuestoIVA find(Object id) {
        return abmService.find(id, ImpuestoIVA.class);
    }

    @Override
    public List<ImpuestoIVA> findAll() {
        return abmService.findByNamedQuery(ImpuestoIVA.TODOS);
    }

    @Override
    public List<ImpuestoIVA> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
