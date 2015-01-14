/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import py.gestion.utils.servicios.QueryParameter;
import py.gestion.utils.web.filtros.Filtros;

/**
 *
 * @author cromero
 */
@Entity
public abstract class FiltroRangoFecha extends Filtro{
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;

    public FiltroRangoFecha() {
    }
    
    public FiltroRangoFecha(String nombre) {
        super(nombre);
        recarga();
    }
    
    
    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    private void recarga() {
        GregorianCalendar gc = new GregorianCalendar(new Locale("es", "py"));
        gc.setTime(new Date());
        fin = gc.getTime();
        gc.add(Calendar.MONTH, -1);
        inicio = gc.getTime();
    }
    
    @Override
    public String getFiltro(StringBuilder sb, QueryParameter queryParameter) {
        System.out.println("inicio: " + inicio);
        System.out.println("fin:    " + fin);
        sb.append(" and ").append(Filtros.OBJ).append(".").append(getCampo());
        sb.append(" between :inicio and :fin ");
        queryParameter.and("inicio", inicio);
        queryParameter.and("fin", fin);
        return sb.toString();
    }
    
    public abstract String getCampo();
    
}
