/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.persistencia;

import py.gestion.clientes.persistencia.enums.TipoOperacion;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import py.gestion.cobranza.persistencia.DetCobroCuota;
import py.gestion.prestamos.persistencia.Prestamo;

/**
 *
 * @author christian
 */
@Entity
public class OperacionCobroCuota extends DetCuentaCliente<DetCobroCuota> {

    
    @ManyToOne
    private DetCobroCuota detCobroCuota;
    
    public OperacionCobroCuota() {
        setTipoOperacion(TipoOperacion.PAGO_CUOTA);
    }

    public OperacionCobroCuota(DetCobroCuota detCobroCuota) {
        this();
        this.detCobroCuota = detCobroCuota;
        setMontoCredito(0d);
        setMontoDebito(detCobroCuota.getMonto());
        setDescripcion("Pago cuota "+ detCobroCuota.getDetPrestamo().getNroCuota()+" Prestamo nro "+ detCobroCuota.getCobroCuota().getPrestamo().getId());
        
    }

    

    @Override
    public DetCobroCuota getReferencia() {
        return detCobroCuota;
    }

   
    
}
