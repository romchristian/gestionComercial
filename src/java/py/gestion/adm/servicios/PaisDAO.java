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
import py.gestion.adm.persistencia.Pais;
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
public class PaisDAO extends AbstractDAO<Pais> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    @Override
    public Pais create(Pais entity) {
        return abmService.create(entity);
    }

    @Override
    public Pais edit(Pais entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Pais entity) {
        abmService.delete(entity);
    }

    @Override
    public Pais find(Object id) {
        return abmService.find(id, Pais.class);
    }

    @Override
    public List<Pais> findAll() {
        return abmService.findByNamedQuery(Pais.TODOS);
    }

    @Override
    public List<Pais> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
