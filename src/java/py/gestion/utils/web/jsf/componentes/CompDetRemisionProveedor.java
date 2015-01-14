/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.util.Iterator;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.compra.persistencia.DetRemisionProveedor;
import py.gestion.compra.persistencia.RemisionProveedor;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDetRemisionProveedor extends CompDetalle<DetRemisionProveedor> {

    private String descripcion;
    private double cantidadRecibida;
    private UnidadMedida unidadMedida;
    private RemisionProveedor remisionProveedor;

    public RemisionProveedor getRemisionProveedor() {
        return remisionProveedor;
    }

    public void setRemisionProveedor(RemisionProveedor remisionProveedor) {
        this.remisionProveedor = remisionProveedor;
    }
    
    
    
    @Override
    public DetRemisionProveedor getInstancia() {
        DetRemisionProveedor R = new DetRemisionProveedor();
        return R;
    }

    @Override
    public DetRemisionProveedor getItemAAgregar() {
        DetRemisionProveedor R = new DetRemisionProveedor(); 
        R.setIndice(getDetalles().size());
        R.setCantidadRecibida(cantidadRecibida);
        R.setDescripcion(descripcion);
        R.setUnidadMedida(unidadMedida);
        R.setRemisionProveedor(remisionProveedor);
        R.setProducto(getProducto());
        return R;
    }

    @Override
    public void busca() {
        super.busca();
        cantidadRecibida = 1;
        descripcion = getProducto().getNombre();
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

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    @Override
    public void quitaDetalle() {
        int indice = getItemElegido().getIndice();
        for(int i = 0; i < getDetalles().size();i++){
            DetRemisionProveedor d = getDetalles().get(i);
            if(d.getIndice() == indice){
                getDetalles().remove(i);
                break;
            }
        }
    }
       
}
