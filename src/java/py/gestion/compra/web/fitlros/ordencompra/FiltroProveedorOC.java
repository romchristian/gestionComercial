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
import py.gestion.proveedores.servicios.ProveedorService;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.utils.web.JsfUtil;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class FiltroProveedorOC implements Serializable {

    private DualListModel<Proveedor> dualList;
    @EJB
    private ProveedorService proveedorDAO;
    private Proveedor proveedor;
    private boolean varios;

    @PostConstruct
    private void init() {
        recarga();
    }

    public DualListModel<Proveedor> getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel<Proveedor> dualList) {
        this.dualList = dualList;
    }

    
    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
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
        dualList = new DualListModel<Proveedor>(proveedorDAO.findAll(), new ArrayList<Proveedor>());
    }

    public String getFiltro(StringBuilder sb, boolean esWhere, QueryParameter queryParameter) {



        if (!varios) {
            if (esWhere) {
                sb.append(" where ");
            } else {
                sb.append(" and ");
            }
            sb.append(" oc.proveedor = :proveedor ");
            queryParameter.and("proveedor", proveedor);
        } else {
            if (dualList.getTarget() != null && !dualList.getTarget().isEmpty()) {

                if (esWhere) {
                    sb.append(" where ( ");
                } else {
                    sb.append(" and ( ");
                }
                
                int i = 0;
                int cant = dualList.getTarget().size();
                for (Proveedor p : dualList.getTarget()) {
                    i++;

                    String nombreParam = "proveedor" + i;
                    sb.append(" oc.proveedor = ");
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
