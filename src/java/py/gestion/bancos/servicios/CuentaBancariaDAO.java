/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.bancos.persistencia.CuentaBancaria;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CuentaBancariaDAO extends AbstractDAO<CuentaBancaria> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public CuentaBancaria create(CuentaBancaria entity) {
        return abmService.create(entity);
    }

    @Override
    public CuentaBancaria edit(CuentaBancaria entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(CuentaBancaria entity) {
        abmService.delete(entity);
    }

    @Override
    public CuentaBancaria find(Object id) {
        return abmService.find(id, CuentaBancaria.class);
    }

    @Override
    public List<CuentaBancaria> findAll() {
        return abmService.findByNamedQuery(CuentaBancaria.TODOS);
    }

    @Override
    public List<CuentaBancaria> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
