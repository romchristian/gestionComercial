/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Transient;
import org.primefaces.model.DualListModel;
import py.gestion.utils.servicios.QueryParameter;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.filtros.Filtros;

/**
 *
 * @author cromero
 */
@Entity
public abstract class FiltroAutoComplete extends Filtro {

    private boolean varios = false;
    private Long entidadId;
    private String bean;
    @Transient
    private BeanGenerico beanObject;
    @Transient
    private DualListModel dualList;

    public FiltroAutoComplete() {
    }

    public FiltroAutoComplete( String nombre, String bean) {
        super(nombre);
        this.bean = bean;
        recarga();
    }
    
    
    private void recarga() {
        List elegidos = getDetalles();
        List sourceAux = new ArrayList();
        sourceAux.addAll(getBeanObject().findAll());
        
        if(elegidos != null){
            Iterator it = sourceAux.iterator();
            while (it.hasNext()) {
                Object ent =  it.next();
                for(Object e : elegidos){
                    if(ent.equals(e)){
                        it.remove();
                        sourceAux.remove(e);
                    }
                }
            }
        }else{
            elegidos = new ArrayList();
        }
        dualList = new DualListModel(sourceAux, elegidos);
    }

    @Override
    public String getFiltro(StringBuilder sb, QueryParameter queryParameter) {
        

        if (!varios) {
            sb.append(" and ").append(Filtros.OBJ).append(".").append(getCampo());
            sb.append("  = :obj ");
            
            queryParameter.and("obj", getBeanObject().find(entidadId == null?0L: entidadId));
        } else {
            if (dualList.getTarget() != null && !dualList.getTarget().isEmpty()) {
                int i = 0;
                int cant = dualList.getTarget().size();
                if(cant > 0){
                    sb.append(" and ( ");
                }
                for (Object p : dualList.getTarget()) {
                    i++;

                    String nombreParam = "obj" + i;
                    sb.append(Filtros.OBJ).append(".").append(getCampo());
                    sb.append("  = ");
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

    public abstract String getCampo();

    public boolean isVarios() {
        return varios;
    }

    public void setVarios(boolean varios) {
        this.varios = varios;
    }

    public Long getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(Long entidadId) {
        this.entidadId = entidadId;
    }

    public BeanGenerico getBeanObject() {
        System.out.println("BEAN : " + bean);
        if (beanObject == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            beanObject = (BeanGenerico) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, bean);
        }
        return beanObject;
    }

    public String getBean() {
        return bean;
    }

    public void setBean(String bean) {
        this.bean = bean;
    }

    public DualListModel getDualList() {
        return dualList;
    }

    public void setDualList(DualListModel dualList) {
        this.dualList = dualList;
    }
}
