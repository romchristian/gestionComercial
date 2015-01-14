/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia;

import java.io.Serializable;
import javax.persistence.*;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Moneda;
import py.gestion.bancos.persistencia.Chequera;
import py.gestion.bancos.persistencia.CuentaBancaria;
import py.gestion.bancos.persistencia.TransaccionBancaria;

/**
 *
 * @author christian
 */
@Entity
public class ChequeEm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private CuentaBancaria cuentaBancaria;
    @ManyToOne
    private Chequera chequera;
    @ManyToOne
    private Moneda moneda;
    @ManyToOne
    private Cotizacion cotizacion;
    private double monto;
    private double montoLocal;
    private String concepto;
    private String numero;
    private EstadoCheque estadoCheque;
    @OneToOne(mappedBy = "chequeEm")
    private TransaccionBancaria transaccionBancaria;

    public EstadoCheque getEstadoCheque() {
        return estadoCheque;
    }

    public void setEstadoCheque(EstadoCheque estadoCheque) {
        this.estadoCheque = estadoCheque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Chequera getChequera() {
        return chequera;
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getMontoLocal() {
        return montoLocal;
    }

    public void setMontoLocal(double montoLocal) {
        this.montoLocal = montoLocal;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TransaccionBancaria getTransaccionBancaria() {
        return transaccionBancaria;
    }

    public void setTransaccionBancaria(TransaccionBancaria transaccionBancaria) {
        this.transaccionBancaria = transaccionBancaria;
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
        if (!(object instanceof ChequeEm)) {
            return false;
        }
        ChequeEm other = (ChequeEm) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.pagos.ChequeEm[ id=" + id + " ]";
    }
}
