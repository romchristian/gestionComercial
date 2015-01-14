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
import py.gestion.bancos.persistencia.TransaccionBancaria;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class TransaccionBancariaDAO extends AbstractDAO<TransaccionBancaria> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB
    private SaldoRealHistoricoDAO saldoRealHistoricoDAO;

    public TransaccionBancaria createInicial(TransaccionBancaria entity) {
        create(entity);
        saldoRealHistoricoDAO.createHistorico(entity);
        return entity;
    }

    public TransaccionBancaria createAjuste(TransaccionBancaria entity) {
        create(entity);
        saldoRealHistoricoDAO.createHistorico(entity);
        return entity;
    }

    public TransaccionBancaria concilia(TransaccionBancaria transaccionBancaria) {
        saldoRealHistoricoDAO.createHistorico(transaccionBancaria);
        edit(transaccionBancaria);
        return transaccionBancaria;
    }

    @Override
    public TransaccionBancaria create(TransaccionBancaria entity) {
        return abmService.create(entity);
    }

    @Override
    public TransaccionBancaria edit(TransaccionBancaria entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(TransaccionBancaria entity) {
        abmService.delete(entity);
    }

    @Override
    public TransaccionBancaria find(Object id) {
        return abmService.find(id, TransaccionBancaria.class);
    }

    @Override
    public List<TransaccionBancaria> findAll() {
        return abmService.findByNamedQuery(TransaccionBancaria.TODOS);
    }

    @Override
    public List<TransaccionBancaria> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
