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
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EmpresaDAO extends AbstractDAO<Empresa> {

    @Inject
    private Credencial credencial;
    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public Empresa find(String razonSocial) {
        return abmService.findByNamedQuerySingleResult(Empresa.POR_RAZON_SOCIAL,
                where("razonSocial", razonSocial).parameters());
    }

    @Override
    public Empresa create(Empresa entity) {
        return abmService.create(entity);
    }

    @Override
    public Empresa edit(Empresa entity) {
        if (entity.equals(credencial.getEmpresa())) {
            credencial.setEmpresa(entity);
        }
        return abmService.update(entity);
    }

    @Override
    public void remove(Empresa entity) {
        abmService.delete(entity);
    }

    @Override
    public Empresa find(Object id) {
        return abmService.find(id, Empresa.class);
    }

    @Override
    public List<Empresa> findAll() {
        return abmService.findByNamedQuery(Empresa.TODOS);
    }

    @Override
    public List<Empresa> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
