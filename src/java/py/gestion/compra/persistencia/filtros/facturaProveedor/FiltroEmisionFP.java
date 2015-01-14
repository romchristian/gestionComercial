/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.filtros.facturaProveedor;

import javax.persistence.Entity;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroRangoFecha;

/**
 *
 * @author cromero
 */
@Entity
public class FiltroEmisionFP  extends FiltroRangoFecha{

    public FiltroEmisionFP() {
    }
    
    public FiltroEmisionFP(String nombre) {
        super(nombre);
    }

    @Override
    public String getCampo() {
        return "emision";
    }

    @Override
    public Filtro getNuevo() {
        FiltroEmisionFP R = new FiltroEmisionFP();
        R.setNombre(this.getNombre());
        R.setInicio(this.getInicio());
        R.setFin(this.getFin());
        return R;
    }
}
