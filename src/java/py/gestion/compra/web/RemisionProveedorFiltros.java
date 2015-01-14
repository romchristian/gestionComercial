/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.filtros.RemisionProveedor.FiltroFechaDocRP;
import py.gestion.compra.persistencia.filtros.RemisionProveedor.FiltroRecepcionRP;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.web.filtros.Filtros;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class RemisionProveedorFiltros extends Filtros {
    private FiltroFechaDocRP fechaDocumento;
    private FiltroRecepcionRP fechaRecepcion;
    
    @Override
    public String getNombreClase() {
        return FacturaProveedor.class.getSimpleName();
    }

    @Override
    public List<Filtro> getFiltros() {
        List<Filtro> R = new ArrayList<Filtro>();
        fechaDocumento = new FiltroFechaDocRP("FechaDocumento");
        fechaRecepcion = new FiltroRecepcionRP("FechaRecepcion");
        R.add(fechaDocumento);
        R.add(fechaRecepcion);
        return R;
    }

    public FiltroFechaDocRP getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(FiltroFechaDocRP fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public FiltroRecepcionRP getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(FiltroRecepcionRP fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
