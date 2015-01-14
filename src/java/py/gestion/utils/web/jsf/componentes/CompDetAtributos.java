/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.stock.persistencia.Atributo;
import py.gestion.stock.persistencia.Color;
import py.gestion.stock.persistencia.Familia;
import py.gestion.stock.persistencia.TipoAtributoProducto;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDetAtributos implements Serializable {

    private TipoAtributoProducto tipo;
    private Color color;
    private List<Atributo> atributos;

    @PostConstruct
    private void init() {
        atributos = new ArrayList<Atributo>();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(List<Atributo> atributos) {
        this.atributos = atributos;
    }

    public TipoAtributoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtributoProducto tipo) {
        this.tipo = tipo;
    }

    public void agrega() {
        if (color != null) {
            atributos.add(color);
            color = null;
        }
    }

    public void remover(Familia d) {
        atributos.remove(d);
    }
}
