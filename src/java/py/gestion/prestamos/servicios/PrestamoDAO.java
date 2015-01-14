/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.prestamos.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.clientes.servicios.DetCuentaClienteDAO;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.clientes.persistencia.CuentaCliente;
import py.gestion.clientes.persistencia.OperacionDesembolsoPrestamo;
import py.gestion.prestamos.persistencia.EstadoPrestamo;
import py.gestion.prestamos.persistencia.Prestamo;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class PrestamoDAO extends AbstractDAO<Prestamo> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB
    private DetCuentaClienteDAO detCuentaClienteDAO;

    @Override
    public Prestamo create(Prestamo entity) {
        abmService.create(entity);
        return entity;
    }

    @Override
    public Prestamo edit(Prestamo entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Prestamo entity) {
        abmService.delete(entity);
    }

    @Override
    public Prestamo find(Object id) {
        return abmService.find(id, Prestamo.class);
    }

    public List<Prestamo> findAll(Cliente cliente) {
        return abmService.findByNamedQuery(Prestamo.POR_CLIENTE,
                QueryParameter.where("cliente", cliente).parameters());
    }

    @Override
    public List<Prestamo> findAll() {
        return abmService.findByNamedQuery(Prestamo.TODOS);
    }

    public Prestamo desembolsa(Prestamo prestamo) {

        OperacionDesembolsoPrestamo op = new OperacionDesembolsoPrestamo(prestamo);

        CuentaCliente cc = abmService.findByNamedQuerySingleResult(CuentaCliente.POR_CLIENTE,
                QueryParameter.where("cliente", prestamo.getCliente()).parameters());

        op.setCuentaCliente(cc);
        //HACER: La fecha del desembolso debe ser igual a la fecha del prestamo
        op.setFecha(prestamo.getFecha());


        detCuentaClienteDAO.create(op);

        prestamo.setEstado(EstadoPrestamo.DESEMBOLSADO);
        edit(prestamo);

        return prestamo;
    }

    @Override
    public List<Prestamo> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
