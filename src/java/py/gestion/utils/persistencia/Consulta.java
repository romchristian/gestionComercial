/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Consulta.TODOS, query = "select c from Consulta c"),
    @NamedQuery(name = Consulta.POR_CLASE, query = "select c from Consulta c where c.nombreClase = :nombreClase and c.usuario = :usuario")})
public class Consulta implements Serializable {

    public static final String TODOS = "py.syscvsa.utils.persistencia.Consulta.TODOS";
    public static final String POR_CLASE = "py.syscvsa.utils.persistencia.Consulta.POR_CLASE";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String nombreClase;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(mappedBy = "consulta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Filtro> filtros;

    public void asociaFiltro(Filtro f) {
        if (filtros == null) {
            filtros = new ArrayList<Filtro>();
        }
        if (f.getConsulta() != this) {
            f.setConsulta(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public List<Filtro> getFiltros() {
        return filtros;
    }

    public void setFiltros(List<Filtro> filtros) {
        this.filtros = filtros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.utils.persistencia.Consulta[ id=" + id + " ]";
    }
}
