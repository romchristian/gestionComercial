/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.eventos.factura;

import javax.persistence.Entity;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author cromero
 */
@Entity
public class EventoFPConformacion extends EventoFP{

    private EstadoFacturaProveedor estadoAnterior;
    
    public EventoFPConformacion(FacturaProveedor facturaProveedor, Usuario usuario) {
        super(facturaProveedor, usuario, "CONFORMACIÓN");
    }

    public EventoFPConformacion(FacturaProveedor facturaProveedor, EstadoFacturaProveedor estadoAnterior) {
        super(facturaProveedor);
        this.estadoAnterior = estadoAnterior;
    }
    
    

    public EventoFPConformacion() {
    }

    public EstadoFacturaProveedor getEstadoAnterior() {
        return estadoAnterior;
    }

    public void setEstadoAnterior(EstadoFacturaProveedor estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }
    
    
    
}
