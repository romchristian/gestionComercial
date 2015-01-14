/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.eventos.factura;

import javax.persistence.Entity;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author cromero
 */
@Entity
public class EventoFPCreacion extends EventoFP{

    public EventoFPCreacion(FacturaProveedor facturaProveedor, Usuario usuario) {
        super(facturaProveedor, usuario, "CREACIÓN");
    }

    public EventoFPCreacion(FacturaProveedor facturaProveedor) {
        super(facturaProveedor);
    }
    
    

    public EventoFPCreacion() {
    }
    
}
