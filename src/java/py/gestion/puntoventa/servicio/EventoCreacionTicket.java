/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package py.gestion.puntoventa.servicio;

import py.gestion.puntoventa.persisitencia.Ticket;

/**
 *
 * @author emelgarejo
 */
public class EventoCreacionTicket {

    private Ticket ticket;

    public EventoCreacionTicket() {
    }

    public EventoCreacionTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    
    
}
