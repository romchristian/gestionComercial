/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.servicios;

import java.util.*;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.bancos.persistencia.CuentaBancaria;
import py.gestion.bancos.persistencia.SaldoRealHistorico;
import py.gestion.bancos.persistencia.TransaccionBancaria;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SaldoRealHistoricoDAO extends AbstractDAO<SaldoRealHistorico> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public SaldoRealHistorico getSaldoRealActual(CuentaBancaria cuenta) {

        return abmService.findByNamedQuerySingleResult(SaldoRealHistorico.ACTUAL,
                where("cuentaBancaria", cuenta).
                and("fecha", new Date()).parameters());
    }

    public void createHistorico(TransaccionBancaria transaccion) {
        SaldoRealHistorico saldoRealHistoricoActual = getSaldoRealActual(transaccion.getCuentaBancaria());

        double saldoReal = saldoRealHistoricoActual != null ? saldoRealHistoricoActual.getSaldo() : 0d;
        saldoReal -= transaccion.getDebito();
        saldoReal += transaccion.getCredito();
        Date fecha = new Date();
        if (saldoRealHistoricoActual != null) {
            saldoRealHistoricoActual.setHasta(fecha);
            edit(saldoRealHistoricoActual);
        }
        SaldoRealHistorico nuevoSaldo = new SaldoRealHistorico();
        nuevoSaldo.setCuentaBancaria(transaccion.getCuentaBancaria());
        nuevoSaldo.setDesde(fecha);

        GregorianCalendar gc = new GregorianCalendar(new Locale("es", "py"));
        gc.setTime(fecha);
        gc.add(Calendar.YEAR, 100);
        nuevoSaldo.setHasta(gc.getTime());
        nuevoSaldo.setSaldo(saldoReal);

        create(nuevoSaldo);
    }

    @Override
    public SaldoRealHistorico create(SaldoRealHistorico entity) {
        return abmService.create(entity);
    }

    @Override
    public SaldoRealHistorico edit(SaldoRealHistorico entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(SaldoRealHistorico entity) {
        abmService.delete(entity);
    }

    @Override
    public SaldoRealHistorico find(Object id) {
        return abmService.find(id, SaldoRealHistorico.class);
    }

    @Override
    public List<SaldoRealHistorico> findAll() {
        return abmService.findByNamedQuery(SaldoRealHistorico.TODOS);
    }

    @Override
    public List<SaldoRealHistorico> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
