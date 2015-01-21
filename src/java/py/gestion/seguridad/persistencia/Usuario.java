/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.persistencia;

import py.gestion.seguridad.persistencia.Rol;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import py.gestion.adm.persistencia.Persona;
import py.gestion.adm.persistencia.enums.TipoDocumento;
import py.gestion.seguridad.web.Encryptador;

/**
 *
 * @author elias
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Usuario.TODOS, query = "select u from Usuario u"),
    @NamedQuery(name = Usuario.POR_CREDENCIAL, query = "select u from Usuario u where u.usuario = :usuario")})
public class Usuario extends Persona{
    public static final String TODOS = "py.gestionpymes.seguridad.Usuario.TODOS";
    public static final String POR_CREDENCIAL = "py.gestionpymes.seguridad.Usuario.POR_CREDENCIAL";
    
    private String nombre;
    private String apellido;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    @NotNull
    private String usuario;
    @NotNull
    private String clave;
    @ManyToOne
    private Rol rol;
    @Transient
    private String plainClave;

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, Date fechaNacimiento, String usuario, String clave, Rol rol, TipoDocumento tipoDocumento, String nroDocumento) {
        super(tipoDocumento, nroDocumento);
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
        this.clave = Encryptador.encrypta(clave);
        this.rol = rol;
    }

    public String getPlainClave() {
        return plainClave;
    }

    public void setPlainClave(String plainClave) {
        this.plainClave = plainClave;
    }
    
    
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        
        this.clave = Encryptador.encrypta(clave);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    
}
