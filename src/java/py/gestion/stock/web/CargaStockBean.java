/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import py.gestion.utils.web.JsfUtil;
import py.gestion.stock.persistencia.Producto;

/**
 *
 * @author christian
 */
@Named
@ConversationScoped
public class CargaStockBean implements Serializable {

    @Inject
    private Conversation conversation;
    private boolean skip;
    private List<Producto> productos;
    private Producto producto;
    

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {
        System.out.println("Current wizard step:" + event.getOldStep());
        System.out.println("Next step:" + event.getNewStep());
        if (event.getOldStep().compareTo("productos") == 0) {
            if (conversation.isTransient()) {
                conversation.begin();
            }
        }

        if (skip) {
            skip = false;   //reset in case user goes back  
            return "confirmacion";
        } else {
            return event.getNewStep();
        }
    }

    public void guardar(ActionEvent actionEvent) {
        JsfUtil.addSuccessMessage("Termino");

        if (!conversation.isTransient()) {
           conversation.end();
        }
    }

    public String agregar(){
        
        for(int i = 35; i < 40; i++){
            productos.add(new Producto(Long.valueOf(i+""), producto.getCodigo()+""+i, producto.getNombre()));
        }
        
        producto = new Producto();
        return null;
    }
    
    public void remover(Producto p){
        productos.remove(p);
        
        
    }

    public Producto getProducto() {
        if(producto == null){
            producto = new Producto();
        }
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public List<Producto> getProductos() {
        if(productos == null){
            productos = new ArrayList<Producto>();
                    
        }
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
