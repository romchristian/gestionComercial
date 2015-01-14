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
import py.gestion.adm.persistencia.Cotizacion;
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
public class CotizacionDAO extends AbstractDAO<Cotizacion> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public List<Cotizacion> findUltimo(Moneda moneda) {
        System.out.println("MONEDA ELEGIDA : " + moneda);
        return abmService.findByNamedQuery(Cotizacion.ULTIMO, where("moneda", moneda).parameters(), 1);
    }

    @Override
    public Cotizacion create(Cotizacion entity) {
        return abmService.create(entity);
    }

    @Override
    public Cotizacion edit(Cotizacion entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Cotizacion entity) {
        abmService.delete(entity);
    }

    @Override
    public Cotizacion find(Object id) {
        return abmService.find(id, Cotizacion.class);
    }

    @Override
    public List<Cotizacion> findAll() {
        return abmService.findByNamedQuery(Cotizacion.TODOS);
    }

    @Override
    public List<Cotizacion> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
