/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.DetRemisionProveedor;
import py.gestion.compra.persistencia.RemisionProveedor;
import py.gestion.compra.servicios.RemisionProveedorDAO;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.jsf.componentes.CompDetRemisionProveedor;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class RemisionProveedorBean extends BeanGenerico<RemisionProveedor> {

    @EJB
    private RemisionProveedorDAO ejb;
    @Inject
    private CompDetRemisionProveedor compDetRemisionProveedor;
    @Inject
    private PickListOCRPBean pickListBean;

    private void iniciaConversacion() {


        if (getActual().getDetalles() == null) {
            getActual().setDetalles(new ArrayList<DetRemisionProveedor>());
        }

        compDetRemisionProveedor.iniciaConversacion();
        compDetRemisionProveedor.setDetalles(getActual().getDetalles());
        compDetRemisionProveedor.setRemisionProveedor(getActual());
    }

    private void terminaConversacion() {
        compDetRemisionProveedor.terminaConversacion();
        pickListBean.resfresca();
    }

    @Override
    public AbstractDAO<RemisionProveedor> getEjb() {
        return ejb;
    }

    @Override
    public RemisionProveedor getNuevo() {
        return new RemisionProveedor();
    }

    @Override
    public String nuevo() {
        setActual(null);
        iniciaConversacion();
        return "nuevo.xhtml";
    }

    @Override
    public String preparaEdicion(RemisionProveedor obj) {
        String R = super.preparaEdicion(obj);
        iniciaConversacion();
        return R;
    }

    public void siCambiaProveedor(SelectEvent event) {
        Proveedor p = (Proveedor) event.getObject();
        getActual().setProveedor(p);
        pickListBean.setProveedor(p);
        pickListBean.resfrescaConsulta();
    }

    public void agregaDetallesDesdeOCs() {
        //Recupero la lista de detalles de OC selecionados
        List<DetOrdenCompra> detocs = pickListBean.getTarget();

        int indice = 1;
        //Limpio detalles de facturas
        getActual().getDetalles().clear();

        for (DetOrdenCompra doc : detocs) {
            //Creo un detalle de factura a partir de un detalle de OC
            DetRemisionProveedor drp = new DetRemisionProveedor(doc, getActual(), indice);
            //Agrego el detalle nuevo a la factura
            doc.asociaDetRemision(drp);
            getActual().getDetalles().add(drp);
            indice++;
        }
    }


}
