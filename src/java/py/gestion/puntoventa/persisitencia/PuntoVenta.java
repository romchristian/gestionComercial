/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Acer
 */
@Entity
public class PuntoVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @ManyToOne
    private Diario diario;
    @ManyToOne
    private Secuencia secuencia;
    @ManyToMany
    private List<MetodoPago> metodoPagos;

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

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }

    public Secuencia getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Secuencia secuencia) {
        this.secuencia = secuencia;
    }

    public List<MetodoPago> getMetodoPagos() {
        return metodoPagos;
    }

    public void setMetodoPagos(List<MetodoPago> metodoPagos) {
        this.metodoPagos = metodoPagos;
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
        if (!(object instanceof PuntoVenta)) {
            return false;
        }
        PuntoVenta other = (PuntoVenta) object;
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
