/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.compra.persistencia.enums.EstadoOC;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.persistencia.Moneda;

import py.gestion.adm.persistencia.Obra;
import py.gestion.stock.persistencia.Deposito;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = OrdenCompra.TODOS, query = "select o from OrdenCompra o"),
    @NamedQuery(name = OrdenCompra.POR_ESTADO, query = "select o from OrdenCompra o where o.estado = :estado"),
    @NamedQuery(name = OrdenCompra.POR_PROVEEDOR, query = "select o from OrdenCompra o where o.proveedor = :proveedor")
})
public class OrdenCompra implements Serializable {

    public static final String TODOS = "py.gestionpymes.jpa.compra.ordenCompra.TODOS";
    public static final String POR_ESTADO = "py.gestionpymes.jpa.compra.ordenCompra.POR_ESTADO";
    public static final String POR_PROVEEDOR = "py.gestionpymes.jpa.compra.ordenCompra.POR_PROVEEDOR";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    @ManyToOne
    private Proveedor proveedor;
    @ManyToOne
    private Obra obra;
    @ManyToOne
    private Deposito deposito;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creacion;
    @Enumerated(EnumType.STRING)
    private EstadoOC estado;
    @OneToMany(mappedBy = "ordenCompra", cascade = CascadeType.ALL,fetch= FetchType.EAGER)
    private List<DetOrdenCompra> detalles;
    @ManyToOne
    private Moneda moneda;
    @ManyToOne
    private Cotizacion cotizacion;
    @Transient
    private double porcentajeFacturado;
    @Transient
    private double porcentajeRecibido;

    public OrdenCompra() {
        this.estado = EstadoOC.PENDIENTE_AUTORIZACION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public EstadoOC getEstado() {
        return estado;
    }

    public void setEstado(EstadoOC estado) {
        this.estado = estado;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<DetOrdenCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetOrdenCompra> detalles) {
        this.detalles = detalles;
    }

    public double getPorcentajeFacturado() {
        porcentajeFacturado = 0d;
        double peso = 1d/getDetalles().size()*100;
        for(DetOrdenCompra doc : getDetalles()){
            porcentajeFacturado += ((doc.getCantidadSolicitada() - doc.getCantidadAFacturar() )* peso / doc.getCantidadSolicitada());
        }
        return porcentajeFacturado;
    }

    public double getPorcentajeRecibido() {
        porcentajeRecibido=0d;
        double peso = 1/getDetalles().size()*100;
        for(DetOrdenCompra doc : getDetalles()){
            porcentajeRecibido += doc.getCantidadARecibir()*peso/doc.getCantidadSolicitada();
        }
        return porcentajeRecibido;
    }

    public void setPorcentajeRecibido(double porcentajeRecibido) {
        this.porcentajeRecibido = porcentajeRecibido;
    }
    
    public void setPorcentajeFacturado(double porcentajeFacturado) {
        this.porcentajeFacturado = porcentajeFacturado;
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
        if (!(object instanceof OrdenCompra)) {
            return false;
        }
        OrdenCompra other = (OrdenCompra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.compra.OrdenCompra[ id=" + id + " ]";
    }
}
