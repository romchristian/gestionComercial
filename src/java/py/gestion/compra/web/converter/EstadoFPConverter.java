/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;

/**
 *
 * @author christian
 */
public class EstadoFPConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        EstadoFacturaProveedor R = EstadoFacturaProveedor.CREADA;
        if (value.compareTo(EstadoFacturaProveedor.CONFORMADA.toString()) == 0) {
            R = EstadoFacturaProveedor.CONFORMADA;
        } else if (value.compareTo(EstadoFacturaProveedor.PAGADA_PARCIALMENTE.toString()) == 0) {
            R = EstadoFacturaProveedor.PAGADA_PARCIALMENTE;
        } else if (value.compareTo(EstadoFacturaProveedor.CANCELADA.toString()) == 0) {
            R = EstadoFacturaProveedor.CANCELADA;
        } else if (value.compareTo(EstadoFacturaProveedor.ANULADA.toString()) == 0) {
            R = EstadoFacturaProveedor.ANULADA;
        }

        return R;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EstadoFacturaProveedor) {
            EstadoFacturaProveedor o = (EstadoFacturaProveedor) object;
            return o.toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoFacturaProveedor.class.getName());
        }
    }
}
