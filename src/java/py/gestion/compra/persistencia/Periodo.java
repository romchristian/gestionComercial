/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author cromero
 */
@Entity
public class Periodo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Min(value = 2000)
    @Max(value = 2100)
    private int anio;
    @Min(value = 1)
    @Max(value = 12)
    private int mes;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date inicio;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fin;

    public Periodo() {
    }

    public Periodo(int anio, int mes) {
        this.anio = anio;
        this.mes = mes;
        this.nombre = anio + (mes < 10 ? "0" + mes : mes + "");
        Calendar cal = Calendar.getInstance();
        cal.set(anio, (mes-1), cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        inicio = cal.getTime();
        cal.set(anio, (mes-1), cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        fin = cal.getTime();
    }

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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    

    

    public Date getInicio() {

        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
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
        if (!(object instanceof Periodo)) {
            return false;
        }
        Periodo other = (Periodo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.compra.persistencia.Periodo[ id=" + id + " ]";
    }
}
