/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.servicio;

import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import py.gestion.puntoventa.persisitencia.SesionTPV;
import py.gestion.puntoventa.persisitencia.Ticket;
import py.gestion.puntoventa.persisitencia.TipoTransaccion;
import py.gestion.puntoventa.persisitencia.Transaccion;

/**
 *
 * @author emelgarejo
 */
@Stateless
public class TransaccionServicio {

    @EJB
    private TransaccionDAO transaccionDAO;
    
    public void agregarTiket(@Observes(during= TransactionPhase.AFTER_SUCCESS)EventoCreacionTicket evento){    
        //solo para ticket
        Transaccion t=new Transaccion();
        t.setDescripcion("Ticket "+evento.getTicket().getId());
        t.setMonto(evento.getTicket().getTotal());
        t.setSesionTPV(evento.getTicket().getSesionTPV());
        t.setTipoTransaccion(TipoTransaccion.VENTA);
        transaccionDAO.create(t);
    
    }
    
    public void sacarDinero(SesionTPV sesionTPV, String descripcion, BigDecimal monto){
    
        //sacar dinero de la caja con un comprobante
        Transaccion t=new Transaccion();
        t.setDescripcion(descripcion);
        t.setMonto(monto.multiply(new BigDecimal(-1)));
        t.setSesionTPV(sesionTPV);
        t.setTipoTransaccion(TipoTransaccion.SALIDA);
        transaccionDAO.create(t);
    }
    
    public void ponerDinero(SesionTPV sesionTPV, String descripcion, BigDecimal monto){
    
        //poner dinero a la caja
        Transaccion t=new Transaccion();
        t.setDescripcion(descripcion);
        t.setMonto(monto);
        t.setSesionTPV(sesionTPV);
        t.setTipoTransaccion(TipoTransaccion.ENTRADA);
        transaccionDAO.create(t);
    }

}
