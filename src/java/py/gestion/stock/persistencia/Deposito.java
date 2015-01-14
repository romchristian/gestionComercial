/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Deposito.TODOS, query = "select d from Deposito d"),
    @NamedQuery(name = Deposito.ACTIVOS, query = "select d from Deposito d where d.estado = :estado")})
public class Deposito implements Serializable {

    public static final String TODOS = "py.gestionpymes.jpa.stock.Deposito.TODOS";
    public static final String ACTIVOS = "py.gestionpymes.jpa.stock.Deposito.ACTIVOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Estado estado;

    public Deposito() {
        this.estado = Estado.ACTIVO;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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
        if (!(object instanceof Deposito)) {
            return false;
        }
        Deposito other = (Deposito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.Deposito[ id=" + id + " ]";
    }
}
