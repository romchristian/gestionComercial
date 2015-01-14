/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.servicios;

import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.pagos.persistencia.OrdenPago;
import py.gestion.pagos.persistencia.EstadoOrdenPago;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class OrdenPagoDAO extends AbstractDAO<OrdenPago> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public OrdenPago create(OrdenPago entity) {
        return abmService.create(entity);
    }

    public EstadoOrdenPago create(EstadoOrdenPago entity) {
        abmService.getEM().persist(entity);
        return entity;
    }

    @Override
    public OrdenPago edit(OrdenPago entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(OrdenPago entity) {
        abmService.delete(entity);
    }

    @Override
    public OrdenPago find(Object id) {
        return abmService.find(id, OrdenPago.class);
    }

    @Override
    public List<OrdenPago> findAll() {
        return abmService.findByNamedQuery(OrdenPago.TODOS);
    }

    @Override
    public List<OrdenPago> findAll(String consulta, QueryParameter parametros) {
        return abmService.findByQuery(consulta, parametros.parameters());
    }

    public EstadoOrdenPago findEstado(Object id) {
        return abmService.find(id, EstadoOrdenPago.class);
    }

    public List<EstadoOrdenPago> findAllEstados() {
        return abmService.findByQuery("SELECT e from EstadoOrdenPago e", new HashMap<String, Object>());
    }
}
