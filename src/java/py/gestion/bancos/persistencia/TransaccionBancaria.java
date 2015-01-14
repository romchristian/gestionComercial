/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import py.gestion.pagos.persistencia.ChequeEm;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = TransaccionBancaria.TODOS, query = "select t from TransaccionBancaria t")})
public class TransaccionBancaria implements Serializable {
    
    public static final String TODOS = "package py.gestionpymes.jpa.bancos.TransaccionBancaria.TODOS";
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String descripcion;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    private double debito;
    private double credito;
    private boolean conciliado;
    @ManyToOne
    private CuentaBancaria cuentaBancaria;
    private TipoTransaccion tipoTransaccion;
    
    
    @OneToOne
    private ChequeEm chequeEm;

    public TransaccionBancaria() {
    }

    public TransaccionBancaria(String descripcion, CuentaBancaria cuentaBancaria, TipoTransaccion tipoTransaccion, String numero, double monto) {
        this.descripcion = descripcion;
        this.cuentaBancaria = cuentaBancaria;
        this.tipoTransaccion = tipoTransaccion;
        
        conciliado = false;
        fecha = new Date();
        if(tipoTransaccion == TipoTransaccion.CHEQUE || tipoTransaccion == TipoTransaccion.EXTRACCION){
            debito = monto;
            credito = 0d;
        } else if(tipoTransaccion == TipoTransaccion.DEPOSITO){
            debito = 0d;
            credito = monto;
        }
    }
    
    public TransaccionBancaria(ChequeEm chequeEm) {
        this(chequeEm.toString(), chequeEm.getCuentaBancaria(), TipoTransaccion.CHEQUE,chequeEm.getNumero(),chequeEm.getMonto());
        this.chequeEm = chequeEm;
    }

    public ChequeEm getChequeEm() {
        return chequeEm;
    }

    public void setChequeEm(ChequeEm chequeEm) {
        this.chequeEm = chequeEm;
    }

    

    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
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

    public boolean isConciliado() {
        return conciliado;
    }

    public void setConciliado(boolean conciliado) {
        this.conciliado = conciliado;
    }

    public double getCredito() {
        return credito;
    }

    public void setCredito(double credito) {
        this.credito = credito;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public double getDebito() {
        return debito;
    }

    public void setDebito(double debito) {
        this.debito = debito;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof TransaccionBancaria)) {
            return false;
        }
        TransaccionBancaria other = (TransaccionBancaria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.bancos.TransaccionBancaria[ id=" + id + " ]";
    }
    
}
