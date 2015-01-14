/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.web.DetOrdenCompraBean;

/**
 *
 * @author christian
 */
public class DetOrdenCompraConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        DetOrdenCompraBean controller = (DetOrdenCompraBean) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "detOrdenCompraBean");
        return controller.find(getKey(value));
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
        if (object instanceof DetOrdenCompra) {
            DetOrdenCompra o = (DetOrdenCompra) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DetOrdenCompra.class.getName());
        }
    }
}
