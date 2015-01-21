/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import py.gestion.adm.persistencia.ImpuestoIVA;


/**
 *
 * @author emelgarejo
 */
@Entity
public class TicketImpuesto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ImpuestoIVA impuestoIVA;
    @ManyToOne
    private Ticket ticket;
    private BigDecimal monto;

    public TicketImpuesto() {
    }

    
    
    public TicketImpuesto(ImpuestoIVA impuestoIVA, Ticket ticket) {
        this.impuestoIVA = impuestoIVA;
        this.ticket = ticket;
        monto = new BigDecimal(0);
        System.out.println("Contructor Impueso 1");
        if(ticket.getTicketDetalles() != null){
            System.out.println("Contructor Impueso 2");
            for(TicketDetalle d: ticket.getTicketDetalles()){
                System.out.println("Contructor Impueso 3");
                if(d.getImpuestoIVALinea().equals(impuestoIVA)){
                    System.out.println("Contructor Impueso 4: " + d.getTotalIVA());
                  monto = monto.add(d.getTotalIVA());
                }
            }
        }
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImpuestoIVA getImpuestoIVA() {
        return impuestoIVA;
    }

    public void setImpuestoIVA(ImpuestoIVA impuestoIVA) {
        this.impuestoIVA = impuestoIVA;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TicketImpuesto)) {
            return false;
        }
        TicketImpuesto other = (TicketImpuesto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.adm.persistencia.validadores.TicketImpuesto[ id=" + id + " ]";
    }
    
}
