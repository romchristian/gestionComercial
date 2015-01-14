/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.servicio;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.puntoventa.persisitencia.Diario;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
*/
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DiarioDAO extends AbstractDAO<Diario> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Diario create(Diario entity) {
        return abmService.create(entity);
    }

    @Override
    public Diario edit(Diario entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Diario entity) {
        abmService.delete(entity);
    }

    @Override
    public Diario find(Object id) {
        return abmService.find(id, Diario.class);
    }

    @Override
    public List<Diario> findAll() {
        return abmService.getEM().createQuery("select obj from Diario obj").getResultList();
    }

    @Override
    public List<Diario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
