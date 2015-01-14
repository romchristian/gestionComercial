/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.compra.persistencia.DetFacturaProveedor;
import py.gestion.compra.persistencia.DetFacturaProveedorProducto;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.utils.servicios.UtilNumero;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class CompDetFacturaProveedor implements Serializable {

    @EJB
    private ProductoDAO ejbProducto;
    private Producto producto = new Producto();
    private FacturaProveedor facturaProveedor;
    private double cantidad = 1d;
    private String descripcion;
    private double iva;
    private UnidadMedida unidadMedida;
    private double precio;
    private DetFacturaProveedorProducto itemFP = new DetFacturaProveedorProducto();
    private List<DetFacturaProveedor> detalles;
    
    
    private BigDecimal iva05;
    private BigDecimal iva10;
    private BigDecimal totalIva;
    private BigDecimal subtotalExenta;
    private BigDecimal subtotalGravada05;
    private BigDecimal subtotalGravada10;
    private BigDecimal total;
    
    
    private DetFacturaProveedorProducto detalleElegido;

    public DetFacturaProveedorProducto getDetalleElegido() {
        return detalleElegido;
    }

    public void setDetalleElegido(DetFacturaProveedorProducto detalleElegido) {
        this.detalleElegido = detalleElegido;
    }
    
    

    public void iniciaConversacion() {
        limpia();
    }

    public void terminaConversacion() {
        limpia();
    }

    public DetFacturaProveedor getItemFP() {

        itemFP.setCantidad(new BigDecimal(cantidad));
        itemFP.setFacturaProveedor(facturaProveedor);
        itemFP.setProducto(producto);
        itemFP.setMonto(new BigDecimal(precio));
        itemFP.setDescripcion(descripcion);
        itemFP.setUnidadMedida(unidadMedida);
        itemFP.setIva(new BigDecimal(iva)); 
        BigDecimal valor = new BigDecimal(cantidad).multiply(new BigDecimal(precio));
        
        itemFP.setExenta(new BigDecimal(0d));
        itemFP.setGravada05(new BigDecimal(0d));
        itemFP.setGravada10(new BigDecimal(0d));
        itemFP.setSubtotal(valor);
        
        if (iva == 0d) {
            itemFP.setExenta(valor);
        } else if (iva == 5d) {
            itemFP.setGravada05(valor);
        } else if (iva == 10d) {
            itemFP.setGravada10(valor);
        }

        return itemFP;
    }

    public List<DetFacturaProveedor> getDetalles() {
        cargaTotales();
        return detalles;
    }

    public void setDetalles(List<DetFacturaProveedor> detalles) {
        this.detalles = detalles;
    }

    public void setItemFP(DetFacturaProveedorProducto itemFP) {
        this.itemFP = itemFP;
    }

    public FacturaProveedor getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(FacturaProveedor facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public Producto getProducto() {
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

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

  

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public void busca() {
        System.out.println("COD: " + producto.getCodigo());   
        producto = ejbProducto.findPorCodigoEstricto(producto.getCodigo());
        descripcion = producto.getNombre();
        //iva = producto.getIva().getValor();
        System.out.println(producto);
    }

    public String agregaDetalle() {
        if (detalles == null) {
            detalles = new ArrayList<DetFacturaProveedor>();
        }
        detalles.add(getItemFP());
        limpia();
        return null;
    }

    public void quitaDetalle() {
        detalles.remove(detalleElegido);
    }

    private void limpia() {
        producto = new Producto();
        itemFP = new DetFacturaProveedorProducto();
        cantidad = 0d;
        descripcion = null;
        iva = 0d;
        precio = 0d;
    }

    public BigDecimal getIva05() {
        return iva05;
    }

    public void setIva05(BigDecimal iva05) {
        this.iva05 = iva05;
    }

    public BigDecimal getIva10() {
        return iva10;
    }

    public void setIva10(BigDecimal iva10) {
        this.iva10 = iva10;
    }

    public BigDecimal getTotalIva() {
        return totalIva;
    }

    public void setTotalIva(BigDecimal totalIva) {
        this.totalIva = totalIva;
    }

    public BigDecimal getSubtotalExenta() {
        return subtotalExenta;
    }

    public void setSubtotalExenta(BigDecimal subtotalExenta) {
        this.subtotalExenta = subtotalExenta;
    }

    public BigDecimal getSubtotalGravada05() {
        return subtotalGravada05;
    }

    public void setSubtotalGravada05(BigDecimal subtotalGravada05) {
        this.subtotalGravada05 = subtotalGravada05;
    }

    public BigDecimal getSubtotalGravada10() {
        return subtotalGravada10;
    }

    public void setSubtotalGravada10(BigDecimal subtotalGravada10) {
        this.subtotalGravada10 = subtotalGravada10;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

   

    public void cargaTotales() {
        subtotalExenta = new BigDecimal(0);
        subtotalGravada05 = new BigDecimal(0);
        subtotalGravada10 = new BigDecimal(0);
        int precision = 0;
        if(facturaProveedor != null && facturaProveedor.getMoneda() != null){
            precision = facturaProveedor.getMoneda().getDecimales();
        }
        
        for (DetFacturaProveedor d : detalles) {
            subtotalExenta = subtotalExenta.add(d.getExenta()).setScale(precision, RoundingMode.HALF_EVEN);
            subtotalGravada05 = subtotalGravada05.add(d.getGravada05()).setScale(precision, RoundingMode.HALF_EVEN);
            subtotalGravada10 = subtotalGravada10.add(d.getGravada10()).setScale(precision, RoundingMode.HALF_EVEN);
        }
        
        
        total = subtotalExenta.add(subtotalGravada05).add(subtotalGravada10).setScale(precision, RoundingMode.HALF_EVEN);
        
        iva05 = UtilNumero.getIva(5, subtotalGravada05,precision);
        iva10 = UtilNumero.getIva(10, subtotalGravada10,precision);
        totalIva = iva05.add(iva10).setScale(precision, RoundingMode.HALF_EVEN);
    }
    
   
}
