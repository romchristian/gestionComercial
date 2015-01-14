/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.eventos.factura;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import py.gestion.adm.persistencia.Evento;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = EventoFP.TODOS, query = "select e from EventoFP e where e.facturaProveedor = :facturaProveedor order by e.fecha desc")})
public abstract class EventoFP extends Evento {

    public static final String TODOS = "py.gestionpymes.jpa.compra.eventos.factura.EventoFP.TODOS";
    @ManyToOne
    private FacturaProveedor facturaProveedor;

    public EventoFP() {
    }

    
    public EventoFP(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    
    public EventoFP(FacturaProveedor facturaProveedor, Usuario usuario, String nombre) {
        super(usuario, nombre);
        this.facturaProveedor = facturaProveedor;
    }

    
    

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }
    
    
}
