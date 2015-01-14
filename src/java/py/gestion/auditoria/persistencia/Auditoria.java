/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.auditoria.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author Acer
 */
@Entity
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String objeto;
    private String accion;
    private String descripcion;

    //Auditoria
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date creacion;
    @ManyToOne
    private Usuario usuarioCreacion;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
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
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.auditoria.persistencia.Auditoria[ id=" + id + " ]";
    }
}
