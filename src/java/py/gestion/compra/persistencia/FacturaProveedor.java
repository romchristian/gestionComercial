/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.persistencia.Obra;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.persistencia.Columna;

/**
 *
 * @author christian
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"timbrado","codigosucursal","codigoestablecimiento","numero"})})
@NamedQueries({
    @NamedQuery(name = FacturaProveedor.TODOS, query = "select f from FacturaProveedor f"),
    @NamedQuery(name = FacturaProveedor.POR_ESTADO, query = "select f from FacturaProveedor f where f.estado = :estado"),
      @NamedQuery(name = FacturaProveedor.POR_ESTADO_PROVEEDOR, query = "select f from FacturaProveedor f where f.estado = :estado and f.proveedor = :proveedor"),
    @NamedQuery(name = FacturaProveedor.POR_ESTADO_EMISION, query = "select f from FacturaProveedor f where f.estado = :estado and f.emision BETWEEN :inicio and :fin"),
    @NamedQuery(name = FacturaProveedor.POR_NUMERO, query = "select f from FacturaProveedor f where f.timbrado = :timbrado and f.codigoEstablecimiento = :codest and f.codigoSucursal = :codsuc and f.numero = :numero")})
public class FacturaProveedor implements Serializable {

    public static final String TODOS = "py.gestionpymes.jpa.compra.FacturaProveedor.TODOS";
    public static final String POR_ESTADO = "py.gestionpymes.jpa.compra.FacturaProveedor.POR_ESTADO";
    public static final String POR_ESTADO_PROVEEDOR = "py.gestionpymes.jpa.compra.FacturaProveedor.POR_ESTADO_PROVEEDOR";
    public static final String POR_ESTADO_EMISION = "py.gestionpymes.jpa.compra.FacturaProveedor.POR_ESTADO_EMISION";
    public static final String POR_NUMERO = "py.gestionpymes.jpa.compra.FacturaProveedor.POR_NUMERO";
    @OneToMany(mappedBy = "facturaProveedor", cascade = CascadeType.ALL)
    private List<DetFacturaProveedor> detalles;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Columna(cabecera = "ID", propiedad = "id", visible = false)
    private Long id;
    @Columna(cabecera = "Timbrado", propiedad = "timbrado", visible = false)
    private String timbrado;
    @Columna(cabecera = "Cod Suc.", propiedad = "codigoSucursal", visible = false)
    
    private String codigoSucursal;
    @Columna(cabecera = "Cod Est.", propiedad = "codigoEstablecimiento", visible = false)
    private String codigoEstablecimiento;
    @Columna(cabecera = "Numero", propiedad = "numero", visible = false)
    @Length(min = 1, max = 7)
    @Range(min = 1, max = 9999999,message="El numero esta fuera rango")
    private String numero;
    @Transient
    @Columna(cabecera = "Nro", propiedad = "nro")
    private String nro;
    @Enumerated(EnumType.STRING)
    @Columna(cabecera = "Estado", propiedad = "estado")
    private EstadoFacturaProveedor estado;
    @ManyToOne
    @Columna(cabecera = "Proveedor", propiedad = "proveedor")
    private Proveedor proveedor;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Columna(cabecera = "Creación", propiedad = "creacion")
    private Date creacion = new Date();
    @Temporal(javax.persistence.TemporalType.DATE)
    @Columna(cabecera = "Emisión", propiedad = "emision")
    private Date emision;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Columna(cabecera = "Cancelación", propiedad = "cancelacion")
    private Date cancelacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Columna(cabecera = "Anulación", propiedad = "anulacion")
    private Date anulacion;
    @ManyToOne
    @Columna(cabecera = "Moneda", propiedad = "moneda")
    private Moneda moneda;
    @ManyToOne
    @Columna(cabecera = "Cotización", propiedad = "cotizacion")
    private Cotizacion cotizacion;
    @Columna(cabecera = "IVA 05", propiedad = "totaliva05",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totaliva05;
    @Columna(cabecera = "IVA 10", propiedad = "totaliva10",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totaliva10;
    @Columna(cabecera = "Total IVA", propiedad = "totaliva",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totaliva;
    @Columna(cabecera = "Exenta", propiedad = "totalexenta",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totalexenta;
    @Columna(cabecera = "Gravada 05", propiedad = "totalgravada05",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totalgravada05;
    @Columna(cabecera = "Gravada 10", propiedad = "totalgravada10",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totalgravada10;
    @Columna(cabecera = "Total Gravada", propiedad = "totalgravada",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totalgravada;
    @Columna(cabecera = "Descuento", propiedad = "totaldescuento",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal totaldescuento;
    @Columna(cabecera = "Total", propiedad = "total",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal total;
    @Columna(cabecera = "Saldo", propiedad = "saldo",totalizable = true)
    @Column(precision = 38, scale = 4)
    private BigDecimal saldo;
    @Enumerated(EnumType.STRING)
    @Columna(cabecera = "tipo", propiedad = "tipo")
    private TipoFacturaProveedor tipo;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Columna(cabecera = "Vencimiento", propiedad = "vencimiento")
    private Date vencimiento;
    @ManyToOne
    @Columna(cabecera = "Obra", propiedad = "obra")
    private Obra obra;
    private Double cotizacionCierre;

    public String getNro() {
        nro = "(" + timbrado + ") " + codigoEstablecimiento + "-" + codigoSucursal + "-" + numero;
        return nro;
    }

    public void setNro(String nro) {
        this.nro = nro;
    }

    public FacturaProveedor() {
        this.estado = EstadoFacturaProveedor.CONFORMADA;
        this.creacion = new Date();
    }

    public Double getCotizacionCierre() {
        return cotizacionCierre;
    }

    public void setCotizacionCierre(Double cambioCotizacionSet) {
        this.cotizacionCierre = cambioCotizacionSet;
    }

 

    public List<DetFacturaProveedor> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetFacturaProveedor> detalles) {
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getCodigoSucursal() {
        return codigoSucursal;
    }

    public void setCodigoSucursal(String codigoSucursal) {
        this.codigoSucursal = codigoSucursal;
    }

    public String getCodigoEstablecimiento() {
        return codigoEstablecimiento;
    }

    public void setCodigoEstablecimiento(String codigoEstablecimiento) {
        this.codigoEstablecimiento = codigoEstablecimiento;
    }

    public EstadoFacturaProveedor getEstado() {
        return estado;
    }

    public void setEstado(EstadoFacturaProveedor estado) {
        this.estado = estado;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Date getEmision() {
        return emision;
    }

    public void setEmision(Date emision) {
        this.emision = emision;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
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

    public BigDecimal getTotaliva05() {
        return totaliva05;
    }

    public void setTotaliva05(BigDecimal totaliva05) {
        this.totaliva05 = totaliva05;
    }

    public BigDecimal getTotaliva10() {
        return totaliva10;
    }

    public void setTotaliva10(BigDecimal totaliva10) {
        this.totaliva10 = totaliva10;
    }

    public BigDecimal getTotaliva() {
        return totaliva;
    }

    public void setTotaliva(BigDecimal totaliva) {
        this.totaliva = totaliva;
    }

    public BigDecimal getTotalexenta() {
        return totalexenta;
    }

    public void setTotalexenta(BigDecimal totalexenta) {
        this.totalexenta = totalexenta;
    }

    public BigDecimal getTotalgravada05() {
        return totalgravada05;
    }

    public void setTotalgravada05(BigDecimal totalgravada05) {
        this.totalgravada05 = totalgravada05;
    }

    public BigDecimal getTotalgravada10() {
        return totalgravada10;
    }

    public void setTotalgravada10(BigDecimal totalgravada10) {
        this.totalgravada10 = totalgravada10;
    }

    public BigDecimal getTotalgravada() {
        return totalgravada;
    }

    public void setTotalgravada(BigDecimal totalgravada) {
        this.totalgravada = totalgravada;
    }

    public BigDecimal getTotaldescuento() {
        return totaldescuento;
    }

    public void setTotaldescuento(BigDecimal totaldescuento) {
        this.totaldescuento = totaldescuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public Date getAnulacion() {
        return anulacion;
    }

    public void setAnulacion(Date anulacion) {
        this.anulacion = anulacion;
    }

    public Date getCancelacion() {
        return cancelacion;
    }

    public void setCancelacion(Date cancelacion) {
        this.cancelacion = cancelacion;
    }

    public TipoFacturaProveedor getTipo() {
        return tipo;
    }

    public void setTipo(TipoFacturaProveedor tipo) {
        this.tipo = tipo;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
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
        if (!(object instanceof FacturaProveedor)) {
            return false;
        }
        FacturaProveedor other = (FacturaProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.FacturaProveedor[id=" + id + "]";
    }
}
