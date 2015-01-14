/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author cromero
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ColumnModel.TODOS, query = "select cm from ColumnModel cm"),
    @NamedQuery(name = ColumnModel.POR_CLASE, query = "select cm from ColumnModel cm where cm.clase= :clase order by cm.indice"),
    @NamedQuery(name = ColumnModel.POR_CLASE_VISIBLE, query = "select cm from ColumnModel cm where cm.clase= :clase and cm.visible = :visible order by cm.indice")})
public class ColumnModel implements Serializable {

    public static final String TODOS = "py.syscvsa.utils.web.ColumnModel.TODOS";
    public static final String POR_CLASE = "py.syscvsa.utils.web.ColumnModel.POR_CLASE";
    public static final String POR_CLASE_VISIBLE = "py.syscvsa.utils.web.ColumnModel.POR_CLASE_VISIBLE";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String clase;
    private String header;
    private String property;
    private boolean visible = true;
    private String tipo;
    private String usuario;
    private String bean;
    private boolean fecha;
    private boolean selectOne;
    private boolean totalizable;
    private int indice;
    private String estilo;
    @Transient
    private BeanGenerico beanObject;

    public ColumnModel() {
    }

    public ColumnModel(String header, String property, boolean visible, String clase, String tipo, String usuario, int indice) {
        this.header = header;
        this.property = property;
        this.visible = visible;
        this.clase = clase;
        this.tipo = tipo;
        this.usuario = usuario;
        this.indice = indice;
        if (tipo.equals("BigDecimal")
                || tipo.equals("BigInteger")
                || tipo.equals("Double")
                || tipo.equals("Long")
                || tipo.equals("Integer")
                || tipo.equals("double")
                || tipo.equals("long")
                || tipo.equals("int")
                || tipo.equals("float")
                || tipo.equals("Number")) {
            this.estilo = "font-size: 9pt;text-align:right;";
        } else if (tipo.equals("Date")) {
            this.estilo = "font-size: 9pt;text-align:center;";
        } else {
            this.estilo = "font-size: 9pt;text-align:left;";
        }
    }

    public ColumnModel(String header, String property, boolean visible, String clase, String tipo, String usuario, int indice, String bean, boolean fecha, boolean selectOne, boolean totalizable) {
        this.header = header;
        this.property = property;
        this.tipo = tipo;
        this.usuario = usuario;
        this.bean = bean;
        this.fecha = fecha;
        this.selectOne = selectOne;
        this.indice = indice;
        this.totalizable = totalizable;
        if (tipo.equals("BigDecimal")
                || tipo.equals("BigInteger")
                || tipo.equals("Double")
                || tipo.equals("Long")
                || tipo.equals("Integer")
                || tipo.equals("double")
                || tipo.equals("long")
                || tipo.equals("int")
                || tipo.equals("float")
                || tipo.equals("Number")) {
            this.estilo = "font-size: 9pt;text-align:right;";
        } else if (tipo.equals("Date")) {
            this.estilo = "font-size: 9pt;text-align:center;";
        } else {
            this.estilo = "font-size: 9pt;text-align:left;";
        }
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public boolean isTotalizable() {
        return totalizable;
    }

    public void setTotalizable(boolean totalizable) {
        this.totalizable = totalizable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public String getProperty() {
        return property;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public boolean isFecha() {
        return fecha;
    }

    public void setFecha(boolean fecha) {
        this.fecha = fecha;
    }

    public boolean isSelectOne() {
        return selectOne;
    }

    public void setSelectOne(boolean selectOne) {
        this.selectOne = selectOne;
    }

    public BeanGenerico getBeanObject() {
        if (beanObject == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            beanObject = (BeanGenerico) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, bean);
        }
        return beanObject;
    }

    public void setBeanObject(BeanGenerico beanObject) {
        this.beanObject = beanObject;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final ColumnModel other = (ColumnModel) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return header;
    }
}
