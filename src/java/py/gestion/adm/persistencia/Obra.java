/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Obra.TODOS, query = "select o from Obra o")})
public class Obra implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final String TODOS = "package py.gestionpymes.jpa.adm.Obra.TODOS";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Long version;
    private String nombre;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Sucursal sucursal;
    private Estado estado;

    public Obra() {
        this.estado = Estado.ACTIVO;
    }

    public Obra(String nombre) {
        this();
        this.nombre = nombre;
    }
    
    

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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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
        if (!(object instanceof Obra)) {
            return false;
        }
        Obra other = (Obra) object;
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
