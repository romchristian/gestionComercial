/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.filtros.facturaProveedor;

import javax.persistence.Entity;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroCadena;

/**
 *
 * @author cromero
 */
@Entity
public class FiltroNroFP  extends FiltroCadena{

    public FiltroNroFP() {
    }
    
    public FiltroNroFP(String nombre) {
        super(nombre);
    }

    @Override
    public String getCampo() {
        return "numero";
    }

    @Override
    public Filtro getNuevo() {
        FiltroNroFP R = new FiltroNroFP();
        R.setNombre(this.getNombre());
        R.setCadena(this.getCadena());
        return R;
    }
}
