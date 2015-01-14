/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import py.gestion.seguridad.persistencia.Usuario;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.Sucursal;



/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class Credencial implements Serializable {

    private Usuario usuario;
    private Empresa empresa;
    private Sucursal sucursal;

    @Produces
    @UsuarioLogeado
    public Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Produces
    @EmpresaLogueada
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Produces
    @SucursalLogeada
    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
    
    
}
