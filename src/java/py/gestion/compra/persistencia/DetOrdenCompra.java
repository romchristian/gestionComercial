/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DetOrdenCompra.TODOS, query = "select doc from DetOrdenCompra doc")
})
public class DetOrdenCompra implements Serializable {

    public static final String TODOS = "py.syscvsa.compra.persistencia.DetOrdenCompra.TODOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private OrdenCompra ordenCompra;
    @ManyToOne
    private Producto producto;
    @ManyToOne
    private UnidadMedida unidadMedida;
    private String descripcion;
    private double cantidadSolicitada;
    private double cantidadRecibida;
    @Transient
    private double cantidadARecibir;
    @Transient
    private double cantidadAFacturar;
    private double precioPactado;
    private double precioPromedio;
    private boolean asociadoConFP;
    @OneToMany(mappedBy = "detOC", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetFacturaProveedor> detallesFacturados;
    @OneToMany(mappedBy = "detOC", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetRemisionProveedor> detallesRemitidos;

    public DetOrdenCompra() {
    }

    public DetOrdenCompra(OrdenCompra ordenCompra, Producto producto, String descripcion, UnidadMedida unidadMedida, double cantidad, double costo) {
        this.ordenCompra = ordenCompra;
        this.producto = producto;
        this.descripcion = descripcion;
        this.unidadMedida = unidadMedida;
        this.cantidadSolicitada = cantidad;
        this.precioPactado = costo;
    }

    public List<DetRemisionProveedor> getDetallesRemitidos() {
        return detallesRemitidos;
    }

    public void setDetallesRemitidos(List<DetRemisionProveedor> detallesRemitidos) {
        this.detallesRemitidos = detallesRemitidos;
    }

    
    
    public double getCantidadARecibir() {
        if (detallesRemitidos != null) {
            cantidadARecibir = 0;
            for (DetRemisionProveedor d : detallesRemitidos) {
                cantidadARecibir += d.getCantidadRecibida();
            }
        }

        cantidadARecibir = cantidadSolicitada - cantidadARecibir;
        return cantidadARecibir;
    }

    
    public double getCantidadAFacturar() {
        if (detallesFacturados != null) {
            cantidadAFacturar = 0;
            for (DetFacturaProveedor d : detallesFacturados) {
                cantidadAFacturar += d.getCantidad().doubleValue();
            }
        }

        cantidadAFacturar = cantidadSolicitada - cantidadAFacturar;
        return cantidadAFacturar;
    }
    
    
    public void setCantidadARecibir(double saldoCantidadRecibida) {
        this.cantidadARecibir = saldoCantidadRecibida;
    }

    public void asociaDetFactura(DetFacturaProveedor dfp) {
        this.detallesFacturados.add(dfp);
        if (dfp.getDetOC() != this) {
            dfp.setDetOC(this);
        }
    }

    public void asociaDetRemision(DetRemisionProveedor dfp) {
        this.detallesRemitidos.add(dfp);
        if (dfp.getDetOC() != this) {
            dfp.setDetOC(this);
        }
    }

    

    public void setCantidadAFacturar(double saldoCantidadFacturada) {
        this.cantidadAFacturar = saldoCantidadFacturada;
    }

    public List<DetFacturaProveedor> getDetallesFacturados() {
        return detallesFacturados;
    }

    public void setDetallesFacturados(List<DetFacturaProveedor> detallesFacturados) {
        this.detallesFacturados = detallesFacturados;
    }

    public boolean isAsociadoConFP() {
        return asociadoConFP;
    }

    public void setAsociadoConFP(boolean asociadoConFP) {
        this.asociadoConFP = asociadoConFP;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(double cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public double getPrecioPactado() {
        return precioPactado;
    }

    public void setPrecioPactado(double precioPactado) {
        this.precioPactado = precioPactado;
    }

    public double getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(double precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetOrdenCompra)) {
            return false;
        }
        DetOrdenCompra other = (DetOrdenCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.compra.DetOrdenCompra[ id=" + id + " ]";
    }
}
