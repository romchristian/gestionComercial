/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package py.gestion.stock.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.adm.persistencia.Estado;
import py.gestion.utils.persistencia.Entidad;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = UnidadMedida.TODOS, query = "select u from UnidadMedida u")})
public class UnidadMedida implements Serializable , Entidad{
    private static final long serialVersionUID = 1L;
    public static final String TODOS = "package py.gestionpymes.jpa.stock.UnidadMedida.TODOS";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Version
    private Long version;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public UnidadMedida() {
        this.estado = Estado.ACTIVO;
    }

    public UnidadMedida(String nombre) {
        this();
        this.nombre = nombre;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadMedida)) {
            return false;
        }
        UnidadMedida other = (UnidadMedida) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

    @Override
    public String getDescripcion() {
        return nombre;
    }

}
