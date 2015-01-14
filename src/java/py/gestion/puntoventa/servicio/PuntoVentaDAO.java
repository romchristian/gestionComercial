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
import py.gestion.puntoventa.persisitencia.PuntoVenta;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PuntoVentaDAO extends AbstractDAO<PuntoVenta> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public PuntoVenta create(PuntoVenta entity) {
        return abmService.create(entity);
    }

    @Override
    public PuntoVenta edit(PuntoVenta entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(PuntoVenta entity) {
        abmService.delete(entity);
    }

    @Override
    public PuntoVenta find(Object id) {
        return abmService.find(id, PuntoVenta.class);
    }

    @Override
    public List<PuntoVenta> findAll() {
        return abmService.getEM().createQuery("SELECT p from PuntoVenta p").getResultList();
    }

    @Override
    public List<PuntoVenta> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
