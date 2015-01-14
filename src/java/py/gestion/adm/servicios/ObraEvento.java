/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.servicios;

import py.gestion.adm.persistencia.Obra;

/**
 *
 * @author cromero
 */
public class ObraEvento {

    private String mensaje;
    private Obra obra;

    public ObraEvento(String mensaje, Obra obra) {
        this.mensaje = mensaje;
        this.obra = obra;
    }
    
    

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    
}
