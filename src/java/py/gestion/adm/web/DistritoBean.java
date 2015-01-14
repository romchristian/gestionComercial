/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import py.gestion.adm.persistencia.Departamento;
import py.gestion.adm.persistencia.Distrito;
import py.gestion.adm.servicios.DistritoDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class DistritoBean extends BeanGenerico<Distrito> {

    @EJB
    private DistritoDAO ejb;
    private Departamento departamento;

    @Override
    public AbstractDAO<Distrito> getEjb() {
        return ejb;
    }

    @Override
    public Distrito getNuevo() {
        return new Distrito();
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejb.findAll(departamento), true);
    }
   
}
