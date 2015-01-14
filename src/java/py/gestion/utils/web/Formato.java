/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import py.gestion.compra.persistencia.enums.EstadoOC;

/**
 *
 * @author christian
 */
@Named
@ApplicationScoped
public class Formato implements Serializable {

    private Locale locale;
    private String patronFecha = "dd/MM/yyyy";
    private String patronFechaHora = "dd/MM/yyyy hh:mm";
    private EstadoOC estadoOC = EstadoOC.AUTORIZADO;

    @PostConstruct
    private void init() {
        locale = new Locale("es", "py");
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getPatronFecha() {
        return patronFecha;
    }

    public void setPatronFecha(String patronFecha) {
        this.patronFecha = patronFecha;
    }

    public EstadoOC getEstadoOC() {
        return estadoOC;
    }

    public void setEstadoOC(EstadoOC estadoOC) {
        this.estadoOC = estadoOC;
    }

    public String getPatronFechaHora() {
        return patronFechaHora;
    }

    public void setPatronFechaHora(String patronFechaHora) {
        this.patronFechaHora = patronFechaHora;
    }
}
