/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.adm.persistencia.Moneda;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = CuentaBancaria.TODOS, query = "select c from CuentaBancaria c")})
public class CuentaBancaria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public static final String TODOS = "package py.gestionpymes.jpa.bancos.CuentaBancaria.TODOS";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private  Long version;
    @ManyToOne
    private Banco banco;
    private String descripcion;
    private double saldoMinimo;
    @ManyToOne
    private Moneda moneda;

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
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
        if (!(object instanceof CuentaBancaria)) {
            return false;
        }
        CuentaBancaria other = (CuentaBancaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.bancos.CuentaBancaria[ id=" + id + " ]";
    }
    
}
