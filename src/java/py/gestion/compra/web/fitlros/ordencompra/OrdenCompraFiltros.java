/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.fitlros.ordencompra;

import py.gestion.compra.web.fitlros.ordencompra.FiltroProveedorOC;
import py.gestion.compra.web.fitlros.ordencompra.FiltroCreacionOC;
import py.gestion.compra.web.fitlros.ordencompra.FiltroEstadoOC;
import py.gestion.compra.web.fitlros.ordencompra.FiltroNroOC;
import py.gestion.compra.web.fitlros.ordencompra.FiltroObraOC;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class OrdenCompraFiltros implements Serializable {

    private List<FiltroOC> filtrosDisponibles;
    private List<FiltroOC> filtrosSeleccionados;
    @Inject
    private FiltroEstadoOC filtroEstadoOC;
    @Inject
    private FiltroProveedorOC filtroProveedorOC;
    @Inject
    private FiltroCreacionOC filtroCreacionOC;
    @Inject
    private FiltroNroOC filtroNroOC;
    @Inject
    private FiltroObraOC filtroObraOC;

    public List<FiltroOC> getFiltrosDisponibles() {
        if (filtrosDisponibles == null) {
            filtrosDisponibles = new ArrayList<FiltroOC>();
            filtrosDisponibles.add(new FiltroOC("Nro"));
            filtrosDisponibles.add(new FiltroOC("Proveedor"));
            filtrosDisponibles.add(new FiltroOC("Obra"));
            filtrosDisponibles.add(new FiltroOC("Creacion"));
            filtrosDisponibles.add(new FiltroOC("Estado"));
        }
        return filtrosDisponibles;
    }

    public void setFiltrosDisponibles(List<FiltroOC> filtrosDisponibles) {
        this.filtrosDisponibles = filtrosDisponibles;
    }

    public List<FiltroOC> getFiltrosSeleccionados() {
        if (filtrosSeleccionados == null) {
            filtrosSeleccionados = new ArrayList<FiltroOC>();
        }
        return filtrosSeleccionados;
    }

    public void setFiltrosSeleccionados(List<FiltroOC> filtrosSeleccionados) {
        this.filtrosSeleccionados = filtrosSeleccionados;
    }

    public void onCarDrop(DragDropEvent ddEvent) {
        FiltroOC filtro = ((FiltroOC) ddEvent.getData());
        filtrosSeleccionados.add(filtro);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(filtro.getOpenDialog());

    }

    public void quitarFiltro(FiltroOC f) {
        if (f.getNombre().compareTo("Estado") == 0) {
            filtroEstadoOC.recarga();

        } else if (f.getNombre().compareTo("Proveedor") == 0) {
            filtroProveedorOC.recarga();
        }
        filtrosSeleccionados.remove(f);
    }

    public String getFiltro(QueryParameter queryParameter) {
        StringBuilder sb = new StringBuilder("select oc from OrdenCompra oc ");

        boolean esWhere = true;
        for (FiltroOC f : filtrosSeleccionados) {
            if (f.getNombre().compareTo("Estado") == 0) {
                filtroEstadoOC.getFiltro(sb, esWhere, queryParameter);
                esWhere = false;
            } else if (f.getNombre().compareTo("Proveedor") == 0) {
                filtroProveedorOC.getFiltro(sb, esWhere, queryParameter);
                esWhere = false;
            } else if (f.getNombre().compareTo("Creacion") == 0) {
                filtroCreacionOC.getFiltro(sb, esWhere, queryParameter);
                esWhere = false;
            } else if (f.getNombre().compareTo("Nro") == 0) {
                filtroNroOC.getFiltro(sb, esWhere, queryParameter);
                esWhere = false;
            } else if (f.getNombre().compareTo("Obra") == 0) {
                filtroObraOC.getFiltro(sb, esWhere, queryParameter);
                esWhere = false;
            }

        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public boolean hayFiltros() {
        boolean R = false;
        if (filtrosSeleccionados != null && !filtrosSeleccionados.isEmpty()) {
            R = true;
        }
        return R;
    }
}
