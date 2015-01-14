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
import javax.inject.Inject;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ClienteDAO extends AbstractDAO<Cliente> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB
    private CuentaClienteDAO cuentaClienteDAO;
    @Inject
    private Credencial credencial;

    @Override
    public Cliente create(Cliente entity) {
        entity.setEmpresa(credencial.getEmpresa());
        entity.setSucursal(credencial.getSucursal());
        abmService.create(entity);
        cuentaClienteDAO.create(entity);
        return entity;
    }

    @Override
    public Cliente edit(Cliente entity) {
        entity.setEmpresa(credencial.getEmpresa());
        entity.setSucursal(credencial.getSucursal());
        return abmService.update(entity);
    }

    @Override
    public void remove(Cliente entity) {
        abmService.delete(entity);
    }

    @Override
    public Cliente find(Object id) {
        return abmService.find(id, Cliente.class);
    }

    @Override
    public List<Cliente> findAll() {
        return abmService.findByNamedQuery(Cliente.TODOS);
    }

    @Override
    public List<Cliente> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
