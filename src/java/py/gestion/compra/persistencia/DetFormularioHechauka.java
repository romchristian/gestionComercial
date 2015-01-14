/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.web.CodigoVerificador;

/**
 *
 * @author cromero
 */
@Entity
public abstract class DetFormularioHechauka implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer tipo;
    @ManyToOne
    private Proveedor proveedor;
    private String rucProveedor;
    private Integer dvProveedor;
    private String nombreProveedor; 
    private Long timbradoProveedor; 
    private Integer tipoDocumento;
    private String numeroDocumento;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDocumento;
    private BigDecimal montoTasa10;
    private BigDecimal iva10;
    private BigDecimal montoTasa05;
    private BigDecimal iva05;
    private BigDecimal montoExenta;
    private Integer tipoOperacion;
    @ManyToOne
    private FormularioHechauka cabecera;

    public DetFormularioHechauka() {
    }

    public DetFormularioHechauka(Integer tipo, Proveedor proveedor, String rucProveedor, Integer dvProveedor, String nombreProveedor, Long timbradoProveedor, Integer tipoDocumento, String numeroDocumento, Date fechaDocumento, BigDecimal montoTasa10, BigDecimal iva10, BigDecimal montoTasa05, BigDecimal iva05, BigDecimal montoExenta, Integer tipoOperacion, FormularioHechauka cabecera) {
        this.tipo = tipo;
        this.proveedor = proveedor;
        this.rucProveedor = rucProveedor;
        this.dvProveedor = dvProveedor == null?CodigoVerificador.calcular(rucProveedor):dvProveedor;
        this.nombreProveedor = nombreProveedor;
        this.timbradoProveedor = timbradoProveedor;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaDocumento = fechaDocumento;
        this.montoTasa10 = montoTasa10;
        this.iva10 = iva10;
        this.montoTasa05 = montoTasa05;
        this.iva05 = iva05;
        this.montoExenta = montoExenta;
        this.tipoOperacion = tipoOperacion;
        this.cabecera = cabecera;
        
        if(tipoDocumento == 4 || tipoDocumento == 7 || tipoDocumento == 8){
            this.rucProveedor = "99999901";
            this.dvProveedor = 0;
        }
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public Integer getDvProveedor() {
        return dvProveedor;
    }

    public void setDvProveedor(Integer dvProveedor) {
        this.dvProveedor = dvProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public Long getTimbradoProveedor() {
        return timbradoProveedor;
    }

    public void setTimbradoProveedor(Long timbradoProveedor) {
        this.timbradoProveedor = timbradoProveedor;
    }

    public Integer getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(Integer tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public BigDecimal getMontoTasa10() {
        return montoTasa10;
    }

    public void setMontoTasa10(BigDecimal montoTasa10) {
        this.montoTasa10 = montoTasa10;
    }

    public BigDecimal getIva10() {
        return iva10;
    }

    public void setIva10(BigDecimal iva10) {
        this.iva10 = iva10;
    }

    public BigDecimal getMontoTasa05() {
        return montoTasa05;
    }

    public void setMontoTasa05(BigDecimal montoTasa05) {
        this.montoTasa05 = montoTasa05;
    }

    public BigDecimal getIva05() {
        return iva05;
    }

    public void setIva05(BigDecimal iva05) {
        this.iva05 = iva05;
    }

    public BigDecimal getMontoExenta() {
        return montoExenta;
    }

    public void setMontoExenta(BigDecimal montoExenta) {
        this.montoExenta = montoExenta;
    }

    

    public Integer getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Integer tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public FormularioHechauka getCabecera() {
        return cabecera;
    }

    public void setCabecera(FormularioHechauka cabecera) {
        this.cabecera = cabecera;
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
        if (!(object instanceof DetFormularioHechauka)) {
            return false;
        }
        DetFormularioHechauka other = (DetFormularioHechauka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.compra.persistencia.CabeceraLibroCompra[ id=" + id + " ]";
    }
    
}
