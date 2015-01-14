/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.servicios.EmpresaDAO;
import py.gestion.adm.persistencia.Empresa;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class EmpresaBean extends BeanGenerico<Empresa> {

    @EJB
    private EmpresaDAO ejb;
    private long id;

    @Override
    public AbstractDAO<Empresa> getEjb() {
        return ejb;
    }

    @Override
    public Empresa getNuevo() {
        return new Empresa();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String cargaDatos(){
        setActual(ejb.find(Long.valueOf(id)));
        return null;
    }

}
