/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.servicios;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import py.gestion.compra.web.fitlros.ordencompra.FiltroObraOC;

/**
 *
 * @author cromero
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ObraListener {

    @Inject
    private FiltroObraOC filtroObraOC;

    public void escuchaEvento(@Observes(during= TransactionPhase.AFTER_SUCCESS) ObraEvento evento) {
        filtroObraOC.recarga();
    }
}
