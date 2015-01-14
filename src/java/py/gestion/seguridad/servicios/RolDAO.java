/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.servicios;

import py.gestion.seguridad.persistencia.Rol;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.*;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class RolDAO extends AbstractDAO<Rol> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Rol create(Rol obj) {
        return abmService.create(obj);
    }

    @Override
    public Rol edit(Rol obj) {
        return abmService.update(obj);
    }

    @Override
    public Rol find(Object id) {
        return abmService.find(id, Rol.class);
    }

    @Override
    public List<Rol> findAll() {
        return abmService.findByNamedQuery(Rol.TODOS);
    }

    @Override
    public void remove(Rol entity) {
        abmService.delete(entity);
    }

    public Rol find(String nombre) {
        return abmService.findByNamedQuerySingleResult(Rol.POR_NOMBRE,
                where("nombre", nombre).
                parameters());
    }

    @Override
    public List<Rol> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
