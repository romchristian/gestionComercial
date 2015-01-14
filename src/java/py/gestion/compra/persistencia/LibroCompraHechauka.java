/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Entity;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.compra.persistencia.enums.Exportador;

/**
 *
 * @author cromero
 */
@Entity
public class LibroCompraHechauka extends FormularioHechauka {
    
    public LibroCompraHechauka() {
        
    }
    
    public LibroCompraHechauka(Periodo periodo, Empresa empresa, Integer cantReg, BigDecimal montoTotal, Exportador exportador) {
        super(1, periodo, 1, 911, 211, empresa, cantReg, montoTotal, exportador);
    }
    
    public LibroCompraHechauka(Periodo periodo, Empresa empresa, Integer cantReg, BigDecimal montoTotal) {
        this(periodo, empresa, cantReg, montoTotal, Exportador.NO);
    }
    
    public LibroCompraHechauka(Periodo periodo, Empresa empresa) {
        this(periodo, empresa, 0, new BigDecimal(0d).setScale(0, RoundingMode.HALF_EVEN));
    }
}
