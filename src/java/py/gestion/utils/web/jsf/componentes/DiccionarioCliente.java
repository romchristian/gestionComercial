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
import py.gestion.clientes.servicios.ClienteDAO;
import py.gestion.clientes.persistencia.Cliente;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class DiccionarioCliente implements Serializable{
    @EJB
    private ClienteDAO dao;
    private List<Cliente> lista;
    
    @PostConstruct
    public void init(){
        carga();
    }

    public List<Cliente> getLista() {
        if(lista == null || lista.isEmpty()){
            carga();
        }
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }
    
    public void carga(){
        lista = dao.findAll();
    }
}
