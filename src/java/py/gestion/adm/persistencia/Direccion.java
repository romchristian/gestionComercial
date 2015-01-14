/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.clientes.persistencia.enums.TipoDireccion;

/**
 *
 * @author cromero
 */
@Entity
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoDireccion tipo;
    private String direccion;
    private String nrocasa;
    @ManyToOne
    private Localidad localidad;
    private boolean principal;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToOne
    private Cliente cliente;

    public Direccion() {
        estado = Estado.ACTIVO;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
    

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TipoDireccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDireccion tipo) {
        this.tipo = tipo;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNrocasa() {
        return nrocasa;
    }

    public void setNrocasa(String nrocasa) {
        this.nrocasa = nrocasa;
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
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    public boolean igual(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Direccion other = (Direccion) obj;
        if (this.tipo != other.tipo) {
            return false;
        }
        if ((this.direccion == null) ? (other.direccion != null) : !this.direccion.equals(other.direccion)) {
            return false;
        }
        if ((this.nrocasa == null) ? (other.nrocasa != null) : !this.nrocasa.equals(other.nrocasa)) {
            return false;
        }
        if (this.localidad != other.localidad && (this.localidad == null || !this.localidad.equals(other.localidad))) {
            return false;
        }
        if (this.principal != other.principal) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.adm.Direccion[ id=" + id + " ]";
    }
}
