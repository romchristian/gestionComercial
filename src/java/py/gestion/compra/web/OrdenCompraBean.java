/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import py.gestion.compra.web.fitlros.ordencompra.OrdenCompraFiltros;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Query;
import org.primefaces.event.DragDropEvent;
import py.gestion.utils.web.jsf.componentes.CompDetOrdenCompra;
import py.gestion.compra.servicios.OrdenCompraDAO;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoOC;
import py.gestion.compra.persistencia.eventos.ordencompra.EventoObsOC;
import py.gestion.compra.web.fitlros.ordencompra.FiltroOC;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Named
@ConversationScoped
public class OrdenCompraBean extends BeanGenerico<OrdenCompra> implements Serializable {

    @EJB
    private OrdenCompraDAO ejb;
    @Inject
    private CompDetOrdenCompra compDetOrdenCompra;
    @Inject
    private Conversation conversation;
    private String nuevaObservacion;
    private List<OrdenCompra> listado = new ArrayList<OrdenCompra>();
    @Inject
    private OrdenCompraFiltros filtros;
    @Inject
    private PickListOCFPBean pickListBean;
    

    private void iniciaConversacion() {

        if (conversation.isTransient()) {
            conversation.begin();
        } else {
            terminaConversacion();
            conversation.begin();
        }




        if (getActual().getDetalles() == null) {
            getActual().setDetalles(new ArrayList<DetOrdenCompra>());
        }

        compDetOrdenCompra.iniciaConversacion();
        compDetOrdenCompra.setDetalles(getActual().getDetalles());
        compDetOrdenCompra.setOrdenCompra(getActual());
    }

    private void terminaConversacion() {
        compDetOrdenCompra.terminaConversacion();
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    @Override
    public AbstractDAO<OrdenCompra> getEjb() {
        return ejb;
    }

    @Override
    public OrdenCompra getNuevo() {
        return new OrdenCompra();
    }

    public String nuevo() {
        setActual(null);
        iniciaConversacion();
        return "nuevo.xhtml";
    }

    @Override
    public String preparaEdicion(OrdenCompra obj) {
        String R = super.preparaEdicion(obj);
        iniciaConversacion();
        return R;
    }

    @Override
    public String create() {
        String R = super.create();
        terminaConversacion();
        pickListBean.resfresca();
        return R;
    }

    @Override
    public String edit() {
        String R = super.edit();
        terminaConversacion();
        return R;
    }

    public String getNuevaObservacion() {
        return nuevaObservacion;
    }

    public void setNuevaObservacion(String nuevaObservacion) {
        this.nuevaObservacion = nuevaObservacion;
    }

    public void agregaObersvacion() {
        ejb.agregaObservacion(getActual(), nuevaObservacion);
        nuevaObservacion = null;
    }

    public List<EventoObsOC> obtObservaciones() {
        return ejb.getObservaciones(getActual());
    }

    public List<EventoOC> obtEventos() {
        return ejb.getEventos(getActual());
    }

    public String atras() {
        terminaConversacion();
        return "listado.xhtml";
    }

    public void limpiaObservacion() {
        nuevaObservacion = null;
    }

    public String autoriza() {
        ejb.autoriza(getActual());
        return null;
    }

    public String ponependiente() {
        ejb.pendiente(getActual());
        return null;
    }

    public String poneenproceso() {
        ejb.enproceso(getActual());
        return null;
    }

    public String cierraoc() {
        ejb.cierra(getActual());
        return null;
    }

    public Long countPedientes() {
        return ejb.countPendientes();
    }

    public Long countAutorizados() {
        return ejb.countAutorizados();
    }

    public Long countEnProceso() {
        return ejb.countEnProceso();
    }

    public Long countCerradas() {
        return ejb.countCerradas();
    }

    public String colorPendiente() {
        return countPedientes() > 0 ? "red" : "black";
    }

    public String colorAutorizados() {
        return countAutorizados() > 0 ? "green" : "black";
    }

    public String colorEnProceso() {
        return countEnProceso() > 0 ? "blue" : "black";
    }

    public String busca() {
        QueryParameter queryParameter = new QueryParameter();
        listado = ejb.findAll(filtros.getFiltro(queryParameter), queryParameter);
        return null;
    }

    @Override
    public List<OrdenCompra> findAll() {
        if (filtros.hayFiltros()) {
            busca();
        } else {
            listado = new ArrayList<OrdenCompra>();
        }
        return listado;
    }
}
