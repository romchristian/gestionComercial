/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.filtros;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.naming.ldap.HasControls;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import py.gestion.utils.persistencia.Consulta;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.persistencia.FiltroRangoFecha;
import py.gestion.utils.servicios.ConsultaDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
public abstract class Filtros implements Serializable {
    
    @EJB
    private ConsultaDAO consultaDAO;
    public static final String OBJ = "obj";
    private List<Filtro> filtrosDisponibles;
    private List<Filtro> filtrosSeleccionados;
    private UIComponent dataGridDisponibles = null;
    private Consulta consultaActual;
    private Map<String, Double> mapTotales = new HashMap<String, Double>();
    
    
    public UIComponent getDataGridDisponibles() {
        return dataGridDisponibles;
    }
    
    public void setDataGridDisponibles(UIComponent dataGridDisponibles) {
        this.dataGridDisponibles = dataGridDisponibles;
    }
    
    public List<Filtro> getFiltrosDisponibles() {
        if (filtrosDisponibles == null) {
            filtrosDisponibles = new ArrayList<Filtro>();
            filtrosDisponibles.addAll(getFiltros());
        }
        return filtrosDisponibles;
    }
    
    public void setFiltrosDisponibles(List<Filtro> filtrosDisponibles) {
        this.filtrosDisponibles = filtrosDisponibles;
    }
    
    public List<Filtro> getFiltrosSeleccionados() {
        if (filtrosSeleccionados == null) {
            filtrosSeleccionados = new ArrayList<Filtro>();
        }
        
        return filtrosSeleccionados;
    }
    
    public void setFiltrosSeleccionados(List<Filtro> filtrosSeleccionados) {
        this.filtrosSeleccionados = filtrosSeleccionados;
    }
    
    public void onCarDrop(DragDropEvent ddEvent) {
        Filtro filtro = ((Filtro) ddEvent.getData());
        filtrosSeleccionados.add(filtro);
        filtrosDisponibles.remove(filtro);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(filtro.getOpenDialog());
    }
    
    public void quitarFiltro(Filtro f) {
        filtrosDisponibles.add(f);
        filtrosSeleccionados.remove(f);
    }
    
    public boolean hayFiltros() {
        boolean R = false;
        if (filtrosSeleccionados != null && !filtrosSeleccionados.isEmpty()) {
            R = true;
        }
        return R;
    }
    
    public String getFiltro(QueryParameter queryParameter) {
        StringBuilder sb = new StringBuilder("select ")
                .append(OBJ)
                .append(" from ")
                .append(getNombreClase())
                .append(" ")
                .append(OBJ)
                .append(" where 1=1 ");
        
        for (Filtro f : getFiltrosSeleccionados()) {
            f.getFiltro(sb, queryParameter);
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String getTotales(QueryParameter queryParameter) {
        
        StringBuilder sb = new StringBuilder("select ")
                .append(OBJ)
                .append(" from ")
                .append(getNombreClase())
                .append(" ")
                .append(OBJ)
                .append(" where 1=1 ");
        
        for (Filtro f : getFiltrosSeleccionados()) {
            f.getFiltro(sb, queryParameter);
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public abstract String getNombreClase();
    
    public List<Filtro> getFiltros(){
        List<Filtro> R = new ArrayList<Filtro>();
        return R;
    }
    
    public List<Consulta> getConsultas() {
        return consultaDAO.findAll(getNombreClase());
    }
    
    public Consulta getConsultaActual() {
        if (consultaActual == null) {
            consultaActual = new Consulta();
            consultaActual.setNombreClase(getNombreClase());
        }
        return consultaActual;
    }
    
    public void setConsultaActual(Consulta consultaActual) {
        this.consultaActual = consultaActual;
    }

    public String guardaConsulta() {
        for (Filtro f : filtrosSeleccionados) {
            getConsultaActual().asociaFiltro(f);
        }
        setConsultaActual(consultaDAO.edit(getConsultaActual()));
        
        
        for(Filtro f: getConsultaActual().getFiltros()){
            for(Filtro fs: getFiltrosSeleccionados()){
                if(fs.getNombre().compareTo(f.getNombre()) ==  0){
                    fs = f;
                }
            }
        }
        //filtrosSeleccionados = consultaActual.getFiltros();
        //getFiltrosSeleccionados().clear();
        //getFiltrosSeleccionados().addAll(getConsultaActual().getFiltros());
        
        
        
        return null;
    }
}
