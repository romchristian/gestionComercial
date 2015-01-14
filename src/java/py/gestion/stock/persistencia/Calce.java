/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.persistencia;

import javax.persistence.Entity;

/**
 *
 * @author cromero
 */
@Entity
public class Calce extends Atributo {

    public Calce() {
    }

    
    public Calce(String nombre) {
        super(nombre, TipoAtributoProducto.CALCE);
    }

    
    @Override
    public String toString() {
        return getNombre();
    }
}
