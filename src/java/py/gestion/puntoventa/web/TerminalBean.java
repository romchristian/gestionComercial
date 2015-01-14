/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.adm.servicios.ImpuestoIVADAO;
import py.gestion.puntoventa.persisitencia.FormaPagoEfectivo;
import py.gestion.puntoventa.persisitencia.SesionTPV;
import py.gestion.puntoventa.persisitencia.Ticket;
import py.gestion.puntoventa.persisitencia.TicketDetalle;
import py.gestion.puntoventa.persisitencia.TicketImpuesto;
import py.gestion.puntoventa.servicio.SesionTPVDAO;
import py.gestion.puntoventa.servicio.TicketDAO;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.servicios.ProductoDAO;

/**
 *
 * @author emelgarejo
 */
@Named
@SessionScoped
public class TerminalBean implements Serializable {

    @EJB
    private SesionTPVDAO sesionTPVDAO;
    @EJB
    private ProductoDAO productoDAO;
    @EJB
    private TicketDAO ticketDAO;
    @EJB
    private ImpuestoIVADAO impuestoIVADAO;
    private SesionTPV sesionActual;
    private Ticket ticketActual;
    private Producto productoActual;
    private String codigoActual;
    private int lineaActual = 1;
    private BigDecimal cantidadActual = BigDecimal.ONE;

    public BigDecimal getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(BigDecimal cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public int getLineaActual() {
        return lineaActual;
    }

    public void setLineaActual(int lineaActual) {
        this.lineaActual = lineaActual;
    }

    public String getCodigoActual() {
        return codigoActual;
    }

    public void setCodigoActual(String codigoActual) {
        this.codigoActual = codigoActual;
    }

    public Producto getProductoActual() {
        return productoActual;
    }

    public void setProductoActual(Producto productoActual) {
        this.productoActual = productoActual;
    }

    public SesionTPV getSesionActual() {
        return sesionActual;
    }

    public void setSesionActual(SesionTPV sesionActual) {
        this.sesionActual = sesionActual;
    }

    public Ticket getTicketActual() {
        if (ticketActual == null) {
            ticketActual = new Ticket();
        }
        return ticketActual;
    }

    public void setTicketActual(Ticket ticketActual) {
        this.ticketActual = ticketActual;
    }

    public String buscar() {
        productoActual = productoDAO.findPorCodigoEstricto(codigoActual);
        agregaDetalle();
        return null;
    }

    public String agregaDetalle() {
        if (productoActual != null) {
            TicketDetalle d = new TicketDetalle(ticketActual, lineaActual,
                    productoActual, cantidadActual,
                    new BigDecimal(40000), productoActual.getIva(), null);
            System.out.println("Linea : " + d.getLinea());
            ticketActual.addDetalle(d);
            lineaActual++;
            
        }
        return null;
    }

    
    public void quitaDetalle(TicketDetalle d){
        ticketActual.remueveDetalle(d);   
        lineaActual--;
    }
    
    private TicketDetalle getUltimoDetalle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public String abrirSesion(SesionTPV sesion) {
        sesionActual = sesion;
        sesionActual.setEstado("ABIERTA");
        sesionTPVDAO.edit(sesionActual);
        ticketActual = new Ticket();
        return "terminal.xhtml";

    }

    public String suspendeSesion() {
        sesionActual.setEstado("SUSPENDIDO");
        sesionTPVDAO.edit(sesionActual);
        return "listado.xhtml";

    }

    // Metodo prueba
    public String crearTicket() {

        FormaPagoEfectivo efe = new FormaPagoEfectivo();
        efe.setDescripcion("Efectivo");
        efe.setImporte(ticketActual.getTotal());
        efe.setTicket(ticketActual);

        for (ImpuestoIVA i : impuestoIVADAO.findAll()) {
            ticketActual.addImpuesto(new TicketImpuesto(i, ticketActual));
        }
        
        ticketActual.addFormaPago(efe);
        ticketDAO.create(ticketActual);

        ticketActual = new Ticket();

        return null;
    }

    public String suspenderSesion() {

        sesionActual.setEstado("SUSPENDIDO");
        sesionTPVDAO.edit(sesionActual);
        return "listado.xhtml";
    }

    
}
