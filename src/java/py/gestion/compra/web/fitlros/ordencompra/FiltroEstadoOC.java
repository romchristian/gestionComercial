/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.fitlros.ordencompra;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import py.gestion.compra.persistencia.enums.EstadoOC;
import py.gestion.utils.web.JsfUtil;
import py.gestion.utils.web.ProductorEnums;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class FiltroEstadoOC implements Serializable {

    private DualListModel<EstadoOC> dualList;
    @Inject
    private ProductorEnums productorEnums;
    
    private EstadoOC estadoOC;
    private boolean varios;

    @PostConstruct
    private void init() {
        recarga();
    }

    public DualListModel<EstadoOC> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<EstadoOC> dualList) {
        this.dualList = dualList;
    }

    public EstadoOC getEstadoOC() {
        return estadoOC;
    }

    public void setEstadoOC(EstadoOC estadoOC) {
        this.estadoOC = estadoOC;
    }

    public boolean isVarios() {
        return varios;
    }

    public void setVarios(boolean varios) {
        this.varios = varios;
    }

    public void siCambiaSeleccion() {
        JsfUtil.addSuccessMessage("Varios = " + varios);
        System.out.println("Varios = " + varios);
    }

    public void recarga() {
        dualList = new DualListModel<EstadoOC>(productorEnums.obtEstadosOCList(), new ArrayList<EstadoOC>());
    }

    public String getFiltro(StringBuilder sb, boolean esWhere, QueryParameter queryParameter) {



        if (!varios) {
            if (esWhere) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(" oc.estado = :estado ");
            queryParameter.and("estado", estadoOC);
        } else {
            if (dualList.getTarget() != null && !dualList.getTarget().isEmpty()) {

                if (esWhere) {
                    sb.append(" where ( ");
                } else {
                    sb.append(" and ( ");
                }
                
                int i = 0;
                int cant = dualList.getTarget().size();
                for (EstadoOC es : dualList.getTarget()) {
                    i++;

                    String nombreEstado = "estado" + i;
                    sb.append(" oc.estado = ");
                    sb.append(":").append(nombreEstado);
                    sb.append(" ");
                    if (i < cant) {
                        sb.append(" or ");
                    }
                    queryParameter.and(nombreEstado, es);

                }
                sb.append(" ) ");
            }
        }


        return sb.toString();
    }

    
}
