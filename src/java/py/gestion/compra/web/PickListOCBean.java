/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.compra.servicios.OrdenCompraDAO;
import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author Elias
 */
public abstract class PickListOCBean implements Serializable {

    @EJB
    private OrdenCompraDAO ordenCompraDAO;
    private OrdenCompra ordenCompraElegida;
    private List<OrdenCompra> ordenesCompra;
    private DualListModel<DetOrdenCompra> detalles;
    private List<DetOrdenCompra> source;
    private List<DetOrdenCompra> target;
    @PersistenceContext(unitName = "SYSCVSAPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    private List<DetOrdenCompra> targetAux;
    private Proveedor proveedor;

    public void resfresca() {
        resfrescaConsulta();
        source = new ArrayList<DetOrdenCompra>();
        target = new ArrayList<DetOrdenCompra>();
        targetAux = new ArrayList<DetOrdenCompra>();
        detalles = new DualListModel<DetOrdenCompra>(source, target);
    }

    public void resfrescaConsulta() {

        if (proveedor != null) {
            ordenesCompra = new ArrayList<OrdenCompra>();
            ordenesCompra.addAll(ordenCompraDAO.findPedientes(proveedor));
            remueveOCSNoPedientes();
        } else {
            ordenesCompra = new ArrayList<OrdenCompra>();
        }

    }

    public void onRowSelect(SelectEvent event) {

        List<DetOrdenCompra> sourceAux = new ArrayList<DetOrdenCompra>();
        sourceAux.addAll(((OrdenCompra) event.getObject()).getDetalles());

        Iterator it = sourceAux.iterator();


        while (it.hasNext()) {
            DetOrdenCompra dSourceAux = refresh((DetOrdenCompra) it.next());
            if (dSourceAux.getCantidadAFacturar() == 0) {
                it.remove();
                sourceAux.remove(dSourceAux);
            }
            for (DetOrdenCompra dtargetAux : targetAux) {
                if (dSourceAux.equals(dtargetAux)) {
                    it.remove();
                    sourceAux.remove(dSourceAux);
                }
            }
        }

        for (DetOrdenCompra d : sourceAux) {
            d.setDetallesFacturados(refresh(d).getDetallesFacturados());
        }

        source.clear();
        source.addAll(sourceAux);
        detalles.setSource(source);
        detalles.setTarget(target);
    }

    public void onTransfer(TransferEvent event) {
        if (event.isAdd()) {
            for (Object item : event.getItems()) {
                DetOrdenCompra d = (DetOrdenCompra) item;
                //d.setAsociadoConFP(true);
                targetAux.add(d);
            }
        }
        if (event.isRemove()) {

            for (Object item : event.getItems()) {
                DetOrdenCompra d = (DetOrdenCompra) item;
                //d.setAsociadoConFP(false);
                targetAux.remove(d);
            }
        }


        target.clear();
        target.addAll(targetAux);
    }

    protected DetOrdenCompra refresh(DetOrdenCompra doc) {
        try {
            utx.begin();
            doc = em.find(DetOrdenCompra.class, doc.getId());
            em.refresh(doc);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
        return doc;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public List<DetOrdenCompra> getTarget() {
        return target;
    }

    public OrdenCompra getOrdenCompraElegida() {
        return ordenCompraElegida;
    }

    public void setOrdenCompraElegida(OrdenCompra ordenCompraElegida) {
        this.ordenCompraElegida = ordenCompraElegida;
    }

    public List<OrdenCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public void setOrdenesCompra(List<OrdenCompra> ordenesCompra) {
        this.ordenesCompra = ordenesCompra;
    }

    public DualListModel<DetOrdenCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(DualListModel<DetOrdenCompra> detalles) {
        this.detalles = detalles;
    }

    public abstract void remueveOCSNoPedientes();
}
