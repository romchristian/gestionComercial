/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios.listeners;

import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.compra.persistencia.enums.EstadoOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoCreacionOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoCambioEstadoOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoModificacionOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoObsOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoOC;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Named
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class OrdenCompraListener implements Serializable {

    @Inject
    private Credencial credencial;
    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;

    public void agregaEventoCreacion(OrdenCompra oc) {
        EventoCreacionOC e = new EventoCreacionOC();
       // e.setUsuario(credencial.getUsuario());
        e.setNombre("CREACIÓN OC");
        e.setOrdenCompra(oc);
        abmService.create(e);
    }
    
    public void agregaEventoModificacion(OrdenCompra oc) {
        EventoModificacionOC e = new EventoModificacionOC();
        //e.setUsuario(credencial.getUsuario());
        e.setNombre("Modificación OC");
        e.setOrdenCompra(oc);
        abmService.create(e);
    }
    public void agregaEventoCambioEstadoOC(OrdenCompra oc, EstadoOC estado) {
        EventoCambioEstadoOC e = new EventoCambioEstadoOC();
        //e.setUsuario(credencial.getUsuario());
        e.setNombre("Cambio de estado de " + oc.getEstado().toString() + " a " + estado.toString());
        e.setOrdenCompra(oc);
        abmService.create(e);
    }
    

    public void agregaEventoAutorizacion(OrdenCompra oc) {
    }

    public void agregaEventoCierre(OrdenCompra oc) {
    }

    public void agregaEventoObservacion(OrdenCompra oc, String obs) {
        EventoObsOC e = new EventoObsOC();
        //e.setUsuario(credencial.getUsuario());
        e.setNombre("OBS OC:");
        e.setOrdenCompra(oc);
        e.setObs(obs);
        abmService.create(e);
    }

    public List<EventoObsOC> getObservaciones(OrdenCompra oc) {
        return abmService.findByNamedQuery(EventoObsOC.TODOS, QueryParameter.where("ordenCompra", oc).parameters());
    }
    
    public List<EventoOC> getEventos(OrdenCompra oc) {
        return abmService.findByNamedQuery(EventoOC.TODOS, QueryParameter.where("ordenCompra", oc).parameters());
    }
}
