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
import py.gestion.puntoventa.persisitencia.Secuencia;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SecuenciaDAO extends AbstractDAO<Secuencia> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Secuencia create(Secuencia entity) {
        return abmService.create(entity);
    }

    @Override
    public Secuencia edit(Secuencia entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Secuencia entity) {
        abmService.delete(entity);
    }

    @Override
    public Secuencia find(Object id) {
        return abmService.find(id, Secuencia.class);
    }

    @Override
    public List<Secuencia> findAll() {
        return abmService.getEM().createQuery("select obj from Secuencia obj").getResultList();
    }

    @Override
    public List<Secuencia> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
