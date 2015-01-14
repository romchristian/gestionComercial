/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.fitlros.ordencompra;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DualListModel;
import py.gestion.adm.servicios.ObraDAO;
import py.gestion.adm.persistencia.Obra;
import py.gestion.utils.web.JsfUtil;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class FiltroObraOC implements Serializable {

    private DualListModel<Obra> dualList;
    @EJB
    private ObraDAO obraDAO;
    private Obra obra;
    private boolean varios;

    @PostConstruct
    private void init() {
        recarga();
    }

    public DualListModel<Obra> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Obra> dualList) {
        this.dualList = dualList;
    }

    
    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra Obra) {
        this.obra = Obra;
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
        dualList = new DualListModel<Obra>(obraDAO.findAll(), new ArrayList<Obra>());
    }

    public String getFiltro(StringBuilder sb, boolean esWhere, QueryParameter queryParameter) {


        if (!varios) {
            if (esWhere) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(" oc.obra = :obra ");
            queryParameter.and("obra", obra);
        } else {
            if (dualList.getTarget() != null && !dualList.getTarget().isEmpty()) {

                if (esWhere) {
                    sb.append(" where ( ");
                } else {
                    sb.append(" and ( ");
                }
                
                int i = 0;
                int cant = dualList.getTarget().size();
                for (Obra p : dualList.getTarget()) {
                    i++;

                    String nombreParam = "obra" + i;
                    sb.append(" oc.obra = ");
                    sb.append(":").append(nombreParam);
                    sb.append(" ");
                    if (i < cant) {
                        sb.append(" or ");
                    }
                    queryParameter.and(nombreParam, p);

                }
                sb.append(" ) ");
            }
        }


        return sb.toString();
    }

    
}
