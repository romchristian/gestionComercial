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
import py.gestion.puntoventa.persisitencia.MetodoPago;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MetodoPagoDAO extends AbstractDAO<MetodoPago> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public MetodoPago create(MetodoPago entity) {
        return abmService.create(entity);
    }

    @Override
    public MetodoPago edit(MetodoPago entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(MetodoPago entity) {
        abmService.delete(entity);
    }

    @Override
    public MetodoPago find(Object id) {
        return abmService.find(id, MetodoPago.class);
    }

    @Override
    public List<MetodoPago> findAll() {
        return abmService.getEM().createQuery("select obj from MetodoPago obj").getResultList();
    }

    @Override
    public List<MetodoPago> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
