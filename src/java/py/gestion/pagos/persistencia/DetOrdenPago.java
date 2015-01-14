/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.utils.persistencia.DetColumna;

/**
 *
 * @author Elias
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DetOrdenPago.TODOS, query = "select dop from DetOrdenPago dop")
})
public class DetOrdenPago implements Serializable {

    public final static String TODOS = "py.syscvsa.pagos.persistencia.detordenpago.todos";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int indice;
    private String descripcion;
    @OneToOne
    private FacturaProveedor facturaProveedor;
    private BigDecimal monto;
    @ManyToOne
    private OrdenPago ordenPago;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public OrdenPago getOrdenPago() {
        return ordenPago;
    }

    public void setOrdenPago(OrdenPago ordenPago) {
        this.ordenPago = ordenPago;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DetOrdenPago other = (DetOrdenPago) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
