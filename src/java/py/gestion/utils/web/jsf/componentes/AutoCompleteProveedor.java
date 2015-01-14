/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class AutoCompleteProveedor implements Serializable{

    private Proveedor elegido;
    @Inject
    private DiccionarioProveedor dic;

    public Proveedor getElegido() {
        return elegido;
    }

    public void setElegido(Proveedor elegido) {
        this.elegido = elegido;
    }

    public List<Proveedor> completar(String query) {
        List<Proveedor> sugerencias = new ArrayList<Proveedor>();

        for (Proveedor p : dic.getLista()) {
            if (p.getNombre().toUpperCase().startsWith(query.toUpperCase())) {
                sugerencias.add(p);
            }
        }

        return sugerencias;
    }
    
    
}
