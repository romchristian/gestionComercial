/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.eventos.ordencompra;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import py.gestion.adm.persistencia.Evento;
import py.gestion.compra.persistencia.OrdenCompra;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = EventoOC.TODOS, query = "select e from EventoOC e where e.ordenCompra = :ordenCompra order by e.fecha desc")})
public abstract class EventoOC extends Evento {

    public static final String TODOS = "py.gestionpymes.jpa.compra.eventos.ordencompra.EventoOC.TODOS";
    @ManyToOne
    private OrdenCompra ordenCompra;

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }
}
