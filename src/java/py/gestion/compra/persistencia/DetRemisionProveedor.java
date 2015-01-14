/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.utils.persistencia.DetColumna;

/**
 *
 * @author cromero
 */
@Entity
public class DetRemisionProveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int indice;
    @ManyToOne
    private RemisionProveedor remisionProveedor;
    @ManyToOne
    private Producto producto;
    @DetColumna(selectOne = false, cabecera = "Descripcion", propiedad = "descripcion", fecha = false, visible = true, bean = "")
    private String descripcion;
    @DetColumna(selectOne = false, cabecera = "Cant.", propiedad = "cantidadRecibida", fecha = false, visible = true, bean = "")
    private double cantidadRecibida;
    @DetColumna(selectOne = true, cabecera = "U.M.", propiedad = "unidadMedida", fecha = false, visible = true, bean = "unidadMedidaBean")
    @ManyToOne
    private UnidadMedida unidadMedida;
    @ManyToOne
    private DetOrdenCompra detOC;

    public DetRemisionProveedor() {
    }

    public DetRemisionProveedor(DetOrdenCompra doc, RemisionProveedor r, int indice) {
        this.remisionProveedor = r;
        this.indice = indice;
        this.producto = doc.getProducto();
        this.unidadMedida = doc.getUnidadMedida();
        this.descripcion = doc.getDescripcion();
        this.cantidadRecibida = doc.getCantidadSolicitada();
    }

    public DetOrdenCompra getDetOC() {
        return detOC;
    }

    public void setDetOC(DetOrdenCompra detOC) {
        this.detOC = detOC;
        if (!detOC.getDetallesRemitidos().contains(this)) {
            detOC.getDetallesRemitidos().add(this);
        }
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RemisionProveedor getRemisionProveedor() {
        return remisionProveedor;
    }

    public void setRemisionProveedor(RemisionProveedor remisionProveedor) {
        this.remisionProveedor = remisionProveedor;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetRemisionProveedor other = (DetRemisionProveedor) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
