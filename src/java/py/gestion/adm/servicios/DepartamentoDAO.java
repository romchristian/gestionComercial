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
public class DepartamentoDAO extends AbstractDAO<Departamento> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    @Override
    public Departamento create(Departamento entity) {
        return abmService.create(entity);
    }

    @Override
    public Departamento edit(Departamento entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Departamento entity) {
        abmService.delete(entity);
    }

    @Override
    public Departamento find(Object id) {
        return abmService.find(id, Departamento.class);
    }

    @Override
    public List<Departamento> findAll() {
        return abmService.findByNamedQuery(Departamento.TODOS);
    }

    @Override
    public List<Departamento> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
