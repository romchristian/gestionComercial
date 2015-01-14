/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Banco.TODOS, query = "select b from Banco b")})
public class Banco implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String TODOS = "package py.gestionpymes.jpa.bancos.Bancos.TODOS";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Long version;
    private String nombre;

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
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Banco)) {
            return false;
        }
        Banco other = (Banco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.bancos.Banco[ id=" + id + " ]";
    }
    
}
