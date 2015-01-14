/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.servicios.listener;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import py.gestion.proveedores.persistencia.eventos.proveedor.ProveedorEvento;
import py.gestion.compra.web.fitlros.ordencompra.FiltroProveedorOC;

/**
 *
 * @author cromero
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ProveedorListener {
    @Inject
    private FiltroProveedorOC filtroProveedorOC;
    
    public void escuchaEvento(@Observes(during= TransactionPhase.AFTER_SUCCESS) ProveedorEvento evento){
        filtroProveedorOC.recarga();
    }
}
