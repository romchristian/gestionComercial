/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.persistencia.Moneda;

import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author Elias
 */
@Entity
@NamedQueries({
    @NamedQuery(name = AsociacionOC_FP.TODOS, query = "select a from AsociacionOC_FP a")})
public class AsociacionOC_FP implements Serializable {

    public static final String TODOS = "py.syscvsa.compra.persistencia.AsociacionOC_FP.TODOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OrdenCompra ordenCompra;
    @ManyToOne
    private FacturaProveedor facturaProveedor;
    @ManyToOne
    private DetOrdenCompra detOrdenCompra;
    @ManyToOne
    private DetFacturaProveedor detFacturaProveedor;
    @ManyToOne
    private RemisionProveedor remisionProveedor;
    @ManyToOne
    private DetRemisionProveedor detRemisionProveedor;
    @ManyToOne
    private Proveedor proveedor;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private UnidadMedida unidadMedida;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFactura;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecepcion;
    @ManyToOne
    private Moneda moneda;
    @ManyToOne
    private Cotizacion cotizacion;
    private Double cantidadRecibida;
    private Double precioPagado;

    public AsociacionOC_FP() {
    }

    public AsociacionOC_FP(DetOrdenCompra detOrdenCompra, DetFacturaProveedor detFacturaProveedor) {
        this.detOrdenCompra = detOrdenCompra;
        this.detFacturaProveedor = detFacturaProveedor;
        this.ordenCompra = detOrdenCompra.getOrdenCompra();
        FacturaProveedor fp = detFacturaProveedor.getFacturaProveedor();
        this.facturaProveedor = fp;
        this.proveedor = fp.getProveedor();
        Producto p = detOrdenCompra.getProducto();
        this.producto = p;
        this.unidadMedida = p!= null? p.getUnidadMedida():null;
        this.fechaFactura = fp.getEmision();
        this.moneda = fp.getMoneda();
        this.cotizacion = fp.getCotizacion();
        this.precioPagado = detFacturaProveedor.getSubtotal().doubleValue();
        
    }

    public AsociacionOC_FP(DetOrdenCompra detOrdenCompra, DetRemisionProveedor detRemisionProveedor) {
        this.detOrdenCompra = detOrdenCompra;
        this.detRemisionProveedor = detRemisionProveedor;
    }

    public AsociacionOC_FP(DetFacturaProveedor detFacturaProveedor, DetRemisionProveedor detRemisionProveedor) {
        this.detFacturaProveedor = detFacturaProveedor;
        this.detRemisionProveedor = detRemisionProveedor;
    }

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetRemisionProveedor getDetRemisionProveedor() {
        return detRemisionProveedor;
    }

    public void setDetRemisionProveedor(DetRemisionProveedor detRemisionProveedor) {
        this.detRemisionProveedor = detRemisionProveedor;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public DetOrdenCompra getDetOrdenCompra() {
        return detOrdenCompra;
    }

    public void setDetOrdenCompra(DetOrdenCompra detOrdenCompra) {
        this.detOrdenCompra = detOrdenCompra;
    }

    public DetFacturaProveedor getDetFacturaProveedor() {
        return detFacturaProveedor;
    }

    public void setDetFacturaProveedor(DetFacturaProveedor detFacturaProveedor) {
        this.detFacturaProveedor = detFacturaProveedor;
    }

    public RemisionProveedor getRemisionProveedor() {
        return remisionProveedor;
    }

    public void setRemisionProveedor(RemisionProveedor remisionProveedor) {
        this.remisionProveedor = remisionProveedor;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public Double getPrecioPagado() {
        return precioPagado;
    }

    public void setPrecioPagado(Double precioPagado) {
        this.precioPagado = precioPagado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AsociacionOC_FP)) {
            return false;
        }
        AsociacionOC_FP other = (AsociacionOC_FP) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.compra.persistencia.AsociacionOC_FP[ id=" + id + " ]";
    }
}
