/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.web.CotizacionBean;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.compra.servicios.FacturaProveedorDAO;
import py.gestion.pagos.persistencia.DetOrdenPago;
import py.gestion.pagos.persistencia.OrdenPago;
import py.gestion.pagos.persistencia.EstadoOrdenPago;
import py.gestion.pagos.persistencia.filtros.ordenPago.FiltroFechaDocOP;
import py.gestion.pagos.persistencia.filtros.ordenPago.FiltroProveedorOP;
import py.gestion.pagos.servicios.OrdenPagoDAO;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class OrdenPagoBean extends BeanGenerico<OrdenPago> {

    @EJB
    private OrdenPagoDAO ejb;
    private boolean habilitaCotizacion;
    private Cotizacion cotizacion;
    @Inject
    private CotizacionBean cotizacionBean;
    private SelectItem[] cotizaciones;
    @EJB
    private FacturaProveedorDAO facturaProveedorDAO;
    private List<FacturaProveedor> facturaProveedorSmall;
    private FacturaProveedor[] facturaProveedorSeleccionadas;

    @Override
    public AbstractDAO<OrdenPago> getEjb() {
        return ejb;
    }

    @Override
    public OrdenPago getNuevo() {
        this.facturaProveedorSmall = new ArrayList<FacturaProveedor>();
        return new OrdenPago();
    }

    //Especificos
    public FacturaProveedor[] getFacturaProveedorSeleccionadas() {
        return facturaProveedorSeleccionadas;
    }

    public void setFacturaProveedorSeleccionadas(FacturaProveedor[] facturaProveedorSeleccionadas) {
        this.facturaProveedorSeleccionadas = facturaProveedorSeleccionadas;

    }

    public List<FacturaProveedor> getFacturaProveedorSmall() {
        return this.facturaProveedorSmall;

    }

    public boolean isHabilitaCotizacion() {
        getMoneda();
        return getActual() != null && getActual().getMoneda() != null ? getActual().getMoneda().isMonedaLocal() : false;
    }
    private SelectItem[] itemsCotizacion;

    public Moneda getMoneda() {
        return getActual() != null && getActual().getMoneda() != null ? getActual().getMoneda() : null;
    }

    public SelectItem[] getCotizaciones() {
        cotizaciones = cotizacionBean.getItemsAvailableSelectOnePorMoneda(getMoneda());
        return cotizaciones;
    }

    public void setCotizaciones(SelectItem[] cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public void siCambiaProveedor(SelectEvent event) {
        Proveedor p = (Proveedor) event.getObject();
        getActual().setProveedor(p);

    }

    public Proveedor getProveedor() {
        return getActual() != null && getActual().getProveedor() != null ? getActual().getProveedor() : null;
    }
    //Filtros
    private FiltroFechaDocOP filtroFecha;
    private FiltroProveedorOP filtroProveedor;

    @Override
    public List<Filtro> getFiltros() {
        List<Filtro> R = new ArrayList<Filtro>();
        R.add(getFiltroFecha());
        R.add(getFiltroProveedor());
        return R;
    }

    public FiltroFechaDocOP getFiltroFecha() {
        if (filtroFecha == null) {
            filtroFecha = new FiltroFechaDocOP("Fecha");
        }

        return filtroFecha;
    }

    public FiltroProveedorOP getFiltroProveedor() {
        if (filtroProveedor == null) {
            filtroProveedor = new FiltroProveedorOP("Proveedor", "proveedorBean");
        }
        return filtroProveedor;
    }

    public void setFiltroFecha(FiltroFechaDocOP fechaDocOP) {
        this.filtroFecha = fechaDocOP;
    }

    public void setFiltroProveedor(FiltroProveedorOP filtroProveedor) {
        this.filtroProveedor = filtroProveedor;
    }

    public EstadoOrdenPago findEstado(Object id) {
        return ejb.findEstado(id);
    }

    public List<EstadoOrdenPago> findAllEstados() {
        return ejb.findAllEstados();
    }

    public List<FacturaProveedor> findAllFacturasPendientesOrdenPago() {
        return facturaProveedorDAO.findAllEstadoProveedor(EstadoFacturaProveedor.CONFORMADA, getActual().getProveedor());
    }

    public SelectItem[] getEstadoItems() {
        return JsfUtil.getSelectItems(findAllEstados(), false);
    }
    private DetOrdenPago detalleElegido;

    public DetOrdenPago getDetalleElegido() {
        if (detalleElegido == null) {
            detalleElegido = new DetOrdenPago();
        }
        return detalleElegido;
    }

    public void setDetalleElegido(DetOrdenPago detalleElegido) {
        this.detalleElegido = detalleElegido;
    }

    public void agregaDetalle() {
        if (getActual().getDetalles() == null) {
            getActual().setDetalles(new ArrayList<DetOrdenPago>());
        }
        getActual().getDetalles().add(detalleElegido);
        detalleElegido = null;
    }

    public void agregaSeleccion() {
        for (int i = 0; i < facturaProveedorSeleccionadas.length; i++) {
            System.out.println(facturaProveedorSeleccionadas[i]);
            FacturaProveedor fp = facturaProveedorSeleccionadas[i];

            DetOrdenPago dop = new DetOrdenPago();
            dop.setIndice(i);
            dop.setDescripcion("Factura N° " + fp.getNro());
            dop.setFacturaProveedor(fp);
            dop.setOrdenPago(getActual());
            dop.setMonto(fp.getTotal());
            if (getActual().getDetalles() == null) {
                getActual().setDetalles(new ArrayList<DetOrdenPago>());
            }
            getActual().getDetalles().add(dop);
        }

    }

    public BigDecimal obtenerTotalOrdenPago() {

        BigDecimal total= new BigDecimal(0.0);
        
        if (getActual().getDetalles() == null) {
            return total;
        }
        
        for(DetOrdenPago d: getActual().getDetalles()){
            total = total.add(d.getMonto());
        }
        return total;
    }
}
