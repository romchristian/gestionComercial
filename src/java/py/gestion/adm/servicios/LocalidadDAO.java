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
import javax.inject.Inject;
import py.gestion.adm.persistencia.Localidad;
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
public class LocalidadDAO extends AbstractDAO<Localidad> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    @Override
    public Localidad create(Localidad entity) {
        return abmService.create(entity);
    }

    @Override
    public Localidad edit(Localidad entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Localidad entity) {
        abmService.delete(entity);
    }

    @Override
    public Localidad find(Object id) {
        return abmService.find(id, Localidad.class);
    }

    @Override
    public List<Localidad> findAll() {
        return abmService.findByNamedQuery(Localidad.TODOS);
    }

    @Override
    public List<Localidad> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
