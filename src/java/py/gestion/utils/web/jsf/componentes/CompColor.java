/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.adm.servicios.ColorDAO;
import py.gestion.stock.persistencia.Color;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompColor implements Serializable {

    @EJB
    private ColorDAO ejb;
    private Color colorElegido;
    private List<Color> colores;

    public Color getColorElegido() {
        return colorElegido;
    }

    public void setColorElegido(Color colorElegido) {
        this.colorElegido = colorElegido;
    }

    public List<Color> getColores() {
        if (colores == null) {
            colores = ejb.findAll();
        }
        return colores;
    }
}
