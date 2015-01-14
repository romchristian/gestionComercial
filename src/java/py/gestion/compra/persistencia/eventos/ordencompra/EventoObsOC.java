/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.eventos.ordencompra;

import py.gestion.compra.persistencia.eventos.ordencompra.EventoOC;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({@NamedQuery(name=EventoObsOC.TODOS,query="select e from EventoObsOC e where e.ordenCompra = :ordenCompra order by e.fecha desc")})
public class EventoObsOC extends EventoOC {
    public static final String TODOS = "py.gestionpymes.jpa.compra.EventoObsOC.TODOS";

    @Column(name="obs",columnDefinition="text")
    private String obs;

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
