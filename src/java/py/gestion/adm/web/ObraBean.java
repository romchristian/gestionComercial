/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.adm.servicios.EmpresaDAO;
import py.gestion.adm.servicios.ObraDAO;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.Obra;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class ObraBean extends BeanGenerico<Obra> {

    @EJB
    private ObraDAO ejb;

    @Override
    public AbstractDAO<Obra> getEjb() {
        return ejb;
    }

    @Override
    public Obra getNuevo() {
        return new Obra();
    }
}
