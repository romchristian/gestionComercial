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
import py.gestion.adm.persistencia.Departamento;
import py.gestion.adm.persistencia.Distrito;
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
public class DistritoDAO extends AbstractDAO<Distrito> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    @Override
    public Distrito create(Distrito entity) {
        return abmService.create(entity);
    }

    @Override
    public Distrito edit(Distrito entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Distrito entity) {
        abmService.delete(entity);
    }

    @Override
    public Distrito find(Object id) {
        return abmService.find(id, Distrito.class);
    }

    @Override
    public List<Distrito> findAll() {
        return abmService.findByNamedQuery(Distrito.TODOS);
    }

    public List<Distrito> findAll(Departamento departamento) {
        return abmService.findByNamedQuery(Distrito.POR_DEPARTAMENTO, QueryParameter.where("departamento", departamento).parameters());
    }

    @Override
    public List<Distrito> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
