/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import py.gestion.seguridad.servicios.RolDAO;
import py.gestion.seguridad.servicios.AccionDAO;
import py.gestion.seguridad.persistencia.Rol;
import py.gestion.seguridad.persistencia.Accion;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.jboss.weld.jsf.JsfApiAbstraction;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class RolBean extends BeanGenerico<Rol> {

    @EJB
    private RolDAO ejb;
    @EJB AccionDAO accionDAO;
    @Inject private PermisosADM permisosADM;
    

    @Override
    public AbstractDAO<Rol> getEjb() {
        return ejb;
    }

    @Override
    public Rol getNuevo() {
        return new Rol();
    }

    @Override
    public String create() {
        Rol R = getEjb().create(getActual());
        String[] acciones = permisosADM.getSeleccionados();
        
        R.setAcciones(new ArrayList<Accion>());
        
        for(int i = 0; i < acciones.length; i++){
            Accion a = accionDAO.findInsert(acciones[i]);
            R.getAcciones().add(a);
        }
        
        getEjb().edit(R);
        JsfUtil.addSuccessMessage("Se creó exitosamente!");
        return "listado.xhtml";
    }

    
    
    
}
