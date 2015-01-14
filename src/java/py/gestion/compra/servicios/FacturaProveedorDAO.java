/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import py.gestion.compra.persistencia.DetFacturaProveedor;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.eventos.factura.EventoFP;
import py.gestion.compra.persistencia.eventos.factura.EventoFPConformacion;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.compra.persistencia.eventos.factura.EventoFPCreacion;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FacturaProveedorDAO extends AbstractDAO<FacturaProveedor> {

    @Inject
    private Event<EventoFPCreacion> eventoCrecion;
    @Inject
    private Event<EventoFP> eventoConformacion;
    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public FacturaProveedor create(FacturaProveedor entity) {
        calculaTotales(entity);

        abmService.create(entity);
        eventoCrecion.fire(new EventoFPCreacion(entity));
        return entity;
    }

    @Override
    public FacturaProveedor edit(FacturaProveedor entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(FacturaProveedor entity) {
        abmService.delete(entity);
    }

    @Override
    public FacturaProveedor find(Object id) {
        return abmService.find(id, FacturaProveedor.class);
    }

    @Override
    public List<FacturaProveedor> findAll() {
        return abmService.findByNamedQuery(FacturaProveedor.TODOS);
    }

    public List<FacturaProveedor> findAllEstado(EstadoFacturaProveedor estado) {
        return abmService.findByNamedQuery(FacturaProveedor.POR_ESTADO, QueryParameter.where("estado", estado).parameters());
    }
    
    public List<FacturaProveedor> findAllEstadoProveedor(EstadoFacturaProveedor estado, Proveedor proveedor) {
        return abmService.findByNamedQuery(FacturaProveedor.POR_ESTADO_PROVEEDOR, QueryParameter.where("estado", estado).and("proveedor", proveedor).parameters());
    }

    public Long countPorEstado(EstadoFacturaProveedor estado) {
        return abmService.count("select count(*) from facturaproveedor where estado = '" + estado.toString() + "'");
    }

    public FacturaProveedor conforma(FacturaProveedor entity) {
        eventoConformacion.fire(new EventoFPConformacion(entity, entity.getEstado()));
        entity.setEstado(EstadoFacturaProveedor.CONFORMADA);
        edit(entity);
        return entity;
    }

    @Override
    public List<FacturaProveedor> findAll(String consulta, QueryParameter parametros) {
        return abmService.findByQuery(consulta, parametros.parameters());
    }

    private void calculaTotales(FacturaProveedor entity) {
        RoundingMode RM = RoundingMode.HALF_EVEN;
        List<DetFacturaProveedor> detalles = entity.getDetalles();
        BigDecimal gravada10 = new BigDecimal(0d);
        BigDecimal gravada05 = new BigDecimal(0d);
        BigDecimal exenta = new BigDecimal(0d);
        BigDecimal iva05;
        BigDecimal iva10;
        BigDecimal ivaTotal;
        BigDecimal total;

        for (DetFacturaProveedor d : detalles) {
            System.out.println("TOTAL : " + d.getGravada10());
            d.setGravada10(d.getGravada10().setScale(entity.getMoneda().getDecimales(), RM));
            d.setGravada05(d.getGravada05().setScale(entity.getMoneda().getDecimales(), RM));
            d.setExenta(d.getExenta().setScale(entity.getMoneda().getDecimales(), RM));
            d.setMonto(d.getMonto().setScale(entity.getMoneda().getDecimales(), RM));
            d.setSubtotal(d.getSubtotal().setScale(entity.getMoneda().getDecimales(), RM));
            System.out.println("TOTAL Despues: " + d.getGravada10());

            gravada10 = gravada10.add(d.getGravada10()).setScale(entity.getMoneda().getDecimales(), RM);
            gravada05 = gravada05.add(d.getGravada05()).setScale(entity.getMoneda().getDecimales(), RM);
            exenta = exenta.add(d.getExenta()).setScale(entity.getMoneda().getDecimales(), RM);
        }


        iva05 = gravada05.divide(new BigDecimal(21), entity.getMoneda().getDecimales(), RM).setScale(entity.getMoneda().getDecimales(), RM);
        iva10 = gravada10.divide(new BigDecimal(11), entity.getMoneda().getDecimales(), RM).setScale(entity.getMoneda().getDecimales(), RM);
        ivaTotal = iva05.add(iva10).setScale(entity.getMoneda().getDecimales(), RM).setScale(entity.getMoneda().getDecimales(), RM);
        total = exenta.add(gravada05).add(gravada10).setScale(entity.getMoneda().getDecimales(), RM).setScale(entity.getMoneda().getDecimales(), RM);

        entity.setTotalgravada(new BigDecimal(0).add(gravada05).add(gravada10).setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotalgravada05(gravada05.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotalgravada10(gravada10.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotalexenta(exenta.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotal(total.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotaliva(ivaTotal.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotaliva05(iva05.setScale(entity.getMoneda().getDecimales(), RM));
        entity.setTotaliva10(iva10.setScale(entity.getMoneda().getDecimales(), RM));
    }

    public boolean hayDocumento(ProveedorTimbrado timbrado, String nro) {
        boolean R = false;
        System.out.println("Factura NRO REC: " + nro);
        try {
            FacturaProveedor f = abmService
                    .findByNamedQuerySingleResult(FacturaProveedor.POR_NUMERO,
                    QueryParameter.where("timbrado", timbrado.getTimbrado() + "")
                    .and("codest", timbrado.getCodEst())
                    .and("codsuc", timbrado.getCodSuc())
                    .and("numero", nro)
                    .parameters());
            if (f != null) {
                System.out.println("Factura : " + f.getNumero());
                R = true;
            }
        } catch (Exception e) {
        }

        System.out.println("R : " + R);
        return R;
    }
}
