/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.seguridad.persistencia.Accion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class Autorizador implements Serializable {

    @Inject
    @UsuarioLogeado
    private Usuario usuario;
    private List<Accion> acciones = new ArrayList<Accion>();

    @Produces
    public List<Accion> getAcciones() {
//        acciones = usuario.getRol().getAcciones();
        return acciones;
    }

    public boolean hayPermiso(String permiso) {
        boolean R = verificaPermiso(permiso);


        if (!R) {
            JsfUtil.redirect("sinpermiso");
        }
        return R;
        
    }

    public boolean hayPermisoAccion(String permiso) {
        boolean R = verificaPermiso(permiso);
        return R;
        
    }

    private boolean verificaPermiso(String permiso) {
        boolean R = false;
        for (Accion a : getAcciones()) {
            if (a.getNombre().compareTo(permiso) == 0) {
                R = true;
                break;
            }
        }
        return true;
    }
}
