/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author cromero
 */
@Entity
public class DetFacturaProveedorProducto extends DetFacturaProveedor{

    @ManyToOne
    private Producto producto;
    
    public DetFacturaProveedorProducto() {
    }

    public DetFacturaProveedorProducto(Producto producto, FacturaProveedor facturaProveedor, int indice, double cantidad, UnidadMedida unidadMedida, String descripcion, double iva, double monto, double exenta, double gravada05, double gravada10) {
        super(facturaProveedor, indice, cantidad, unidadMedida, descripcion, iva, monto, exenta, gravada05, gravada10);
        this.producto = producto;
    }

    public DetFacturaProveedorProducto(DetOrdenCompra doc, FacturaProveedor f,int indice) {
        super(doc, f,indice);
        this.producto = doc.getProducto();
       
    }

    
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    
    
    
}
