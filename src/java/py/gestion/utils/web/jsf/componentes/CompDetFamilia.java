/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.stock.persistencia.Familia;


/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDetFamilia implements Serializable {

    private Familia familia;
    private List<Familia> familias;

    @PostConstruct
    private void init() {
        inicializa();
    }

    public List<Familia> getFamilias() {
        return familias;
    }

    public void setFamilias(List<Familia> familias) {
        this.familias = familias;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public void agrega() {
        familias.add(familia);
    }

    public void remover(Familia d) {
        familias.remove(d);
    }

    public void inicializa() {
        familias = new ArrayList<Familia>();
    }
}
