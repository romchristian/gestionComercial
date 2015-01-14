/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.filtros.RemisionProveedor;

import javax.persistence.Entity;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroRangoFecha;

/**
 *
 * @author cromero
 */
@Entity
public class FiltroRecepcionRP  extends FiltroRangoFecha{

    public FiltroRecepcionRP() {
    }
    
    public FiltroRecepcionRP(String nombre) {
        super(nombre);
    }

    @Override
    public String getCampo() {
        return "fechaRecepcion";
    }

    @Override
    public Filtro getNuevo() {
        FiltroRecepcionRP R = new FiltroRecepcionRP();
        R.setNombre(this.getNombre());
        R.setInicio(this.getInicio());
        R.setFin(this.getFin());
        return R;
    }
}
