/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.prestamos.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import py.gestion.clientes.persistencia.Cliente;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Prestamo.TODOS, query = "select p from Prestamo p"),
 @NamedQuery(name = Prestamo.POR_CLIENTE, query = "select p from Prestamo p where p.cliente = :cliente")})
public class Prestamo implements Serializable {

    public static final String TODOS = "py.gestionpymes.jpa.prestamos.Prestamo.TODOS";
    public static final String POR_CLIENTE = "py.gestionpymes.jpa.prestamos.Prestamo.POR_CLIENTE";
    @OneToMany(mappedBy = "prestamo", cascade = CascadeType.ALL)
    private List<DetPrestamo> detalles;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    @ManyToOne
    private Cliente cliente;
    private double capital = 1000000d;
    private int plazo = 12;
    private int tasa = 24;
    @Enumerated(EnumType.STRING)
    private PeriodoPago periodoPago = PeriodoPago.MENSUAL;
    private double gastos;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicioOperacion;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha = new Date();
    private double montoCuota;
    private double totalIntereses;
    private double totalOperacion;
    private SistemaAmortizacion sistemaAmortizacion = SistemaAmortizacion.FRANCES;
    @Transient
    private Sistema sistema;
    private EstadoPrestamo estado;

    public Prestamo() {
        this.estado = EstadoPrestamo.PENDIENTE_DESEMBOLSO;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    public Sistema getSistema() {
        if (sistema == null) {
            switch (sistemaAmortizacion) {
                case INTERES_SIMPLE:
                    sistema = new InteresSimple(this);
                    break;
                case FRANCES:
                    sistema = new InteresFrances(this);
                    break;
            }
        }
        return sistema;
    }

    public EstadoPrestamo getEstado() {
        return estado;
    }

    public void setEstado(EstadoPrestamo estado) {
        this.estado = estado;
    }

    public double getCapital() {
        return capital + gastos;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetPrestamo> getDetalles() {
        if (detalles == null) {
            detalles = getSistema().calculaCuotas();
        }
        return detalles;
    }

    public void setDetalles(List<DetPrestamo> detalles) {
        this.detalles = detalles;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaInicioOperacion() {
        fechaInicioOperacion = new Date();
        return fechaInicioOperacion;
    }

    public void setFechaInicioOperacion(Date fechaInicioOperacion) {
        this.fechaInicioOperacion = fechaInicioOperacion;
    }

    public double getGastos() {
        return gastos;
    }

    public void setGastos(double gastos) {
        this.gastos = gastos;
    }

    public double getMontoCuota() {
        
        montoCuota = getSistema().getCuota();
        return montoCuota;
    }

    public void setMontoCuota(double montoCuota) {
        this.montoCuota = montoCuota;
    }

    public PeriodoPago getPeriodoPago() {
        return periodoPago;
    }

    public void setPeriodoPago(PeriodoPago periodoPago) {
        this.periodoPago = periodoPago;
    }

    public int getPlazo() {
        return plazo;
    }

    public void setPlazo(int plazo) {
        this.plazo = plazo;
    }

    public SistemaAmortizacion getSistemaAmortizacion() {
        return sistemaAmortizacion;
    }

    public void setSistemaAmortizacion(SistemaAmortizacion sistemaAmortizacion) {
        this.sistemaAmortizacion = sistemaAmortizacion;
    }

    public int getTasa() {
        return tasa;
    }

    public void setTasa(int tasa) {
        this.tasa = tasa;
    }

    public double getTotalIntereses() {
        totalIntereses = 0;
        for (DetPrestamo d : getDetalles()) {
            totalIntereses += d.getCuotaInteres();
        }
        return totalIntereses;
    }

    public void setTotalIntereses(double totalIntereses) {
        this.totalIntereses = totalIntereses;
    }

    public double getTotalOperacion() {
        totalOperacion = getCapital() + getGastos() + getTotalIntereses();
        return totalOperacion;
    }

    public void setTotalOperacion(double totalOperacion) {
        this.totalOperacion = totalOperacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.prestamos.Prestamo[ id=" + id + " ]";
    }
}
