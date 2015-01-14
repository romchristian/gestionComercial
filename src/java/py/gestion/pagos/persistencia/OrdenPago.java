/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.persistencia.Obra;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.persistencia.Columna;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = OrdenPago.TODOS, query = "select op from OrdenPago op"),
    @NamedQuery(name = OrdenPago.POR_ESTADO, query = "select op from OrdenPago op where op.estado = :estado")
})
//@SequenceGenerator(name = "ordenpago_sec", sequenceName = "ordenpago_sec", allocationSize = 1)
public class OrdenPago implements Serializable {

    private static final long serialVersionUID = 1L;
    public final static String TODOS = "py.syscvsa.pagos.persistencia.ordenpago.todos";
    public final static String POR_ESTADO = "py.syscvsa.pagos.persistencia.ordenpago.por_estado";
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordenpago_sec")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Columna(cabecera = "Nro", propiedad = "id")
    private Long id;
    @Columna(cabecera = "Fecha", propiedad = "creacion")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creacion;
    @Columna(cabecera = "Concepto", propiedad = "concepto")
    private String concepto;
    @Columna(cabecera = "Estado", propiedad = "estado")
    @ManyToOne
    private EstadoOrdenPago estado;
    @Columna(cabecera = "Obra", propiedad = "obra")
    @ManyToOne
    private Obra obra;
    @Columna(cabecera = "Proveedor", propiedad = "proveedor")
    @ManyToOne
    private Proveedor proveedor;
    @Columna(cabecera = "Moneda", propiedad = "moneda")
    @ManyToOne
    private Moneda moneda;
    @Columna(cabecera = "Cotización", propiedad = "cotizacion")
    @ManyToOne
    private Cotizacion cotizacion;
    @Columna(cabecera = "Total", propiedad = "total", totalizable = true)
    private double total;
    @Columna(cabecera = "Observación", propiedad = "observacion")
    private String observacion;
    @OneToMany(mappedBy = "ordenPago",cascade= CascadeType.ALL,orphanRemoval=true)
    private List<DetOrdenPago> detalles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public EstadoOrdenPago getEstado() {
        return estado;
    }

    public void setEstado(EstadoOrdenPago estado) {
        this.estado = estado;
    }

    public List<DetOrdenPago> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetOrdenPago> detalles) {
        this.detalles = detalles;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
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
        if (!(object instanceof OrdenPago)) {
            return false;
        }
        OrdenPago other = (OrdenPago) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.pagos.OrdenPago[ id=" + id + " ]";
    }
}
