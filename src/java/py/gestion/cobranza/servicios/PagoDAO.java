/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.cobranza.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.cobranza.persistencia.Pago;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PagoDAO extends AbstractDAO<Pago> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Pago create(Pago entity) {
        return abmService.create(entity);
    }

    @Override
    public Pago edit(Pago entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Pago entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Pago find(Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pago> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Pago> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
