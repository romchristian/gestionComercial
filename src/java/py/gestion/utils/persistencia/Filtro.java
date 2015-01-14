/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Entity
public abstract class Filtro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Transient
    private String openDialog;
    @ManyToOne
    private Consulta consulta;
    @OneToMany(mappedBy = "filtro",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<FiltroDetalle> detalles;

    public Filtro() {
    }

    public Filtro(String nombre) {
        this.nombre = nombre;
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

    public String getOpenDialog() {
        openDialog = "dialog" + nombre + ".show();";
        return openDialog;
    }

    public void setOpenDialog(String openDialog) {
        this.openDialog = openDialog;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
        if (!consulta.getFiltros().contains(this)) {
            consulta.getFiltros().add(this);
        } else if (consulta.getFiltros().contains(this)) {
            consulta.getFiltros().remove(this);
            consulta.getFiltros().add(this);
        }
    }

    public abstract String getFiltro(StringBuilder sb, QueryParameter queryParameter);

    public abstract Filtro getNuevo();

    public List<FiltroDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FiltroDetalle> detalles) {
        this.detalles = detalles;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Filtro other = (Filtro) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
}
