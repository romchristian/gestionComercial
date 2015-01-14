/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia.filtros.ordenPago;


import javax.persistence.Entity;
import javax.persistence.Transient;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroAutoComplete;

/**
 *
 * @author cromero
 */
@Entity
public class FiltroProveedorOP  extends FiltroAutoComplete{
    @Transient
    private Proveedor elegido;

    public FiltroProveedorOP() {
    }
    
    public FiltroProveedorOP(String nombre, String bean) {
        super(nombre, bean);
    }

    @Override
    public String getCampo() {
        return "proveedor";
    }

    @Override
    public Filtro getNuevo() {
        FiltroProveedorOP R = new FiltroProveedorOP();
        R.setNombre(this.getNombre());
        R.setBean("proveedorBean");
        return R;
    }

    public Proveedor getElegido() {
        return elegido;
    }

    public void setElegido(Proveedor elegido) {
        setEntidadId(elegido.getId());
        this.elegido = elegido;
    }
}
