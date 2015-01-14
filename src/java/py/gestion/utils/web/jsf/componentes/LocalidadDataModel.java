/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web.jsf.componentes;

import java.util.List;
import javax.faces.model.ListDataModel;
import org.primefaces.model.SelectableDataModel;
import py.gestion.adm.persistencia.Localidad;

/**
 *
 * @author cromero
 */
public class LocalidadDataModel extends ListDataModel<Localidad> implements SelectableDataModel<Localidad> {

    public LocalidadDataModel(List<Localidad> data) {
        super(data);
    }

    @Override
    public Object getRowKey(Localidad object) {
        return object.getId();
    }

    @Override
    public Localidad getRowData(String rowKey) {
        List<Localidad> localidades = (List<Localidad>) getWrappedData();

        for (Localidad localidad : localidades) {
            if (localidad.getId() == Long.valueOf(rowKey)) {
                return localidad;
            }
        }

        return null;
    }
}
