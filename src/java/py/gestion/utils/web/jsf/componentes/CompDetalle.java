/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import py.gestion.compra.persistencia.DetRemisionProveedor;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.utils.persistencia.ColumnModel;
import py.gestion.utils.persistencia.DetColumna;

/**
 *
 * @author christian
 */
public abstract class CompDetalle<T> implements Serializable {

    //private AbstractDAO<T> ejbBusca;    
    @EJB
    private ProductoDAO ejbProducto;
    private Producto producto = new Producto();
    private T itemElegido;
    private List<T> detalles;
    private List<ColumnModel> columnasDisponibles = new ArrayList<ColumnModel>();

    public void iniciaConversacion() {
        limpia();
    }

    public void terminaConversacion() {
        limpia();
    }

    public T getItemElegido() {
        if (itemElegido == null) {
            itemElegido = getInstancia();
        }
        return itemElegido;
    }

    public void setItemElegido(T itemElegido) {
        this.itemElegido = itemElegido;
    }

    public List<T> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<T> detalles) {
        this.detalles = detalles;
    }

    //public abstract void busca();
    public String agregaDetalle() {
        if (detalles == null) {
            detalles = new ArrayList<T>();
        }
        detalles.add(getItemAAgregar());
        limpia();
        return null;
    }

    public void busca() {
        System.out.println("COD: " + producto.getCodigo());
        producto = ejbProducto.findPorCodigoEstricto(producto.getCodigo());
        System.out.println(producto);
    }

    public abstract T getItemAAgregar();

    public abstract void quitaDetalle();

    public void limpia() {
        itemElegido = null;
    }

    public abstract T getInstancia();

    public void createDynamicColumns() {
        if (columnasDisponibles == null || columnasDisponibles.isEmpty()) {

            int indice = 0;
            for (Field field : getInstancia().getClass().getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                String tipo = field.getType().getSimpleName();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof DetColumna) {
                        DetColumna c = (DetColumna) annotation;
                        if (c.visible()) {
                            System.out.println("TIPO::: " + tipo);
                            ColumnModel cm = new ColumnModel(c.cabecera(), c.propiedad(), c.visible(), getInstancia().getClass().getName(), tipo, "admin", indice, c.bean(), c.fecha(), c.selectOne(),false);
                            columnasDisponibles.add(cm);
                        }
                    }
                }
                indice++;
            }
        }

    }

    public List<ColumnModel> getColumnasDisponibles() {
        createDynamicColumns();
        return columnasDisponibles;
    }

    public void setColumnasDisponibles(List<ColumnModel> columnasDisponibles) {
        this.columnasDisponibles = columnasDisponibles;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
