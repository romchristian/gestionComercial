/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.OrdenCompra;

/**
 *
 * @author Elias
 */
@Named
@SessionScoped
public class PickListOCRPBean extends PickListOCBean{
    
    @PostConstruct
    public void init(){
        resfresca();
    }

    @Override
    public void remueveOCSNoPedientes() {
        Iterator<OrdenCompra> it = getOrdenesCompra().iterator();
        while (it.hasNext()) {

            boolean tieneDetalles = false;
            OrdenCompra oc = it.next();
            for (DetOrdenCompra doc : oc.getDetalles()) {
                if (refresh(doc).getCantidadARecibir() > 0) {
                    tieneDetalles = true;
                }
            }
            
            if (!tieneDetalles) {
                it.remove();
                getOrdenesCompra().remove(oc);
            }
        }
    }
    
}
