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
import javax.persistence.Temporal;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.Estado;
import py.gestion.compra.persistencia.enums.Exportador;
import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.utils.persistencia.Columna;

/**
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = FormularioHechauka.TODOS, query = "select fh from FormularioHechauka fh")
})
public abstract class FormularioHechauka implements Serializable {

    public static final String TODOS = "py.syscvsa.compra.persistencia.FormularioHechauka.TODOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer tipoReg;
    private String periodoNombre;
    private Integer tipoDDJJ;
    private Integer tipoInf;
    private Integer formulario;
    private String agenteInf;
    private Integer dvAgenteInf;
    private String nombreAgenteInf;
    private String rucRep;
    private Integer dvRep;
    private String nombreRep;
    private Integer cantReg;
    private BigDecimal montoTotal;
    @Enumerated(EnumType.STRING)
    private Exportador exportador;
    @ManyToOne
    @Columna(cabecera = "Periodo", propiedad = "periodo", visible = true)
    private Periodo periodo;
    @ManyToOne
    @Columna(cabecera = "Empresa", propiedad = "empresa", visible = true)
    private Empresa empresa;
    @ManyToOne
    @Columna(cabecera = "Usuario", propiedad = "usuario", visible = true)
    private Usuario usuarioResponsable;
    @Temporal(javax.persistence.TemporalType.DATE)
    @Columna(cabecera = "Creación", propiedad = "creacion", visible = true)
    private Date creacion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @OneToMany(mappedBy = "cabecera",cascade = CascadeType.ALL)
    private List<DetFormularioHechauka> detalles;
    private Double cambioCotizacionSet;

    public FormularioHechauka() {
        this.creacion = new Date();
        this.estado = Estado.ACTIVO;
    }

    public FormularioHechauka(Integer tipoReg,
            Periodo periodo,
            Integer tipoDDJJ,
            Integer tipoInf,
            Integer formulario,
            Empresa empresa,
            Integer cantReg,
            BigDecimal montoTotal,
            Exportador exportador) {
        this();
        this.tipoReg = tipoReg;
        this.periodo = periodo;
        this.periodoNombre = periodo.getNombre();
        this.tipoDDJJ = tipoDDJJ;
        this.tipoInf = tipoInf;
        this.formulario = formulario;
        this.empresa = empresa;
        this.agenteInf = empresa.getRuc();
        this.dvAgenteInf = empresa.getDv();
        this.nombreAgenteInf = empresa.getRazonSocial();
        this.rucRep = empresa.getRucRepLegal();
        this.dvRep = empresa.getDvRepLegal();
        this.nombreRep = empresa.getNombreRepLegal();
        this.cantReg = cantReg;
        this.montoTotal = montoTotal;
        this.exportador = exportador;
    }

    public Double getCambioCotizacionSet() {
        return cambioCotizacionSet;
    }

    public void setCambioCotizacionSet(Double cambioCotizacionSet) {
        this.cambioCotizacionSet = cambioCotizacionSet;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipoReg() {
        return tipoReg;
    }

    public void setTipoReg(Integer tipoReg) {
        this.tipoReg = tipoReg;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public String getPeriodoNombre() {
        return periodoNombre;
    }

    public void setPeriodoNombre(String periodoNombre) {
        this.periodoNombre = periodoNombre;
    }

    public Integer getTipoDDJJ() {
        return tipoDDJJ;
    }

    public void setTipoDDJJ(Integer tipoDDJJ) {
        this.tipoDDJJ = tipoDDJJ;
    }

    public Integer getTipoInf() {
        return tipoInf;
    }

    public void setTipoInf(Integer tipoInf) {
        this.tipoInf = tipoInf;
    }

    public Integer getFormulario() {
        return formulario;
    }

    public void setFormulario(Integer formulario) {
        this.formulario = formulario;
    }

    public String getAgenteInf() {
        return agenteInf;
    }

    public void setAgenteInf(String agenteInf) {
        this.agenteInf = agenteInf;
    }

    public Integer getDvAgenteInf() {
        return dvAgenteInf;
    }

    public void setDvAgenteInf(Integer dvAgenteInf) {
        this.dvAgenteInf = dvAgenteInf;
    }

    public String getNombreAgenteInf() {
        return nombreAgenteInf;
    }

    public void setNombreAgenteInf(String nombreAgenteInf) {
        this.nombreAgenteInf = nombreAgenteInf;
    }

    public String getRucRep() {
        return rucRep;
    }

    public void setRucRep(String rucRep) {
        this.rucRep = rucRep;
    }

    public Integer getDvRep() {
        return dvRep;
    }

    public void setDvRep(Integer dvRep) {
        this.dvRep = dvRep;
    }

    public String getNombreRep() {
        return nombreRep;
    }

    public void setNombreRep(String nombreRep) {
        this.nombreRep = nombreRep;
    }

    public Integer getCantReg() {
        return cantReg;
    }

    public void setCantReg(Integer cantReg) {
        this.cantReg = cantReg;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    
    public Exportador getExportador() {
        return exportador;
    }

    public void setExportador(Exportador exportador) {
        this.exportador = exportador;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(Usuario usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public Date getCreacion() {
        return creacion;
    }

    public void setCreacion(Date creacion) {
        this.creacion = creacion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<DetFormularioHechauka> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetFormularioHechauka> detalles) {
        this.detalles = detalles;
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
        if (!(object instanceof FormularioHechauka)) {
            return false;
        }
        FormularioHechauka other = (FormularioHechauka) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.compra.persistencia.DetLibroCompra[ id=" + id + " ]";
    }
}
