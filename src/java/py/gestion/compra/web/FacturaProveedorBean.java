/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.web.CotizacionBean;
import py.gestion.compra.persistencia.DetFacturaProveedor;
import py.gestion.compra.persistencia.DetFacturaProveedorProducto;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.compra.persistencia.filtros.facturaProveedor.FiltroEmisionFP;
import py.gestion.compra.persistencia.filtros.facturaProveedor.FiltroNroFP;
import py.gestion.compra.persistencia.filtros.facturaProveedor.FiltroProveedorFP;
import py.gestion.compra.servicios.FacturaProveedorDAO;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;
import py.gestion.proveedores.servicios.ProveedorTimbradoDAO;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;
import py.gestion.utils.web.jsf.componentes.CompDetFacturaProveedor;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class FacturaProveedorBean extends BeanGenerico<FacturaProveedor> {

    @EJB
    private FacturaProveedorDAO ejb;
    @EJB
    private ProveedorTimbradoDAO proveedorTimbradoDAO;
    @Inject
    private CompDetFacturaProveedor compDetFacturaProveedor;
    private String nuevaObservacion;
    private boolean habilitaCotizacion;
    private Cotizacion cotizacion;
    @Inject
    private CotizacionBean cotizacionBean;
    @Inject
    private PickListOCFPBean pickListBean;
    private SelectItem[] cotizaciones;
    private ProveedorTimbrado proveedorTimbrado;

    private void iniciaConversacion() {


        if (getActual().getDetalles() == null) {
            getActual().setDetalles(new ArrayList<DetFacturaProveedor>());
        }

        compDetFacturaProveedor.iniciaConversacion();
        compDetFacturaProveedor.setDetalles(getActual().getDetalles());
        compDetFacturaProveedor.setFacturaProveedor(getActual());
    }

    private void terminaConversacion() {
        compDetFacturaProveedor.terminaConversacion();
        pickListBean.resfresca();
    }

    @Override
    public AbstractDAO<FacturaProveedor> getEjb() {
        return ejb;
    }

    @Override
    public FacturaProveedor getNuevo() {
        return new FacturaProveedor();
    }

    @Override
    public String nuevo() {
        setActual(null);
        iniciaConversacion();
        return "nuevo.xhtml";
    }

    @Override
    public String preparaEdicion(FacturaProveedor obj) {
        String R = super.preparaEdicion(obj);
        iniciaConversacion();
        return R;
    }

    @Override
    public String create() {

        getActual().setTimbrado(proveedorTimbrado.getTimbrado() + "");
        getActual().setCodigoEstablecimiento(proveedorTimbrado.getCodEst());
        getActual().setCodigoSucursal(proveedorTimbrado.getCodSuc());

        String R = super.create();
        terminaConversacion();
        return R;
    }

    @Override
    public String edit() {
        getActual().setCotizacion(cotizacion);
        String R = super.edit();
        terminaConversacion();
        return R;
    }

    public String getNuevaObservacion() {
        return nuevaObservacion;
    }

    public void setNuevaObservacion(String nuevaObservacion) {
        this.nuevaObservacion = nuevaObservacion;
    }

    public void agregaObersvacion() {
        //ejb.agregaObservacion(getActual(), nuevaObservacion);
        nuevaObservacion = null;
    }

    public String atras() {
        terminaConversacion();
        return "listado.xhtml";
    }

    public void limpiaObservacion() {
        nuevaObservacion = null;
    }

    public String conforma() {
        ejb.conforma(getActual());
        return null;
    }

    public Long countCreadas() {
        return ejb.countPorEstado(EstadoFacturaProveedor.CREADA);
    }

    public Long countConformada() {
        return ejb.countPorEstado(EstadoFacturaProveedor.CONFORMADA);
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
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
        pickListBean.setProveedor(p);
        pickListBean.resfrescaConsulta();
    }

    public Proveedor getProveedor() {
        return getActual() != null && getActual().getProveedor() != null ? getActual().getProveedor() : null;
    }

    public SelectItem[] getTimbrados() {
        return JsfUtil.getSelectItems(proveedorTimbradoDAO.findAll(getProveedor()), false);
    }

    public ProveedorTimbrado getProveedorTimbrado() {
        return proveedorTimbrado;
    }

    public void setProveedorTimbrado(ProveedorTimbrado proveedorTimbrado) {
        this.proveedorTimbrado = proveedorTimbrado;
    }

    public void validateNroFactura(ComponentSystemEvent event) {
        UIComponent components = event.getComponent();
        UIInput uiInputTimbrado = (UIInput) components.findComponent("timbrado");
        UIInput uiInputNumero = (UIInput) components.findComponent("numero");


        ProveedorTimbrado oTimbrado = (ProveedorTimbrado) (uiInputTimbrado.getLocalValue() == null ? ""
                : uiInputTimbrado.getLocalValue());


        String nro = uiInputNumero.getLocalValue() == null ? ""
                : uiInputNumero.getLocalValue().toString();

        if (ejb.hayDocumento(oTimbrado, nro)) {
            uiInputNumero.setValid(false);
            JsfUtil.addErrorMessage("La factura  " + oTimbrado + "-" + nro + " ya existe!");
        }
    }

    public void agregaDetallesDesdeOCs() {
        //Recupero la lista de detalles de OC selecionados
        List<DetOrdenCompra> detocs = pickListBean.getTarget();

        int indice = 1;
        //Limpio detalles de facturas
        getActual().getDetalles().clear();

        for (DetOrdenCompra doc : detocs) {
            //Creo un detalle de factura a partir de un detalle de OC
            DetFacturaProveedor dfp = new DetFacturaProveedorProducto(doc, getActual(), indice);
            //Agrego el detalle nuevo a la factura
            doc.asociaDetFactura(dfp);
            getActual().getDetalles().add(dfp);
            //asociaADetalleOC(doc,dfp);
            System.out.println("DESDE FACTURA BEAN: ");
            System.out.println("SALDO CANTIDAD FACTURADA: " + doc.getCantidadAFacturar());

            indice++;
        }
    }
    //Filtros
    private FiltroEmisionFP filtroEmision;
    private FiltroNroFP filtroNro;
    private FiltroProveedorFP filtroProveedor;
    
    @Override
    public List<Filtro> getFiltros() {
        List<Filtro> R = new ArrayList<Filtro>();
        R.add(getFiltroEmision());
        R.add(getFiltroNro());
        R.add(getFiltroProveedor());
        return R;
    }
    
     public FiltroEmisionFP getFiltroEmision() {
        if (filtroEmision == null) {
            filtroEmision = new FiltroEmisionFP("Emision");
        }
        return filtroEmision;
    }

    public FiltroNroFP getFiltroNro() {
        if (filtroNro == null) {
            filtroNro = new FiltroNroFP("Nro");
        }
        return filtroNro;
    }

    
    public FiltroProveedorFP getFiltroProveedor() {
        if (filtroProveedor == null) {
            filtroProveedor = new FiltroProveedorFP("Proveedor", "proveedorBean");
        }
        return filtroProveedor;
    }

    public void setHabilitaCotizacion(boolean habilitaCotizacion) {
        this.habilitaCotizacion = habilitaCotizacion;
    }

    public void setFiltroEmision(FiltroEmisionFP filtroEmision) {
        this.filtroEmision = filtroEmision;
    }

    public void setFiltroNro(FiltroNroFP filtroNro) {
        this.filtroNro = filtroNro;
    }

    public void setFiltroProveedor(FiltroProveedorFP filtroProveedor) {
        this.filtroProveedor = filtroProveedor;
    }
    
    
}
