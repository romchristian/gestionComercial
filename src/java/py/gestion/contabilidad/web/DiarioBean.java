/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.contabilidad.web;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.gestion.contabilidad.persistencia.Diario;
import py.gestion.contabilidad.servicio.DiarioDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class DiarioBean extends BeanGenerico<Diario> {

    @EJB
    private DiarioDAO ejb;
    
    @Override
    public AbstractDAO<Diario> getEjb() {
        return ejb;
    }

    @Override
    public Diario getNuevo() {
        return new Diario();
    }

}
