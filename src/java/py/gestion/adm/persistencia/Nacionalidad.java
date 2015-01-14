/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author cromero
 */
@Entity

@NamedQueries({
    @NamedQuery(name = Nacionalidad.TODOS, query = "select n from Nacionalidad n")})
public class Nacionalidad implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TODOS = "py.gestionpymes.adm.persistencia.Nacionalidad.TODOS";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Pais pais;
    private String gentilicio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGentilicio() {
        return gentilicio;
    }

    public void setGentilicio(String gentilicio) {
        this.gentilicio = gentilicio;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
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
        if (!(object instanceof Nacionalidad)) {
            return false;
        }
        Nacionalidad other = (Nacionalidad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return gentilicio;
    }
}
