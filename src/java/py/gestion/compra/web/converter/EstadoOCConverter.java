/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import py.gestion.compra.persistencia.enums.EstadoOC;

/**
 *
 * @author christian
 */

public class EstadoOCConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        EstadoOC R = EstadoOC.PENDIENTE_AUTORIZACION;
          if(value.compareTo(EstadoOC.AUTORIZADO.toString()) == 0){
              R = EstadoOC.AUTORIZADO;
          } else if(value.compareTo(EstadoOC.EN_PROCESO.toString()) == 0){
              R = EstadoOC.EN_PROCESO;
          }else if(value.compareTo(EstadoOC.CERRADA.toString()) == 0){
              R = EstadoOC.CERRADA;
          }
          
        return R;
    }

    

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof EstadoOC) {
            EstadoOC o = (EstadoOC) object;
            return o.toString();
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EstadoOC.class.getName());
        }
    }
}
