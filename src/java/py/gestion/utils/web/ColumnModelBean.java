/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.utils.persistencia.ColumnModel;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.ColumnModelDAO;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class ColumnModelBean extends BeanGenerico<ColumnModel> {

    @EJB
    private ColumnModelDAO ejb;

    @Override
    public AbstractDAO<ColumnModel> getEjb() {
        return ejb;
    }

    @Override
    public ColumnModel getNuevo() {
        return new ColumnModel();
    }
}
