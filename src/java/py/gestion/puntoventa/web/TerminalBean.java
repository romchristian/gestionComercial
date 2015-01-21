/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.adm.servicios.ImpuestoIVADAO;
import py.gestion.puntoventa.persisitencia.FormaPagoEfectivo;
import py.gestion.puntoventa.persisitencia.FormaPagoTarjeta;
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

    private final static int OPERACION_CANTIDAD = 1;
    private final static int OPERACION_PU = 2;
    private final static int OPERACION_DESC = 3;
    private final static int OPERACION_EFE = 4;
    private final static int OPERACION_TARJETA = 5;
    private int operacionActual;
    //Variables para el teclado numerico
    private String cantidad = "";
    private String panelActual = "productosTicket.xhtml";

    public void calculaOpecionLinea() {

        TicketDetalle d = ticketActual.getUltimoDetalle();

        switch (operacionActual) {
            case OPERACION_CANTIDAD:

                String[] aPartes = cantidad.split("\\.");
                System.out.println("Cantidad: " + cantidad);
                System.out.println("aPartes: " + aPartes.length);
                double cantidadOpe = Double.parseDouble(cantidad);
                if (aPartes.length > 1 && Double.parseDouble(aPartes[1]) > 0) {
                    d.setCantidad((new BigDecimal(Double.parseDouble(cantidad))).setScale(3, RoundingMode.HALF_EVEN));
                } else {
                    d.setCantidad((new BigDecimal(Double.parseDouble(cantidad))).setScale(0, RoundingMode.HALF_EVEN));
                }
                break;
            case OPERACION_PU:
                d.setPrecioUnitario((new BigDecimal(Double.parseDouble(cantidad))).setScale(3, RoundingMode.HALF_EVEN));
                break;
            case OPERACION_DESC:
                double cantidadDesc = Double.parseDouble(cantidad);
                if (cantidadDesc <= 100) {
                    BigDecimal cantDe = new BigDecimal(cantidadDesc).setScale(2, RoundingMode.HALF_EVEN);
                    BigDecimal descuentoImporte = d.getPrecioUnitario().multiply(cantDe).divide(new BigDecimal(100)).setScale(0, RoundingMode.HALF_EVEN);
                    d.setPorcentajeDescuento(new BigDecimal(cantidadDesc));
                    d.setPrecioUnitarioNeto(d.getPrecioUnitario().subtract(descuentoImporte));
                }
                break;
            default:
        }

        ticketActual.calculaTotal();
    }

    public void cero() {
        cantidad += "0";
        calculaOpecionLinea();
    }

    public void uno() {
        cantidad += "1";
        calculaOpecionLinea();
    }

    public void dos() {
        cantidad += "2";
        calculaOpecionLinea();
    }

    public void tres() {
        cantidad += "3";
        calculaOpecionLinea();
    }

    public void cuatro() {
        cantidad += "4";
        calculaOpecionLinea();
    }

    public void cinco() {
        cantidad += "5";
        calculaOpecionLinea();
    }

    public void seis() {
        cantidad += "6";
        calculaOpecionLinea();
    }

    public void siete() {
        cantidad += "7";
        calculaOpecionLinea();
    }

    public void ocho() {
        cantidad += "8";
        calculaOpecionLinea();
    }

    public void nueve() {
        cantidad += "9";
        calculaOpecionLinea();
    }

    public void coma() {
        if (!cantidad.contains(".")) {
            cantidad += ".";
            calculaOpecionLinea();
        }
    }

    public void ceroComna() {
        if (cantidad.length() == 0) {
            cantidad += "0.";
            calculaOpecionLinea();
        }
    }

    public void operacionCantidad() {
        cantidad = "";
        operacionActual = OPERACION_CANTIDAD;
    }

    public void operacionPU() {
        cantidad = "";
        operacionActual = OPERACION_PU;
    }

    public void operacionDesc() {
        cantidad = "";
        operacionActual = OPERACION_DESC;
    }

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

    public String getPanelActual() {
        return panelActual;
    }

    public void setPanelActual(String panelActual) {
        this.panelActual = panelActual;
    }

    
    public String pagaEfetivo(){
        operacionActual = OPERACION_EFE;
        preparaPago();
        
        return null;
    }
    
    public String pagaTarjeta(){
        operacionActual = OPERACION_TARJETA;
        preparaPago();
        
        return null;
    }
    
    private void preparaPago() {
        agregaPagoSeleccionado();
        panelActual = "pagosTicket.xhtml";
        
    }

    private void agregaPagoSeleccionado() {
        switch (operacionActual) {
            case OPERACION_EFE:
                
                FormaPagoEfectivo efe = new FormaPagoEfectivo();
                efe.setDescripcion("Dinero en Efectivo (G)");
                efe.setImporte(ticketActual.getTotal());
                efe.setTicket(ticketActual);
                ticketActual.addTicketPago(efe);
                break;
            case OPERACION_TARJETA:
                FormaPagoTarjeta tarjeta = new FormaPagoTarjeta();
                tarjeta.setDescripcion("Tarjeta (G)");
                tarjeta.setImporte(ticketActual.getTotal());
                tarjeta.setTicket(ticketActual);
                ticketActual.addTicketPago(tarjeta);
        }

    }

    public void atras() {
        panelActual = "productosTicket.xhtml";
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
                    productoActual.getPrecioVenta(), productoActual.getIva(), null);
            System.out.println("Linea : " + d.getLinea());
            ticketActual.addDetalle(d);
            lineaActual++;

        }

        cantidad = "";
        return null;
    }

    public void quitaDetalle(TicketDetalle d) {
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
        for (ImpuestoIVA i : impuestoIVADAO.findAll()) {
            ticketActual.addImpuesto(new TicketImpuesto(i, ticketActual));
        }
        ticketDAO.create(ticketActual);
        ticketActual = new Ticket();
        panelActual = "productosTicket.xhtml";
        return null;
    }

    public String suspenderSesion() {

        sesionActual.setEstado("SUSPENDIDO");
        sesionTPVDAO.edit(sesionActual);
        return "listado.xhtml";
    }

}
