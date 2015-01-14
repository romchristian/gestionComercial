/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios;

import py.gestion.compra.servicios.listeners.OrdenCompraListener;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.compra.persistencia.enums.EstadoOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoObsOC;
import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class OrdenCompraDAO extends AbstractDAO<OrdenCompra> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB
    private OrdenCompraListener manejadorEventosOC;

    @Override
    public OrdenCompra create(OrdenCompra entity) {
        abmService.create(entity);
        manejadorEventosOC.agregaEventoCreacion(entity);
        return entity;
    }

    @Override
    public OrdenCompra edit(OrdenCompra entity) {
        return edit(entity, true);
    }

    public OrdenCompra edit(OrdenCompra entity, boolean conEvento) {
        if (conEvento) {
            manejadorEventosOC.agregaEventoModificacion(entity);
        }
        return abmService.update(entity);
    }

    @Override
    public void remove(OrdenCompra entity) {
        abmService.delete(entity);
    }

    @Override
    public OrdenCompra find(Object id) {
        return abmService.find(id, OrdenCompra.class);
    }

    @Override
    public List<OrdenCompra> findAll() {
        return abmService.findByNamedQuery(OrdenCompra.TODOS);
    }

    public List<OrdenCompra> findAllPedientes() {
        return abmService.findByNamedQuery(OrdenCompra.POR_ESTADO, QueryParameter.where("estado", EstadoOC.PENDIENTE_AUTORIZACION).parameters());
    }

    public List<OrdenCompra> findAllAutorizados() {
        return abmService.findByNamedQuery(OrdenCompra.POR_ESTADO, QueryParameter.where("estado", EstadoOC.AUTORIZADO).parameters());
    }

    public List<OrdenCompra> findAllEnProceso() {
        return abmService.findByNamedQuery(OrdenCompra.POR_ESTADO, QueryParameter.where("estado", EstadoOC.EN_PROCESO).parameters());
    }

    public List<OrdenCompra> findAllCerrada() {
        return abmService.findByNamedQuery(OrdenCompra.POR_ESTADO, QueryParameter.where("estado", EstadoOC.CERRADA).parameters());
    }

    public Long countPendientes() {
        Long R = countPorEstado(EstadoOC.PENDIENTE_AUTORIZACION);
        if (R > 0) {
            EstadoOC.PENDIENTE_AUTORIZACION.setColorPendiente("red");
        } else {
            EstadoOC.PENDIENTE_AUTORIZACION.setColorPendiente("black");
        }

        return R;
    }

    public Long countAutorizados() {
        return countPorEstado(EstadoOC.AUTORIZADO);
    }

    public Long countEnProceso() {
        return countPorEstado(EstadoOC.EN_PROCESO);
    }

    public Long countCerradas() {
        return countPorEstado(EstadoOC.CERRADA);
    }

    private Long countPorEstado(EstadoOC estado) {
        return abmService.count("select count(*) from ordencompra where estado = '" + estado.toString() + "'");
    }

    public void agregaObservacion(OrdenCompra oc, String obs) {
        manejadorEventosOC.agregaEventoObservacion(oc, obs);
    }

    public List<EventoObsOC> getObservaciones(OrdenCompra oc) {
        return manejadorEventosOC.getObservaciones(oc);
    }

    public List<EventoOC> getEventos(OrdenCompra oc) {
        return manejadorEventosOC.getEventos(oc);
    }

    public OrdenCompra autoriza(OrdenCompra oc) {
        return cambiaEstado(oc, EstadoOC.AUTORIZADO);
    }

    public OrdenCompra pendiente(OrdenCompra oc) {
        return cambiaEstado(oc, EstadoOC.PENDIENTE_AUTORIZACION);
    }

    public OrdenCompra enproceso(OrdenCompra oc) {
        return cambiaEstado(oc, EstadoOC.EN_PROCESO);
    }

    public OrdenCompra cierra(OrdenCompra oc) {
        return cambiaEstado(oc, EstadoOC.CERRADA);
    }

    public OrdenCompra cambiaEstado(OrdenCompra oc, EstadoOC estado) {
        manejadorEventosOC.agregaEventoCambioEstadoOC(oc, estado);
        oc.setEstado(estado);
        edit(oc, false);
        return oc;
    }

    @Override
    public List<OrdenCompra> findAll(String consulta, QueryParameter parametros) {
        return abmService.findByQuery(consulta, parametros.parameters());
    }

    public List<OrdenCompra> findPedientes(Proveedor p) {
        return abmService.findByNamedQuery(OrdenCompra.POR_PROVEEDOR, QueryParameter.where("proveedor", p).parameters());
    }
}
