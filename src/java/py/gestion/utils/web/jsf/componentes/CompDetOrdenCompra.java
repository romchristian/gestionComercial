/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;

/**
 *
 * @author christian
 */
@Named
@ConversationScoped
public class CompDetOrdenCompra implements Serializable {

    @EJB
    private ProductoDAO ejbProducto;
    private Producto producto;
    private OrdenCompra ordenCompra;
    private double cantidad = 1d;
    private String descripcion;
    private UnidadMedida unidadMedida;
    private double costo;
    private DetOrdenCompra itemOC;
    private List<DetOrdenCompra> detalles;
    @Inject
    private Conversation conversation;

    public void iniciaConversacion() {
        if (conversation.isTransient()) {
            conversation.begin();
        } else {
            terminaConversacion();
            conversation.begin();
        }
        limpia();
    }

    public void terminaConversacion() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    public DetOrdenCompra getItemOC() {
if(itemOC==null)
    itemOC = new DetOrdenCompra();
        itemOC.setCantidadSolicitada(cantidad);
        itemOC.setOrdenCompra(ordenCompra);
        itemOC.setProducto(producto);
        itemOC.setPrecioPactado(costo);
        itemOC.setDescripcion(descripcion);
        itemOC.setUnidadMedida(unidadMedida);

        return itemOC;
    }

    public List<DetOrdenCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetOrdenCompra> detalles) {
        this.detalles = detalles;
    }

    public void setItemOC(DetOrdenCompra itemOC) {
        this.itemOC = itemOC;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Producto getProducto() {
        if (producto == null) {
            producto = new Producto();
        }
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ProductoDAO getEjbProducto() {
        return ejbProducto;
    }

    public void setEjbProducto(ProductoDAO ejbProducto) {
        this.ejbProducto = ejbProducto;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void busca() {
        producto = ejbProducto.findPorCodigoEstricto(producto.getCodigo());
        descripcion = producto.getNombre();
        System.out.println(producto);
    }

    public String agregaDetalle() {
        if (detalles == null) {
            detalles = new ArrayList<DetOrdenCompra>();
        }
        detalles.add(getItemOC());
        limpia();
        return null;
    }

    public void quitaDetalle(DetOrdenCompra itemOC) {
        detalles.remove(itemOC);
    }

    private void limpia() {
        producto = new Producto();
        itemOC = new DetOrdenCompra();
        cantidad = 1d;
    }
}
