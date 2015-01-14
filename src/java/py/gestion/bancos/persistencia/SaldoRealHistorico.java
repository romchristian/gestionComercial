/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = SaldoRealHistorico.TODOS, query = "select s from SaldoRealHistorico s"),
    @NamedQuery(name = SaldoRealHistorico.ACTUAL, query = "select s from SaldoRealHistorico s "
    + "where s.cuentaBancaria = :cuentaBancaria"
    + " and :fecha between s.desde and s.hasta")
})
public class SaldoRealHistorico implements Serializable {

    public static final String TODOS = "package py.gestionpymes.jpa.bancos.SaldoRealHistorico.TODOS";
    public static final String ACTUAL = "package py.gestionpymes.jpa.bancos.SaldoRealHistorico.ACTUAL";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    @ManyToOne
    private CuentaBancaria cuentaBancaria;
    private double saldo;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date desde;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date hasta;

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

    public Date getDesde() {
        return desde;
    }

    public void setDesde(Date desde) {
        this.desde = desde;
    }

    public Date getHasta() {
        return hasta;
    }

    public void setHasta(Date hasta) {
        this.hasta = hasta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
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
        if (!(object instanceof SaldoRealHistorico)) {
            return false;
        }
        SaldoRealHistorico other = (SaldoRealHistorico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.bancos.Saldo[ id=" + id + " ]";
    }
}
