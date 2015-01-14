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
public class FiltroNroOC implements Serializable {

    private String nro;

    public String getNro() {
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    @PostConstruct
    private void init() {
        recarga();
    }

    public void recarga() {
        
    }

    public String getFiltro(StringBuilder sb, boolean esWhere, QueryParameter queryParameter) {
        if (esWhere) {
            sb.append(" where ");
        } else {
            sb.append(" and ");
        }
        sb.append(" oc.numero = :nro ");
        queryParameter.and("nro", nro);
        
        return sb.toString();
    }
}
