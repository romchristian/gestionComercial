/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import py.gestion.adm.persistencia.Obra;
import py.gestion.compra.persistencia.enums.EstadoRemisionProveedor;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.stock.persistencia.Deposito;
import py.gestion.utils.persistencia.Columna;

/**
 *
 * @author Elias
 */
@Entity
@NamedQueries({
    @NamedQuery(name = RemisionProveedor.TODOS, query = "select r from RemisionProveedor r")})
public class RemisionProveedor implements Serializable {

    public static final String TODOS = "py.gestionpymes.jpa.compra.RemisionProveedor.TODOS";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Columna(cabecera = "Estado", propiedad = "estado", visible = true)
    @Enumerated(EnumType.STRING)
    private EstadoRemisionProveedor estado;
    @ManyToOne
    @Columna(cabecera = "Obra", propiedad = "obra", visible = true)
    private Obra obra;
    @Columna(cabecera = "Provedor", propiedad = "proveedor", visible = true)
    @ManyToOne
    private Proveedor proveedor;
    @Columna(cabecera = "Fecha Doc", propiedad = "fechaDocumento", visible = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDocumento;
    @Columna(cabecera = "Fecha Recepción", propiedad = "fechaRecepcion", visible = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaRecepcion;
    @Columna(cabecera = "Número", propiedad = "numero", visible = true)
    private String numero;
    @Columna(cabecera = "Recibido por", propiedad = "recibidoPor", visible = true)
    private String recibidoPor;
    @Columna(cabecera = "Observación", propiedad = "observacion", visible = false)
    private String observacion;
    @OneToMany(mappedBy = "remisionProveedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetRemisionProveedor> detalles;
    @ManyToOne
    @Columna(cabecera = "Depósito", propiedad = "deposito", visible = true)
    private Deposito deposito;
    

    public RemisionProveedor() {
        this.estado = EstadoRemisionProveedor.CREADA;
    }
    
    public List<DetRemisionProveedor> getDetalles() {
        return detalles;
    }
    

    public void setDetalles(List<DetRemisionProveedor> detalles) {
        this.detalles = detalles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(Date fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public EstadoRemisionProveedor getEstado() {
        return estado;
    }

    public void setEstado(EstadoRemisionProveedor estado) {
        this.estado = estado;
    }

    public String getRecibidoPor() {
        return recibidoPor;
    }

    public void setRecibidoPor(String recibidoPor) {
        this.recibidoPor = recibidoPor;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
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
        if (!(object instanceof RemisionProveedor)) {
            return false;
        }
        RemisionProveedor other = (RemisionProveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.compra.persistencia.RemisionProveedor[ id=" + id + " ]";
    }
}
