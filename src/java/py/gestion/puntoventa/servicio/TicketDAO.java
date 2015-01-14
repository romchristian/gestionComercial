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
import javax.enterprise.event.Event;
import javax.inject.Inject;
import py.gestion.puntoventa.persisitencia.Ticket;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TicketDAO extends AbstractDAO<Ticket> {

    @Inject
    private Event<EventoCreacionTicket> evento;
    
    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Ticket create(Ticket entity) {
        abmService.create(entity);
        
        evento.fire(new EventoCreacionTicket(entity));
        
        return entity;
    }

    @Override
    public Ticket edit(Ticket entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Ticket entity) {
        abmService.delete(entity);
    }

    @Override
    public Ticket find(Object id) {
        return abmService.find(id, Ticket.class);
    }

    @Override
    public List<Ticket> findAll() {
        return abmService.getEM().createQuery("select obj from Ticket obj").getResultList();
    }

    @Override
    public List<Ticket> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
