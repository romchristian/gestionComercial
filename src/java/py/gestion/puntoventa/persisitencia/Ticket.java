/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import py.gestion.adm.persistencia.Moneda;

/**
 *
 * @author emelgarejo
 */
@Entity
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date emision;
    @ManyToOne
    private SesionTPV sesionTPV;
    @ManyToOne
    private SocioComercial socioComercial;
    private BigDecimal total = new BigDecimal(0);
    private BigDecimal totalNeto = new BigDecimal(0);
    private BigDecimal totalIVA = new BigDecimal(0);
    private BigDecimal totalDescuento = new BigDecimal(0);
    private BigDecimal totalExento = new BigDecimal(0);
    @ManyToOne
    private Moneda moneda;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketImpuesto> ticketImpuestos;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketDetalle> ticketDetalles;
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormaPago> ticketFormasPago;

    public Ticket() {
    }

    public Ticket(Long numero, Date emision, SesionTPV sesionTPV, SocioComercial socioComercial) {
        this.numero = numero;
        this.emision = emision;
        this.sesionTPV = sesionTPV;
        this.socioComercial = socioComercial;
    }

    public void addDetalle(TicketDetalle d) {
        if (ticketDetalles == null) {
            ticketDetalles = new ArrayList<TicketDetalle>();
        }

        boolean hayProducto = false;
        for (TicketDetalle dt : ticketDetalles) {
            if (dt.getProducto().equals(d.getProducto())) {
                dt.setCantidad(dt.getCantidad().add(BigDecimal.ONE));
                hayProducto = true;
                break;
            }
        }

        if (!hayProducto) {
            ticketDetalles.add(d);
        }

        total = new BigDecimal(0);
        for (TicketDetalle dt : ticketDetalles) {
            total = total.add(dt.getTotal());
        }

        System.out.println("Total: " + total);
    }

    public TicketDetalle getUltimoDetalle() {
        TicketDetalle ultimo = null;
        Collections.sort(ticketDetalles, new Comparator<TicketDetalle>() {

            @Override
            public int compare(TicketDetalle o1, TicketDetalle o2) {
                return o1.getLinea() > o2.getLinea() ? 1 : -1;
            }
        });

        for (TicketDetalle dt : ticketDetalles) {
            ultimo = dt;
        }
        return ultimo;
    }

    public void remueveDetalle(TicketDetalle d) {
        if (ticketDetalles != null) {

            
            Iterator<TicketDetalle> it = ticketDetalles.iterator();
            
            int UltimoIndice = 0;
            for(int i= 0; i < ticketDetalles.size();i++){
                TicketDetalle dt = ticketDetalles.get(i);
                if(d.getLinea().equals(dt.getLinea())){
                    UltimoIndice = i;
                    break;
                }
            }
            
            ticketDetalles.remove(UltimoIndice);

            total = new BigDecimal(0);

            for (TicketDetalle dt : ticketDetalles) {
                total = total.add(dt.getTotal());
            }

        }
    }

    public void addImpuesto(TicketImpuesto ti) {
        if (ticketImpuestos == null) {
            ticketImpuestos = new ArrayList<TicketImpuesto>();
        }
        ticketImpuestos.add(ti);

        totalIVA = new BigDecimal(0);
        for (TicketImpuesto dt : ticketImpuestos) {
            totalIVA = totalIVA.add(dt.getMonto());
        }

        totalNeto = total.subtract(totalIVA);
        System.out.println("Total IVA: " + totalIVA);
        System.out.println("Total Neto: " + totalNeto);
    }

    public void addFormaPago(FormaPago fp) {
        if (ticketFormasPago == null) {
            ticketFormasPago = new ArrayList<FormaPago>();
        }
        ticketFormasPago.add(fp);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getEmision() {
        return emision;
    }

    public void setEmision(Date emision) {
        this.emision = emision;
    }

    public SesionTPV getSesionTPV() {
        return sesionTPV;
    }

    public void setSesionTPV(SesionTPV sesionTPV) {
        this.sesionTPV = sesionTPV;
    }

    public SocioComercial getSocioComercial() {
        return socioComercial;
    }

    public void setSocioComercial(SocioComercial socioComercial) {
        this.socioComercial = socioComercial;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(BigDecimal totalNeto) {
        this.totalNeto = totalNeto;
    }

    public BigDecimal getTotalIVA() {
        return totalIVA;
    }

    public void setTotalIVA(BigDecimal totalIVA) {
        this.totalIVA = totalIVA;
    }

    public BigDecimal getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(BigDecimal totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public BigDecimal getTotalExento() {
        return totalExento;
    }

    public void setTotalExento(BigDecimal totalExento) {
        this.totalExento = totalExento;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public List<TicketImpuesto> getTicketImpuestos() {
        return ticketImpuestos;
    }

    public void setTicketImpuestos(List<TicketImpuesto> ticketImpuestos) {
        this.ticketImpuestos = ticketImpuestos;
    }

    public List<TicketDetalle> getTicketDetalles() {
        if (ticketDetalles == null) {
            ticketDetalles = new ArrayList<TicketDetalle>();
        }
        return ticketDetalles;
    }

    public void setTicketDetalles(List<TicketDetalle> ticketDetalles) {
        this.ticketDetalles = ticketDetalles;
    }

    public List<FormaPago> getTicketFormasPago() {
        return ticketFormasPago;
    }

    public void setTicketFormasPago(List<FormaPago> ticketFormasPago) {
        this.ticketFormasPago = ticketFormasPago;
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
        if (!(object instanceof Ticket)) {
            return false;
        }
        Ticket other = (Ticket) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.puntoventa.persisitencia.Ticket[ id=" + id + " ]";
    }
}
