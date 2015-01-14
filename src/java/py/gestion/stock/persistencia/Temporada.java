/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author cromero
 */
@Entity
public class Temporada implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String cod;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private boolean vencida;

    public Temporada() {
    }

    public Temporada(String nombre, String cod, Date inicio, Date fin) {
        this.nombre = nombre;
        this.cod = cod;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isVencida() {

        if (fin != null && fin.after(new Date())) {
            vencida = true;
        } else {
            vencida = false;
        }
        return vencida;
    }

    public void setVencida(boolean vencida) {
        if (fin != null && fin.after(new Date())) {
            vencida = true;
        } else {
            vencida = false;
        }
        this.vencida = vencida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {

        this.fin = fin;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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
        if (!(object instanceof Temporada)) {
            return false;
        }
        Temporada other = (Temporada) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.Temporada[ id=" + id + " ]";
    }
}
