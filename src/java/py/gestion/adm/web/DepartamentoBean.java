/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.adm.persistencia.Departamento;
import py.gestion.adm.servicios.DepartamentoDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class DepartamentoBean extends BeanGenerico<Departamento> {

    @EJB
    private DepartamentoDAO ejb;

    @Override
    public AbstractDAO<Departamento> getEjb() {
        return ejb;
    }

    @Override
    public Departamento getNuevo() {
        return new Departamento();
    }
    
}
