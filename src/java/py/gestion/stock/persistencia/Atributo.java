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
public class Atributo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Version 
    private Long version;
    @Enumerated(EnumType.STRING)
    private TipoAtributoProducto tipo;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    @ManyToMany(mappedBy = "atributos")
    private List<Producto> productos;
    

    public Atributo() {
        this.estado = Estado.ACTIVO;
    }

    public Atributo(String nombre, TipoAtributoProducto tipo) {
        this();
        this.nombre = nombre;
        this.tipo = tipo;
        
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoAtributoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtributoProducto tipo) {
        this.tipo = tipo;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
        if (!(object instanceof Atributo)) {
            return false;
        }
        Atributo other = (Atributo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.gestionpymes.jpa.stock.Atributo[ id=" + id + " ]";
    }
    
}
