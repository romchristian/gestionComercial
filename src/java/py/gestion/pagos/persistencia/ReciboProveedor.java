/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Moneda;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author christian
 */
@Entity
public class ReciboProveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Proveedor proveedor;
    private String numero;
    private String concepto;
    @ManyToOne
    private Moneda moneda;
    @ManyToOne
    private Cotizacion cotizacion;
    private double monto;
    private double montoLocal;
    @ManyToOne
    private FacturaProveedor facturaProveedor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMontoLocal() {
        return montoLocal;
    }

    public void setMontoLocal(double montoLocal) {
        this.montoLocal = montoLocal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        if (!(object instanceof ReciboProveedor)) {
            return false;
        }
        ReciboProveedor other = (ReciboProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.pagos.ReciboProveedor[ id=" + id + " ]";
    }
    
}
