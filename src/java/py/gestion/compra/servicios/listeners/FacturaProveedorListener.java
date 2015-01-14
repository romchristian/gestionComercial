/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios.listeners;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import py.gestion.compra.persistencia.eventos.factura.EventoFPConformacion;
import py.gestion.compra.persistencia.eventos.factura.EventoFPCreacion;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;

/**
 *
 * @author cromero
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FacturaProveedorListener {

    @EJB(beanName="ABMServiceBean")
    private ABMService abms;
    @Inject
    private Credencial credencial;
    
    public void escuchaEventoCreacion(@Observes(during= TransactionPhase.AFTER_SUCCESS) EventoFPCreacion evento) {
        //evento.setUsuario(credencial.getUsuario());
        evento.setNombre("CREACIÓN");
        abms.create(evento);
    }
    
    public void escuchaEventoConformacion(@Observes(during= TransactionPhase.AFTER_SUCCESS) EventoFPConformacion evento) {
        //evento.setUsuario(credencial.getUsuario());
        evento.setNombre("De " + evento.getEstadoAnterior() + " a " + evento.getFacturaProveedor().getEstado());
        abms.create(evento);
    }
}
