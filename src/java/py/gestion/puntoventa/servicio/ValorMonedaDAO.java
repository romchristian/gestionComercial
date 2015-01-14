/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.servicio;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.puntoventa.persisitencia.ValorMoneda;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ValorMonedaDAO extends AbstractDAO<ValorMoneda> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public ValorMoneda create(ValorMoneda entity) {
        return abmService.create(entity);
    }

    @Override
    public ValorMoneda edit(ValorMoneda entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(ValorMoneda entity) {
        abmService.delete(entity);
    }

    @Override
    public ValorMoneda find(Object id) {
        return abmService.find(id, ValorMoneda.class);
    }

    @Override
    public List<ValorMoneda> findAll() {
        return abmService.getEM().createQuery("select obj from ValorMoneda obj").getResultList();
    }

    @Override
    public List<ValorMoneda> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
