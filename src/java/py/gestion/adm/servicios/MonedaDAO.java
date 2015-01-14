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
import py.gestion.adm.persistencia.Estado;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MonedaDAO extends AbstractDAO<Moneda> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public Moneda find(String nombre) {
        return abmService.findByNamedQuerySingleResult(Moneda.POR_NOMBRE,
                where("nombre", nombre).parameters());
    }

    public List<Moneda> findAllActivo() {
        return abmService.findByNamedQuery(Moneda.POR_ESTADO,
                where("estado", Estado.ACTIVO).parameters());
    }

    @Override
    public Moneda create(Moneda entity) {
        return abmService.create(entity);
    }

    @Override
    public Moneda edit(Moneda entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Moneda entity) {
        abmService.delete(entity);
    }

    @Override
    public Moneda find(Object id) {
        return abmService.find(id, Moneda.class);
    }

    @Override
    public List<Moneda> findAll() {
        return abmService.findByNamedQuery(Moneda.TODOS);
    }

    @Override
    public List<Moneda> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
