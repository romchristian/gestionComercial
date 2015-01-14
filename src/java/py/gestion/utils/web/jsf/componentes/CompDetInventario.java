/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.stock.persistencia.Producto;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDetInventario implements Serializable{
    
    @EJB
    private ProductoDAO ejbProducto;
    
    private Producto producto;
    private double cantidad;
    private boolean verificarTodo;
    private List<DetInventario> detalles;
    
    
    @PostConstruct
    private void init(){
        producto = new Producto();
        detalles = new ArrayList<DetInventario>();
        cantidad = 1d;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public List<DetInventario> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetInventario> detalles) {
        this.detalles = detalles;
    }

    public boolean isVerificarTodo() {
        return verificarTodo;
    }

    public void setVerificarTodo(boolean verificarTodo) {
        this.verificarTodo = verificarTodo;
    }
    
    
    
    
    public void busca(){
        producto = ejbProducto.findPorCodigoEstricto(producto.getCodigo());
        System.out.println(producto);
    }
    
    public void agrega(){
        detalles.add(new DetInventario(producto,cantidad,verificarTodo));
        producto = new Producto();
        cantidad = 1d;
    }
    
    public void remover(DetInventario d){
        detalles.remove(d);
        
    }
    
    public void verificaTodos(){
        for(DetInventario d : detalles){

            d.setVerificado(verificarTodo);
        }
    }
    
}
