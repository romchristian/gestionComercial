/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ConfRed.TODOS, query = "select c from ConfRed c"),
    @NamedQuery(name = ConfRed.POR_NOMBRE, query = "select c from ConfRed c where c.nombre = :nombre")})
public class ConfRed extends Parametro {

    public static final String TODOS = "package py.gestionpymes.jpa.adm.ConfRed.TODOS";
    public static final String POR_NOMBRE = "package py.gestionpymes.jpa.adm.ConfRed.POR_NOMBRE";
    
    private boolean tieneProxy;
    private String servidor;
    private Integer puerto;

    public ConfRed() {
    }

    public ConfRed(boolean tieneProxy, String servidor, Integer puerto, String nombre) {

        super(nombre);
        this.tieneProxy = tieneProxy;
        this.servidor = servidor;
        this.puerto = puerto;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public boolean isTieneProxy() {
        return tieneProxy;
    }

    public void setTieneProxy(boolean tieneProxy) {
        this.tieneProxy = tieneProxy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfRed)) {
            return false;
        }
        ConfRed other = (ConfRed) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
