/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class CompLayout2 implements Serializable{
    private boolean operacionesAbierto = true;
    private boolean filtroAbierto = false;
    

    public boolean isFiltroAbierto() {
        return filtroAbierto;
    }

    public void setFiltroAbierto(boolean filtroAbierto) {
        
        this.filtroAbierto = filtroAbierto;
    }

    public boolean isOperacionesAbierto() {
        return operacionesAbierto;
    }

    public void setOperacionesAbierto(boolean operacionesAbierto) {
        this.operacionesAbierto = operacionesAbierto;
    }

    
    
    public void toggleFiltro(ValueChangeEvent event){
        this.filtroAbierto = filtroAbierto?false:true;
        this.operacionesAbierto = false;
    }
    
    public void toggleOperaciones(ValueChangeEvent event){
        this.operacionesAbierto = operacionesAbierto?false:true;
        this.filtroAbierto = false;
    }
    
    
}
