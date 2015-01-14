/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.cobranza.persistencia;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import py.gestion.prestamos.persistencia.DetPrestamo;

/**
 *
 * @author christian
 */
@Entity
public class DetCobroCuota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double monto;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    @ManyToOne
    private Pago pago;
    @ManyToOne
    private DetPrestamo detPrestamo;
    
    
    @ManyToOne
    private CobroCuota cobroCuota;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CobroCuota getCobroCuota() {
        return cobroCuota;
    }

    public void setCobroCuota(CobroCuota cobroCuota) {
        this.cobroCuota = cobroCuota;
    }

    public DetPrestamo getDetPrestamo() {
        return detPrestamo;
    }

    public void setDetPrestamo(DetPrestamo detPrestamo) {
        this.detPrestamo = detPrestamo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Pago getPago() {
        return pago;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
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
        if (!(object instanceof DetCobroCuota)) {
            return false;
        }
        DetCobroCuota other = (DetCobroCuota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.cobranza.DetCobroCuota[ id=" + id + " ]";
    }
}
