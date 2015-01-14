/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.clientes.persistencia.DetCuentaCliente;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class DetCuentaClienteDAO extends AbstractDAO<DetCuentaCliente> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public DetCuentaCliente create(DetCuentaCliente entity) {
        return abmService.create(entity);
    }

    @Override
    public DetCuentaCliente edit(DetCuentaCliente entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(DetCuentaCliente entity) {
        abmService.delete(entity);
    }

    @Override
    public DetCuentaCliente find(Object id) {
        return abmService.find(id, DetCuentaCliente.class);
    }

    @Override
    public List<DetCuentaCliente> findAll() {
        return null;
    }

    @Override
    public List<DetCuentaCliente> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
