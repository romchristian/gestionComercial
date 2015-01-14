/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.web.CotizacionBean;


/**
 *
 * @author christian
 */
@FacesConverter(forClass = Cotizacion.class)
public class CotizacionConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
          CotizacionBean controller = (CotizacionBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "cotizacionBean");
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
        if (object instanceof Cotizacion) {
            Cotizacion o = (Cotizacion) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Cotizacion.class.getName());
        }
    }
}
