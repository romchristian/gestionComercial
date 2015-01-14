/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.clientes.persistencia.ContactoTelefonico;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompContactoTelefonico implements Serializable {

    private ContactoTelefonico contacto;
    private List<ContactoTelefonico> lista;

    public List<ContactoTelefonico> getLista() {
        if (lista == null) {
            lista = new ArrayList<ContactoTelefonico>();
        }
        return lista;
    }

    public void setLista(List<ContactoTelefonico> lista) {
        this.lista = lista;
    }

    public ContactoTelefonico getContacto() {
        if (contacto == null) {
            contacto = new ContactoTelefonico();
        }
        return contacto;
    }

    public void setContacto(ContactoTelefonico contacto) {
        this.contacto = contacto;
    }

    public void agrega() {
        getLista().add(contacto);
        contacto = new ContactoTelefonico();
    }

    
    public void remueve(ContactoTelefonico contactoTelefonico){
        lista.remove(contactoTelefonico);
    }
    public void marcaPrincipal(ContactoTelefonico ct) {
//        List listaNueva = new ArrayList();
//        listaNueva.add(ct);
        for( ContactoTelefonico c: lista){
            if(!ct.igual(c)){
                c.setPrincipal(false);
//                listaNueva.add(c);
            } 
        }
//        lista = listaNueva;
    }
}
