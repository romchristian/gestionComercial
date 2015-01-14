/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.servicio;

import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.puntoventa.persisitencia.SesionTPV;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SesionTPVDAO extends AbstractDAO<SesionTPV> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    

    @Override
    public SesionTPV create(SesionTPV entity) {
        return abmService.create(entity);
    }

    @Override
    public SesionTPV edit(SesionTPV entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(SesionTPV entity) {
        abmService.delete(entity);
    }

    @Override
    public SesionTPV find(Object id) {
        return abmService.find(id, SesionTPV.class);
    }

    @Override
    public List<SesionTPV> findAll() {
        return abmService.getEM().createQuery("select obj from SesionTPV obj").getResultList();
    }

    @Override
    public List<SesionTPV> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
