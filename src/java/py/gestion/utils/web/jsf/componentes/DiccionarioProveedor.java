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
import py.gestion.proveedores.servicios.ProveedorService;
import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class DiccionarioProveedor implements Serializable{
    @EJB
    private ProveedorService proveedorDAO;
    private List<Proveedor> lista;
    
    @PostConstruct
    public void init(){
        carga();
    }

    public List<Proveedor> getLista() {
        if(lista == null || lista.isEmpty()){
            carga();
        }
        return lista;
    }

    public void setLista(List<Proveedor> lista) {
        this.lista = lista;
    }
    
    public void carga(){
        lista = proveedorDAO.findAll();
    }
}
