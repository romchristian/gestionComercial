/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.pagos.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;    
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import py.gestion.pagos.persistencia.EstadoOrdenPago;
import py.gestion.pagos.persistencia.OrdenPago;
import py.gestion.pagos.web.OrdenPagoBean;



/**
 *
 * @author christian
 */
@FacesConverter(forClass=EstadoOrdenPago.class)
public class EstadoOrdenPagoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        OrdenPagoBean controller = (OrdenPagoBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "ordenPagoBean");
        
        return controller.findEstado(getKey(value));
    }

    java.lang.Long getKey(String value) {
        java.lang.Long key;
        key = Long.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Long value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EstadoOrdenPago) {
            EstadoOrdenPago o = (EstadoOrdenPago) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoOrdenPago.class.getName());
        }
    }
}
