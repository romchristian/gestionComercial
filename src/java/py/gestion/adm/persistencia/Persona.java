/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.clientes.persistencia.enums.TipoDocumento;

/**
 *
 * @author christian
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name="persona_seq",sequenceName="persona_seq",allocationSize=1)
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator="persona_seq",strategy= GenerationType.SEQUENCE)
    private Long id;
    
    private String nroDocumento;
    @Version
    private Long version;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Sucursal sucursal;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    private Estado estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    public Persona() {
        this.estado = Estado.ACTIVO;
    }

    public Persona(TipoDocumento tipoDocumento, String nroDocumento) {
        this();
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if ((this.nroDocumento == null) ? (other.nroDocumento != null) : !this.nroDocumento.equals(other.nroDocumento)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.nroDocumento != null ? this.nroDocumento.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.adm.Persona[ id=" + id + " ]";
    }
}
