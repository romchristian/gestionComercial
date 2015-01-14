/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.persistencia;

import py.gestion.seguridad.persistencia.Accion;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Rol.TODOS, query = "select r from Rol r"),
    @NamedQuery(name = Rol.POR_NOMBRE, query = "select r from Rol r where r.nombre = :nombre")})
public class Rol implements Serializable{
    public static final String TODOS = "py.gestionpymes.seguridad.Rol.TODOS";
    public static final String POR_NOMBRE = "py.gestionpymes.seguridad.Rol.POR_NOMBRE";
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToMany
    private List<Accion> acciones;

    public Rol() {
        this.estado = Estado.ACTIVO;
    }

    public Rol (String nombre) {
        this();
        this.nombre = nombre;
    }
    public Rol(String nombre, List<Accion> acciones) {
        
        this(nombre);
        this.acciones = acciones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rol other = (Rol) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }
    
    


    
    
}
