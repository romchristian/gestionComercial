/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author cromero
 */
@Entity
public abstract class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Usuario usuario;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha = new Date();
    private String nombre;
    private String responsable;

    public Evento() {
    }

    public Evento(String nombre) {
        this.nombre = nombre;
    }
    
    

    public Evento(Usuario usuario, String nombre) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.responsable = usuario.getNombre() + " " + usuario.getApellido();
    }
    
    

    public String getResponsable() {
        return responsable;
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

    public Date getFecha() {
        return fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        responsable = usuario.getNombre() + " " + usuario.getApellido();
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
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.compra.Eventos[ id=" + id + " ]";
    }
}
