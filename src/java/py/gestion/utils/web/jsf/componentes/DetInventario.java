/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import py.gestion.stock.persistencia.Producto;

/**
 *
 * @author christian
 */
public class DetInventario implements Serializable {

    private String cod;
    private String descripcion;
    private double cantidad;
    private double cantidadSistema;
    private boolean verificado;

    public DetInventario() {
    }

    public DetInventario(String cod, String descripcion, double cantidad) {
        this.cod = cod;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    public DetInventario(Producto producto, double cantidad) {
        this(producto.getCodigo(), producto.getNombre(), cantidad);
    }

    public DetInventario(Producto producto, double cantidad, boolean verificado) {
        this(producto, cantidad);
        this.verificado = verificado;
    }
    
    

    public double getCantidadSistema() {
        return cantidadSistema;
    }

    public void setCantidadSistema(double cantidadSistema) {
        this.cantidadSistema = cantidadSistema;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    
    
    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
