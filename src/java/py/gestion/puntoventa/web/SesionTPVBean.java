/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.event.FlowEvent;
import py.gestion.contabilidad.persistencia.MetodoPago;
import py.gestion.puntoventa.persisitencia.PuntoVenta;
import py.gestion.puntoventa.persisitencia.SesionTPV;
import py.gestion.puntoventa.persisitencia.Ticket;
import py.gestion.puntoventa.persisitencia.TipoMetodoPago;
import py.gestion.puntoventa.persisitencia.TipoValorEfectivo;
import py.gestion.puntoventa.persisitencia.ValorEfectivo;
import py.gestion.puntoventa.persisitencia.ValorMoneda;
import py.gestion.contabilidad.servicio.MetodoPagoDAO;
import py.gestion.puntoventa.servicio.SesionTPVDAO;
import py.gestion.puntoventa.servicio.TransaccionServicio;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class SesionTPVBean extends BeanGenerico<SesionTPV> {
    
    @EJB
    private SesionTPVDAO ejb;
    @EJB
    private MetodoPagoDAO ejbMetodoPago;
    private ValorMoneda valorMoneda;
    @EJB
    private TransaccionServicio transaccionServicio;
    
    @Override
    public AbstractDAO<SesionTPV> getEjb() {
        return ejb;
    }
    
    @Override
    public SesionTPV getNuevo() {
        return new SesionTPV();
    }
    
    @Override
    public String edit() {
        SesionTPV s = ejb.edit(getActual());
        
        // Simular tickets
        
        
        
        setActual(null);
        return "listado.xhtml";
    }
    
    public ValorMoneda getValorMoneda() {
        if (valorMoneda == null) {
            valorMoneda = new ValorMoneda();
        }
        return valorMoneda;
    }
    
    public void setValorMoneda(ValorMoneda valorMoneda) {
        this.valorMoneda = valorMoneda;
    }
    
    @Override
    public String preparaCreacion() {
        setActual(new SesionTPV());

        //getActual().setUsuario("");
        getActual().setEstado("Creado");
        
        
        return "nuevo.xhtml";
    }
    
    public void siCamabiaTPV(ValueChangeEvent event) {
        PuntoVenta pv = (PuntoVenta) event.getNewValue();
        
        if (pv != null) {
            if (getActual().getValorEfectivos() == null) {
                getActual().setValorEfectivos(new ArrayList<ValorEfectivo>());
            }
            
            System.out.print("HOLA1");
            
            System.out.print("HOLA2");
            
            List<MetodoPago> metodos = pv.getMetodoPagos();
            
            if (metodos == null || metodos.isEmpty()) {
                System.out.print("HOLA2.1");
                metodos = ejbMetodoPago.findAll();
                
            }
            
            for (MetodoPago m : metodos) {
                System.out.print("HOLA3");
                
                if (m.getTipoMetodoPago() == TipoMetodoPago.EFECTIVO) {
                    System.out.print("HOLA4");
                    for (ValorMoneda vm : m.getValoresMonedas()) {
                        System.out.print("Valor: " + vm.getDenominacion());
                        ValorEfectivo ve = new ValorEfectivo();
                        ve.setDenominacionMoneda(vm.getDenominacion());
                        ve.setCantidad(0);
                        ve.setTipo(TipoValorEfectivo.INICIAL);
                        ve.setSesionTPV(getActual());
                        
                        getActual().getValorEfectivos().add(ve);
                    }
                }
            }
            
        }
        
    }
    
    public String onFlowProcess(FlowEvent event) {
        if (event.getNewStep().compareToIgnoreCase("valores") == 0) {
        }
        return event.getNewStep();
    }
}
