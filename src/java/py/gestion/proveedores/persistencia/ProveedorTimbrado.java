/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import org.hibernate.validator.constraints.Length;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ProveedorTimbrado.TODOS, query = "select p from ProveedorTimbrado p"),
    @NamedQuery(name = ProveedorTimbrado.PROVEEDOR, query = "select p from ProveedorTimbrado p where p.proveedor = :proveedor and p.estado = :estado")
})
public class ProveedorTimbrado implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TODOS = "py.syscvsa.jpa.compra.ProveedorTimbrado.TODOS";
    public static final String PROVEEDOR = "py.syscvsa.jpa.compra.ProveedorTimbrado.PROVEEDOR";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Proveedor proveedor;
    private Long timbrado;
    @Length(min = 3, max = 3)
    private String codEst;
    @Length(min = 3, max = 3)
    private String codSuc;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vigencia;

    public ProveedorTimbrado() {
        estado = Estado.ACTIVO;
    }

    public ProveedorTimbrado(Proveedor proveedor, Long timbrado, String codEst, String codSuc, Date vigencia) {
        this();
        this.proveedor = proveedor;
        this.timbrado = timbrado;
        this.codEst = codEst;
        this.codSuc = codSuc;
        this.vigencia = vigencia;
    }
    


    public Estado getEstado() {
        if (vigencia.before(new Date())) {
            estado = Estado.INACTIVO;
        }
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Long getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Long timbrado) {
        this.timbrado = timbrado;
    }

    public String getCodEst() {
        return codEst;
    }

    public void setCodEst(String codEst) {
        this.codEst = codEst;
    }

    public String getCodSuc() {
        return codSuc;
    }

    public void setCodSuc(String codSuc) {
        this.codSuc = codSuc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof ProveedorTimbrado)) {
            return false;
        }
        ProveedorTimbrado other = (ProveedorTimbrado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return timbrado + ": " + codEst + "-" + codSuc;
    }
}
