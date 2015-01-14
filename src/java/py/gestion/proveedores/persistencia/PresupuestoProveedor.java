/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.persistencia;

import py.gestion.adm.persistencia.Obra;
import py.gestion.adm.persistencia.Empresa;

import py.gestion.adm.persistencia.Estado;
import py.gestion.adm.persistencia.Moneda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Cotizacion;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = PresupuestoProveedor.TODOS, query = "select p from PresupuestoProveedor p")})
public class PresupuestoProveedor implements Serializable {

    public static final String TODOS = "package py.gestionpymes.jpa.compra.PresupuestoProveedor.TODOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Obra obra;
    @Version
    private Long version;
    private Long numero;
    @ManyToOne
    private Proveedor proveedor;
    @ManyToOne
    private Moneda moneda;
    @ManyToOne
    private Cotizacion cotizacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCulminacion;
    private Estado estado;
    private double total;
    private double totalLocal;
    @OneToMany(mappedBy = "presupuesto")
    private List<DetPresupuestoProveedor> detalles;

    public List<DetPresupuestoProveedor> getDetalles() {
        if (detalles == null) {
            detalles = new ArrayList<DetPresupuestoProveedor>();
        }
        return detalles;
    }

    public void setDetalles(List<DetPresupuestoProveedor> detalles) {
        this.detalles = detalles;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cotizacion getCotizacion() {
        if (cotizacion == null) {
            cotizacion = new Cotizacion();
        }
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaCulminacion() {
        return fechaCulminacion;
    }

    public void setFechaCulminacion(Date fechaCulminacion) {
        this.fechaCulminacion = fechaCulminacion;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalLocal() {
        return totalLocal;
    }

    public void setTotalLocal(double totalLocal) {
        this.totalLocal = totalLocal;
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
        if (!(object instanceof PresupuestoProveedor)) {
            return false;
        }
        PresupuestoProveedor other = (PresupuestoProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.compra.PresupuestoProveedor[ id=" + id + " ]";
    }
}
