/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.stock.persistencia.Producto;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProductoDAO extends AbstractDAO<Producto> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Producto create(Producto entity) {
        return abmService.create(entity);
    }

    @Override
    public Producto edit(Producto entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Producto entity) {
        abmService.delete(entity);
    }

    @Override
    public Producto find(Object id) {
        return abmService.find(id, Producto.class);
    }

    @Override
    public List<Producto> findAll() {
        return abmService.findByNamedQuery(Producto.TODOS);
    }

    public Producto findPorCodigoEstricto(String codigo) {
        return abmService.findByNamedQuerySingleResult(Producto.POR_CODIGO,
                where("codigo", codigo).parameters());
    }

    @Override
    public List<Producto> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
