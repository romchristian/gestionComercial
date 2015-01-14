/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Direccion;
import py.gestion.adm.persistencia.Estado;


/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Proveedor.TODOS, query = "select p from Proveedor p"),
    @NamedQuery(name = Proveedor.POR_NOMBRE, query = "select p from Proveedor p where p.nombre = :nombre")})
public class Proveedor implements Serializable {

    public static final String TODOS = "py.syscvsa.jpa.compra.Proveedor.TODOS";
    public static final String POR_NOMBRE = "py.syscvsa.jpa.compra.Proveedor.POR_NOMBRE";
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String documento;
    private Integer codVerificador;
    private String nombre;
    private String cod;
    @ManyToOne
    private Direccion direccion;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
    private List<ProveedorTimbrado> timbrados;

    public Proveedor() {
        this.estado = Estado.ACTIVO;
    }

    public Proveedor(String nombre, String documento, String cod, Integer dv) {
        this();
        this.documento = documento;
        this.nombre = nombre;
        this.cod = cod;
        this.codVerificador = dv;
    }

 

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<ProveedorTimbrado> getTimbrados() {
        return timbrados;
    }

    public void setTimbrados(List<ProveedorTimbrado> timbrados) {
        this.timbrados = timbrados;
    }

    public Integer getCodVerificador() {
        return codVerificador;
    }

    public void setCodVerificador(Integer codVerificador) {
        this.codVerificador = codVerificador;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Direccion getDireccion() {

        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
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
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
