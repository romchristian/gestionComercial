/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.adm.persistencia.Direccion;
import py.gestion.adm.persistencia.Localidad;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDirecciones implements Serializable {

    @Inject
    private DiccionarioLocalidad dicLocalidad;
    private Direccion direccion;
    private List<Direccion> lista;
   
    
    public Direccion getDireccion() {
        if (direccion == null) {
            direccion = new Direccion();
        }
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }


    public List<Direccion> getLista() {
        if (lista == null) {
            lista = new ArrayList<Direccion>();
        }
        return lista;
    }

    public void setLista(List<Direccion> lista) {
        this.lista = lista;
    }

    public String agrega() {
        direccion.setLocalidad(dicLocalidad.getElegida());
        lista.add(direccion);
        dicLocalidad.setElegida(new Localidad());
        limpia();
        return null;
    }
    
    public void remueve(Direccion direccion){
        lista.remove(direccion);
    }
    
    private void limpia(){
        direccion = new Direccion();
    }
    
        public void marcaPrincipal(Direccion dic) {
//        List listaNueva = new ArrayList();
//        listaNueva.add(ct);
        for( Direccion d: lista){
            if(!dic.igual(d)){
                d.setPrincipal(false);
//                listaNueva.add(c);
            } 
        }
//        lista = listaNueva;
    }
}
