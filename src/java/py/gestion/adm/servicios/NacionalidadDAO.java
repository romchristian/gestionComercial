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
import py.gestion.adm.persistencia.Nacionalidad;
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
public class NacionalidadDAO extends AbstractDAO<Nacionalidad> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    @Override
    public Nacionalidad create(Nacionalidad entity) {
        return abmService.create(entity);
    }

    @Override
    public Nacionalidad edit(Nacionalidad entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Nacionalidad entity) {
        abmService.delete(entity);
    }

    @Override
    public Nacionalidad find(Object id) {
        return abmService.find(id, Nacionalidad.class);
    }

    @Override
    public List<Nacionalidad> findAll() {
        return abmService.findByNamedQuery(Nacionalidad.TODOS);
    }

    @Override
    public List<Nacionalidad> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
