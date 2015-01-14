/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.proveedores.web.ProveedorBean;

/**
 *
 * @author christian
 */

public class ProveedorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
          ProveedorBean controller = (ProveedorBean) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "proveedorBean");
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
        if (object instanceof Proveedor) {
            Proveedor o = (Proveedor) object;
            return getStringKey(o.getId());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Proveedor.class.getName());
        }
    }
}
