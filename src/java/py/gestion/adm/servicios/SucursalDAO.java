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
import py.gestion.adm.persistencia.Sucursal;
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
public class SucursalDAO extends AbstractDAO<Sucursal> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;

    public List<Sucursal> findAllPorEmpresa() {
        return abmService.findByNamedQuery(Sucursal.POR_EMPRESA,
                where("empresa", credencial.getEmpresa()).parameters());
    }

    @Override
    public Sucursal create(Sucursal entity) {
        return abmService.create(entity);
    }

    @Override
    public Sucursal edit(Sucursal entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Sucursal entity) {
        abmService.delete(entity);
    }

    @Override
    public Sucursal find(Object id) {
        return abmService.find(id, Sucursal.class);
    }

    @Override
    public List<Sucursal> findAll() {
        return abmService.findByNamedQuery(Sucursal.TODOS);
    }

    @Override
    public List<Sucursal> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
