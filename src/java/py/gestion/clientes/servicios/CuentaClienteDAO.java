/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.servicios;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.clientes.persistencia.CuentaCliente;
import py.gestion.clientes.persistencia.EstadoCuenta;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CuentaClienteDAO extends AbstractDAO<CuentaCliente> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public CuentaCliente create(Cliente cliente) {
        CuentaCliente c = new CuentaCliente();
        c.setCliente(cliente);
        c.setDenominacion(cliente.getPrimerApellido() + ", " + cliente.getPrimerNombre());
        if (!cliente.getDirecciones().isEmpty()) {
            c.setDireccion(cliente.getDirecciones().get(0).getDireccion());
        }
        c.setEstado(EstadoCuenta.ACTIVO);
        c.setFechaAlta(new Date());
        c.setNroCuenta(getSgteNumero());
        c.setTelefono("");
        return create(c);
    }

    @Override
    public CuentaCliente create(CuentaCliente entity) {
        return abmService.create(entity);
    }

    @Override
    public CuentaCliente edit(CuentaCliente entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(CuentaCliente entity) {
        abmService.delete(entity);
    }

    @Override
    public CuentaCliente find(Object id) {
        return abmService.find(id, CuentaCliente.class);
    }

    @Override
    public List<CuentaCliente> findAll() {
        return abmService.findByNamedQuery(CuentaCliente.TODOS);
    }

    public String getSgteNumero() {
        return (CuentaCliente.contador + 1) + "";
    }

    @Override
    public List<CuentaCliente> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
