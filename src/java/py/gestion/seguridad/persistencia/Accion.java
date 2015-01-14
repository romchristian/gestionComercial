/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Accion.TODOS, query = "select a from Accion a"),
    @NamedQuery(name = Accion.POR_NOMBRE, query = "select a from Accion a where a.nombre = :nombre")})
public class Accion implements Serializable {

    public static final String TODOS = "py.gestionpymes.seguridad.Accion.TODOS";
    public static final String POR_NOMBRE = "py.gestionpymes.seguridad.Accion.POR_NOMBRE";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String nombre;
    @ManyToMany(mappedBy = "acciones")
    private List<Rol> roles;

    public Accion() {
    }

    public Accion(String nombre) {
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
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
        final Accion other = (Accion) obj;
        if ((this.nombre == null) ? (other.nombre != null) : !this.nombre.equals(other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        return hash;
    }
}
