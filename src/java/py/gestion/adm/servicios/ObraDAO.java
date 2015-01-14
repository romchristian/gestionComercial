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
import javax.enterprise.event.Event;
import javax.inject.Inject;
import py.gestion.adm.persistencia.Obra;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ObraDAO extends AbstractDAO<Obra> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;
    @Inject
    private Event<ObraEvento> evento;

    @Override
    public Obra create(Obra entity) {
        entity.setEmpresa(credencial.getEmpresa());
        entity.setSucursal(credencial.getSucursal());
        abmService.create(entity);
        evento.fire(new ObraEvento("Creación", entity));
        return entity;
    }

    @Override
    public Obra edit(Obra entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Obra entity) {
        abmService.delete(entity);
    }

    @Override
    public Obra find(Object id) {
        return abmService.find(id, Obra.class);
    }

    @Override
    public List<Obra> findAll() {
        return abmService.findByNamedQuery(Obra.TODOS);
    }

    @Override
    public List<Obra> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
