/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.persistence.Entity;


/**
 *
 * @author cromero
 */
@Entity
public class DetLibroCompraFactura extends DetFormularioHechauka {

    public DetLibroCompraFactura() {
    }

    public DetLibroCompraFactura(FormularioHechauka cabecera, FacturaProveedor factura) {
        super(2, factura.getProveedor(),
                factura.getProveedor().getDocumento(),
                factura.getProveedor().getCodVerificador(),
                factura.getProveedor().getNombre(),
                Long.parseLong(factura.getTimbrado()),
                1, factura.getCodigoEstablecimiento() + "-" + factura.getCodigoSucursal() + "-" + factura.getNumero(),
                factura.getEmision(),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalgravada10().subtract(factura.getTotaliva10()).setScale(0, RoundingMode.HALF_EVEN)
                : factura.getTotalgravada10().multiply(new BigDecimal(factura.getCotizacion().getVenta()).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN).subtract(factura.getTotaliva10().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotaliva10().setScale(0, RoundingMode.HALF_EVEN) : factura.getTotaliva10().multiply(new BigDecimal(factura.getCotizacion().getVenta()).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalgravada05().subtract(factura.getTotaliva05()).setScale(0, RoundingMode.HALF_EVEN)
                : factura.getTotalgravada05().multiply(new BigDecimal(factura.getCotizacion().getVenta()).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN).subtract(factura.getTotaliva05().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotaliva05().setScale(0, RoundingMode.HALF_EVEN) : factura.getTotaliva05().multiply(new BigDecimal(factura.getCotizacion().getVenta()).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalexenta().setScale(0, RoundingMode.HALF_EVEN) : factura.getTotalexenta().multiply(new BigDecimal(factura.getCotizacion().getVenta()).setScale(0, RoundingMode.HALF_EVEN)).setScale(0, RoundingMode.HALF_EVEN),
                0,
                cabecera);
    }

    public DetLibroCompraFactura(FormularioHechauka cabecera, FacturaProveedor factura, boolean esDiestra) {
        super(2, factura.getProveedor(),
                factura.getProveedor().getDocumento(),
                factura.getProveedor().getCodVerificador(),
                factura.getProveedor().getNombre(),
                Long.parseLong(factura.getTimbrado()),
                1, factura.getCodigoEstablecimiento() + "-" + factura.getCodigoSucursal() + "-" + factura.getNumero(),
                factura.getEmision(),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalgravada10() : factura.getTotalgravada10().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotaliva10() : factura.getTotaliva10().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalgravada05() : factura.getTotalgravada05().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotaliva05() : factura.getTotaliva05().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN),
                factura.getMoneda().isMonedaLocal() ? factura.getTotalexenta() : factura.getTotalexenta().multiply(new BigDecimal(factura.getCotizacion().getVenta())).setScale(0, RoundingMode.HALF_EVEN),
                0,
                cabecera);
    }

    public DetLibroCompraFactura(FacturaProveedor factura, FormularioHechauka cabecera) {
        this(cabecera, factura,true);
    }
}
