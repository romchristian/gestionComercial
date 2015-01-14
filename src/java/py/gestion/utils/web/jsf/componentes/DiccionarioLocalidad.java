/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import py.gestion.adm.persistencia.Localidad;
import py.gestion.adm.servicios.LocalidadDAO;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class DiccionarioLocalidad implements Serializable {

    @EJB
    private LocalidadDAO localidadDAO;
    private List<Localidad> lista;
    private Localidad elegida;

    @PostConstruct
    public void init() {
        carga();
    }

    public Localidad getElegida() {
        return elegida;
    }

    public void setElegida(Localidad elegida) {
        this.elegida = elegida;
    }

    public List<Localidad> getLista() {
        if (lista == null) {
            carga();
        }
        return lista;
    }

    public void setLista(List<Localidad> lista) {
        this.lista = lista;
    }

    public void carga() {
        lista = localidadDAO.findAll();
    }
    
    public void limpia(){
        elegida = null;
    }
    
     public void onRowDblselect(Localidad localidad) {  
         elegida = localidad;
     }
}
