/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author ACER
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Distrito.TODOS, query = "select d from Distrito d"),
    @NamedQuery(name = Distrito.POR_DEPARTAMENTO , query = "select d from Distrito d where d.departamento = :departamento")})
public class Distrito implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TODOS = "py.gestionpymes.adm.persistencia.Distrito.TODOS";
    public static final String POR_DEPARTAMENTO = "py.gestionpymes.adm.persistencia.Distrito.POR_DEPARTAMENTO";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Departamento departamento;
    private String nombre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
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
        if (!(object instanceof Distrito)) {
            return false;
        }
        Distrito other = (Distrito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
