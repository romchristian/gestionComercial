/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.filtros.facturaProveedor;

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
public class FiltroProveedorFP  extends FiltroAutoComplete{
    @Transient
    private Proveedor elegido;

    public FiltroProveedorFP() {
    }
    
    public FiltroProveedorFP(String bean, String nombre) {
        super(bean, nombre);
    }

    @Override
    public String getCampo() {
        return "proveedor";
    }

    @Override
    public Filtro getNuevo() {
        FiltroProveedorFP R = new FiltroProveedorFP();
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
