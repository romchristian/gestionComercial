/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author christian
 */
@Entity
public class Chequera implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
     
    @ManyToOne
    private CuentaBancaria cuentaBancaria;
    private Long inicio; 
    private Long fin;
    private Long numero;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vencimiento;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getFin() {
        return fin;
    }

    public void setFin(Long fin) {
        this.fin = fin;
    }

    public Long getInicio() {
        return inicio;
    }

    public void setInicio(Long inicio) {
        this.inicio = inicio;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Date getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(Date vencimiento) {
        this.vencimiento = vencimiento;
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
        if (!(object instanceof Chequera)) {
            return false;
        }
        Chequera other = (Chequera) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.bancos.Chequera[ id=" + id + " ]";
    }
    
}
