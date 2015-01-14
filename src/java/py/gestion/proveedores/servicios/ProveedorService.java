/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.servicios;

import py.gestion.proveedores.persistencia.eventos.proveedor.ProveedorEvento;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProveedorService extends AbstractDAO<Proveedor> {

    @Inject
    private Event<ProveedorEvento> evento;
    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Proveedor create(Proveedor entity) {
        abmService.create(entity);
        disparaEvento(entity);
        return entity;
    }

    @Override
    public Proveedor edit(Proveedor entity) {
        abmService.update(entity);
        disparaEvento(entity);
        return entity;
    }

    @Override
    public void remove(Proveedor entity) {
        abmService.delete(entity);
        disparaEvento(entity);
    }

    @Override
    public Proveedor find(Object id) {
        return abmService.find(id, Proveedor.class);
    }

    public Proveedor find(String nombre) {
        return abmService.findByNamedQuerySingleResult(Proveedor.POR_NOMBRE, QueryParameter.where("nombre", nombre).parameters());
    }

    @Override
    public List<Proveedor> findAll() {
        return abmService.findByNamedQuery(Proveedor.TODOS);
    }

    private void disparaEvento(Proveedor p) {
        evento.fire(new ProveedorEvento("Evento proveedor", p));
    }

    @Override
    public List<Proveedor> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
