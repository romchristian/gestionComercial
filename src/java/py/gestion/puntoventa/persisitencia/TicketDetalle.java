/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.stock.persistencia.Producto;

/**
 *
 * @author emelgarejo
 */
@Entity
public class TicketDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Ticket ticket;
    private Integer linea;
    @ManyToOne
    private Moneda moneda;
    private String descripcion;
    @ManyToOne
    private Producto producto;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    @ManyToOne
    private ImpuestoIVA impuestoIVALinea;
    private BigDecimal totalIVA;
    private BigDecimal total;

    public TicketDetalle() {
    }

    public TicketDetalle(Ticket ticket, Integer linea, Producto producto, BigDecimal cantidad, BigDecimal precioUnitario, ImpuestoIVA impuestoIVALinea, Moneda moneda) {
        this.ticket = ticket;
        this.descripcion = producto.getNombre();
        this.linea = linea;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.impuestoIVALinea = impuestoIVALinea;
        this.moneda = moneda;
        this.total = cantidad.multiply(precioUnitario);
        double factorIVA = impuestoIVALinea.getValor()+1;
        BigDecimal precioSinIva = total.divide(new BigDecimal(factorIVA),0, RoundingMode.HALF_EVEN);
        this.totalIVA = total.subtract(precioSinIva);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getLinea() {
        return linea;
    }

    public void setLinea(Integer linea) {
        this.linea = linea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public ImpuestoIVA getImpuestoIVALinea() {
        return impuestoIVALinea;
    }

    public void setImpuestoIVALinea(ImpuestoIVA impuestoIVALinea) {
        this.impuestoIVALinea = impuestoIVALinea;
    }

    public BigDecimal getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(BigDecimal totalIVA) {
        this.totalIVA = totalIVA;
    }

    public BigDecimal getTotal() {
        total = cantidad.multiply(precioUnitario);
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
        if (!(object instanceof TicketDetalle)) {
            return false;
        }
        TicketDetalle other = (TicketDetalle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.puntoventa.persisitencia.TicketDetalle[ id=" + id + " ]";
    }
    
}
