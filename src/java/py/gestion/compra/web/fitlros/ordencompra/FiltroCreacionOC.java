/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.fitlros.ordencompra;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class FiltroCreacionOC implements Serializable {

    private Date inicio;
    private Date fin;

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

    @PostConstruct
    private void init() {
        recarga();
    }

    public void recarga() {
        GregorianCalendar gc = new GregorianCalendar(new Locale("es", "py"));
        gc.setTime(new Date());
        fin = gc.getTime();
        gc.add(Calendar.MONTH, -1);
        inicio = gc.getTime();
    }

    public String getFiltro(StringBuilder sb, boolean esWhere, QueryParameter queryParameter) {
        if (esWhere) {
            sb.append(" where ");
        } else {
            sb.append(" and ");
        }
        sb.append(" oc.creacion between :inicio and :fin ");
        queryParameter.and("inicio", inicio);
        queryParameter.and("fin", fin);
        return sb.toString();
    }
}
