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
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class AutoCompleteCliente implements Serializable{

    private Cliente elegido;
    @Inject
    private DiccionarioCliente dic;

    public Cliente getElegido() {
        return elegido;
    }

    public void setElegido(Cliente elegido) {
        this.elegido = elegido;
    }

    public List<Cliente> completar(String query) {
        List<Cliente> sugerencias = new ArrayList<Cliente>();

        for (Cliente p : dic.getLista()) {
            if (p.getNroDocumento().toUpperCase().startsWith(query.toUpperCase())) {
                sugerencias.add(p);
            }
        }

        return sugerencias;
    }
    
    
}
