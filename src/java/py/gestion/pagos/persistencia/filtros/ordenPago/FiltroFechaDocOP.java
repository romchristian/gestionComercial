/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia.filtros.ordenPago;

import javax.persistence.Entity;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroRangoFecha;

/**
 *
 * @author cromero
 */
@Entity
public class FiltroFechaDocOP extends FiltroRangoFecha {

    public FiltroFechaDocOP() {
    }

    public FiltroFechaDocOP(String nombre) {
        super(nombre);
    }

    @Override
    public String getCampo() {
        return "creacion";
    }

    @Override
    public Filtro getNuevo() {
        FiltroFechaDocOP R = new FiltroFechaDocOP();
        R.setNombre(this.getNombre());
        R.setInicio(this.getInicio());
        R.setFin(this.getFin());
        return R;
    }
}
