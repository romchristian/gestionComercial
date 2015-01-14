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
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.stock.persistencia.Color;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */


@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ColorDAO extends AbstractDAO<Color> {
    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;

    @Override
    public Color create(Color entity) {
        return abmService.create(entity);
    }

    @Override
    public Color edit(Color entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Color entity) {
        abmService.delete(entity);
    }

    @Override
    public Color find(Object id) {
        return abmService.find(id, Color.class);
    }

    @Override
    public List<Color> findAll() {
        return abmService.findByNamedQuery(Color.TODOS);
    }
    
    @Override
    public List<Color> findAll(String consulta, QueryParameter parametros) {
        return abmService.findByQuery(consulta, parametros.parameters());
    }
}
