/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Estado;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Familia.TODOS, query = "select f from Familia f")})
public class Familia implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String TODOS = "package py.gestionpymes.jpa.stock.Familia.TODOS";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    private String nombre;
    private String cod;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @Lob
    private byte[] imagen;

    @ManyToMany(mappedBy = "familias")
    private List<Producto> productos;

    public Familia() {
        this.estado = Estado.ACTIVO;
    }

    public Familia(String nombre, String cod) {
        this();
        this.nombre = nombre;
        this.cod = cod;
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

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
        if (!(object instanceof Familia)) {
            return false;
        }
        Familia other = (Familia) object;
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
