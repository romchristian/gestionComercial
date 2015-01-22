/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author Acer
 */
@Entity
public class Secuencia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    private String timbrado;
    private String codEstablecimiento;
    private String codSucursal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vencimiento;
    private Long valorInicial;
    private Long valorFinal;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Enumerated(EnumType.STRING)
    private TipoSecuencia tipoSecuencia;
    private boolean infinito;

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

    public Long getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Long valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Long getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Long valorFinal) {
        this.valorFinal = valorFinal;
    }

    public String getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(String timbrado) {
        this.timbrado = timbrado;
    }

    public String getCodEstablecimiento() {
        return codEstablecimiento;
    }

    public void setCodEstablecimiento(String codEstablecimiento) {
        this.codEstablecimiento = codEstablecimiento;
    }

    public String getCodSucursal() {
        return codSucursal;
    }

    public void setCodSucursal(String codSucursal) {
        this.codSucursal = codSucursal;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoSecuencia getTipoSecuencia() {
        return tipoSecuencia;
    }

    public void setTipoSecuencia(TipoSecuencia tipoSecuencia) {
        this.tipoSecuencia = tipoSecuencia;
    }

    public boolean isInfinito() {
        return infinito;
    }

    public void setInfinito(boolean infinito) {
        this.infinito = infinito;
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
        if (!(object instanceof Secuencia)) {
            return false;
        }
        Secuencia other = (Secuencia) object;
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
