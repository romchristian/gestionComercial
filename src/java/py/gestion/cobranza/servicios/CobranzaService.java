/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.cobranza.servicios;

import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.clientes.servicios.DetCuentaClienteDAO;
import py.gestion.clientes.persistencia.OperacionCobroCuota;
import py.gestion.cobranza.persistencia.CobroCuota;
import py.gestion.cobranza.persistencia.DetCobroCuota;
import py.gestion.utils.servicios.ABMService;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CobranzaService {

    @EJB
    private DetCuentaClienteDAO detCuentaClienteDAO;
    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;

    public CobroCuota create(CobroCuota cobro) {
        CobroCuota R = abmService.create(cobro);
        for (DetCobroCuota d : cobro.getDetalles()) {
            OperacionCobroCuota cc = new OperacionCobroCuota(d);
            cc.setFecha(new Date());
            detCuentaClienteDAO.create(cc);
            if (!d.getDetPrestamo().afectaSaldoCuota(d.getMonto())) {
                throw new RuntimeException("El monto no puede ser mayor al saldo de la cuota");
            }

        }

        return R;
    }
}
