/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.persistencia;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DetPresupuestoProveedor.TODOS, query = "select dp from DetPresupuestoProveedor dp")})
public class DetPresupuestoProveedor implements Serializable {
    
    public static final String TODOS = "package py.gestionpymes.jpa.compra.DetPresupuestoProveedor.TODOS";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    @ManyToOne
    private PresupuestoProveedor presupuesto;
    private String descripcion;
    private double cantidad;
    private double monto;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public PresupuestoProveedor getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(PresupuestoProveedor presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
        if (!(object instanceof DetPresupuestoProveedor)) {
            return false;
        }
        DetPresupuestoProveedor other = (DetPresupuestoProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.compra.DetPresupuestoProveedor[ id=" + id + " ]";
    }
    
}
