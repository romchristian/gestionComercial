/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author christian
 */
@Entity
public abstract class DetFacturaProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private FacturaProveedor facturaProveedor;
    private int indice;
    private BigDecimal cantidad;
    @ManyToOne
    private UnidadMedida unidadMedida;
    private String descripcion;
    @Column(precision = 38, scale = 4)
    private BigDecimal iva;
    @Column(precision = 38, scale = 4)
    private BigDecimal monto;
    @Column(precision = 38, scale = 4)
    private BigDecimal exenta;
    @Column(precision = 38, scale = 4)
    private BigDecimal gravada05;
    @Column(precision = 38, scale = 4)
    private BigDecimal gravada10;
    @Column(precision = 38, scale = 4)
    private BigDecimal subtotal;
    @ManyToOne
    private DetOrdenCompra detOC;

    public DetFacturaProveedor() {
    }

    public DetFacturaProveedor(FacturaProveedor facturaProveedor, int indice, double cantidad, UnidadMedida unidadMedida, String descripcion, double iva, double monto, double exenta, double gravada05, double gravada10) {
        this.facturaProveedor = facturaProveedor;
        this.indice = indice;
        this.cantidad = new BigDecimal(cantidad).setScale(2);
        this.unidadMedida = unidadMedida;
        this.descripcion = descripcion;
        this.iva = new BigDecimal(iva).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.monto = new BigDecimal(monto).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.exenta = new BigDecimal(exenta).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.gravada05 = new BigDecimal(gravada05).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.gravada10 = new BigDecimal(gravada10).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.subtotal = this.exenta.add(this.gravada05).add(this.gravada10).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
    }

    public DetFacturaProveedor(DetOrdenCompra doc, FacturaProveedor f, int indice) {
        this.facturaProveedor = f;
        this.indice = indice;
        this.cantidad = new BigDecimal(doc.getCantidadAFacturar()).setScale(2);
        this.unidadMedida = doc.getUnidadMedida();
        this.descripcion = doc.getDescripcion();
        this.iva = new BigDecimal(10).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.monto = new BigDecimal(doc.getPrecioPactado()).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.exenta = new BigDecimal(0).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.gravada05 = new BigDecimal(0).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.gravada10 = cantidad.multiply(monto).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        this.subtotal = this.exenta.add(this.gravada05).add(this.gravada10).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);

    }

    public DetOrdenCompra getDetOC() {
        return detOC;
    }

    public void setDetOC(DetOrdenCompra detOC) {
        this.detOC = detOC;
        if (!detOC.getDetallesFacturados().contains(this)) {
            detOC.getDetallesFacturados().add(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getExenta() {
        if(iva.compareTo(new BigDecimal(0)) ==0){
            exenta = cantidad.multiply(monto).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        }
        return exenta;
    }

    public void setExenta(BigDecimal exenta) {
        this.exenta = exenta;
    }

    public BigDecimal getGravada05() {
        if(iva.compareTo(new BigDecimal(5)) ==0){
            gravada05 = cantidad.multiply(monto).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        }
        return gravada05;
    }

    public void setGravada05(BigDecimal gravada05) {
        this.gravada05 = gravada05;
    }

    public BigDecimal getGravada10() {
        if(iva.compareTo(new BigDecimal(10)) ==0){
            gravada10 = cantidad.multiply(monto).setScale(facturaProveedor.getMoneda().getDecimales(), RoundingMode.HALF_EVEN);
        }
        return gravada10;
    }

    public void setGravada10(BigDecimal gravada10) {
        this.gravada10 = gravada10;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
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
        if (!(object instanceof DetFacturaProveedor)) {
            return false;
        }
        DetFacturaProveedor other = (DetFacturaProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.DetFacturaProveedor[id=" + id + "]";
    }
}
