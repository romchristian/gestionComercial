/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.fitlros.ordencompra;

import py.gestion.utils.persistencia.Filtro;
import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */

public  class FiltroOC extends Filtro{

    private String filtro;
    
    public FiltroOC() {
    }

    public FiltroOC(String nombre) {
        super(nombre);
    }


    @Override
    public String getFiltro(StringBuilder sb, QueryParameter queryParameter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Filtro getNuevo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
