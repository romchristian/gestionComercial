/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import py.gestion.adm.persistencia.Estado;
import py.gestion.adm.persistencia.TipoCosto;
import py.gestion.adm.persistencia.TipoPersona;
import py.gestion.contabilidad.persistencia.TipoDiario;
import py.gestion.puntoventa.persisitencia.TipoMetodoPago;

import py.gestion.stock.persistencia.TipoAtributoProducto;

/**
 *
 * @author christian
 */
@Named
@ApplicationScoped
public class ProductorEnums implements Serializable {

    public SelectItem[] obtEstados() {
        SelectItem[] R = new SelectItem[Estado.values().length];
        Estado[] lista = Estado.values();
        for (int i = 0; i < lista.length; i++) {
            Estado e = lista[i];
            R[i] = new SelectItem(e, e.toString());
        }
        return R;
    }

    public SelectItem[] obtTiposPersonas() {
        SelectItem[] R = new SelectItem[TipoPersona.values().length];
        TipoPersona[] lista = TipoPersona.values();
        for (int i = 0; i < lista.length; i++) {
            TipoPersona tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }
    
    public SelectItem[] obtTiposDiario() {
        SelectItem[] R = new SelectItem[TipoDiario.values().length];
        TipoDiario[] lista = TipoDiario.values();
        for (int i = 0; i < lista.length; i++) {
            TipoDiario tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }

    public SelectItem[] obtTiposCosto() {
        SelectItem[] R = new SelectItem[TipoCosto.values().length];
        TipoCosto[] lista = TipoCosto.values();
        for (int i = 0; i < lista.length; i++) {
            TipoCosto tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }

//    public SelectItem[] obtSexo() {
//        SelectItem[] R = new SelectItem[Sexo.values().length];
//        Sexo[] lista = Sexo.values();
//        for (int i = 0; i < lista.length; i++) {
//            Sexo tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }

    public SelectItem[] obtTiposAtributoProducto() {
        SelectItem[] R = new SelectItem[TipoAtributoProducto.values().length];
        TipoAtributoProducto[] lista = TipoAtributoProducto.values();
        for (int i = 0; i < lista.length; i++) {
            TipoAtributoProducto tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }

//    public SelectItem[] obtTiposDoc() {
//        SelectItem[] R = new SelectItem[TipoDocumento.values().length];
//        TipoDocumento[] lista = TipoDocumento.values();
//        for (int i = 0; i < lista.length; i++) {
//            TipoDocumento tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtTiposFacturasProveedor() {
//        SelectItem[] R = new SelectItem[TipoFacturaProveedor.values().length];
//        TipoFacturaProveedor[] lista = TipoFacturaProveedor.values();
//        for (int i = 0; i < lista.length; i++) {
//            TipoFacturaProveedor tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtTiposDireccion() {
//        SelectItem[] R = new SelectItem[TipoDireccion.values().length];
//        TipoDireccion[] lista = TipoDireccion.values();
//        for (int i = 0; i < lista.length; i++) {
//            TipoDireccion tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtSistemasAmortizacion() {
//        SelectItem[] R = new SelectItem[SistemaAmortizacion.values().length];
//        SistemaAmortizacion[] lista = SistemaAmortizacion.values();
//        for (int i = 0; i < lista.length; i++) {
//            SistemaAmortizacion tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtPeriodosPago() {
//        SelectItem[] R = new SelectItem[PeriodoPago.values().length];
//        PeriodoPago[] lista = PeriodoPago.values();
//        for (int i = 0; i < lista.length; i++) {
//            PeriodoPago tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtEstadosOC() {
//        SelectItem[] R = new SelectItem[EstadoOC.values().length];
//        EstadoOC[] lista = EstadoOC.values();
//        for (int i = 0; i < lista.length; i++) {
//            EstadoOC tp = lista[i];
//            R[i] = new SelectItem(tp, tp.getDescripcion());
//        }
//        return R;
//    }
//    
//   
//
//    public SelectItem[] obtEstadosCivil() {
//        SelectItem[] R = new SelectItem[EstadoCivil.values().length];
//        EstadoCivil[] lista = EstadoCivil.values();
//        for (int i = 0; i < lista.length; i++) {
//            EstadoCivil tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//    
    public SelectItem[] obtTiposMetodosPagos() {
        SelectItem[] R = new SelectItem[TipoMetodoPago.values().length];
        TipoMetodoPago[] lista = TipoMetodoPago.values();
        for (int i = 0; i < lista.length; i++) {
            TipoMetodoPago tp = lista[i];
            R[i] = new SelectItem(tp, tp.toString());
        }
        return R;
    }
//
//    public List<EstadoOC> obtEstadosOCList() {
//        List<EstadoOC> R = new ArrayList<EstadoOC>();
//        EstadoOC[] lista = EstadoOC.values();
//        for (int i = 0; i < lista.length; i++) {
//            EstadoOC tp = lista[i];
//            R.add(tp);
//        }
//        return R;
//    }
//
//    public SelectItem[] obtTiposContactosTelefonicos() {
//        SelectItem[] R = new SelectItem[TipoContactoTelefonico.values().length];
//        TipoContactoTelefonico[] lista = TipoContactoTelefonico.values();
//        for (int i = 0; i < lista.length; i++) {
//            TipoContactoTelefonico tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//
//    public SelectItem[] obtEstadosContactosTelefonicos() {
//        SelectItem[] R = new SelectItem[EstadoContactoTelefonico.values().length];
//        EstadoContactoTelefonico[] lista = EstadoContactoTelefonico.values();
//        for (int i = 0; i < lista.length; i++) {
//            EstadoContactoTelefonico tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
//    
//    
//    public SelectItem[] obtEstadosRemisionProveedor() {
//        SelectItem[] R = new SelectItem[EstadoRemisionProveedor.values().length];
//        EstadoRemisionProveedor[] lista = EstadoRemisionProveedor.values();
//        for (int i = 0; i < lista.length; i++) {
//            EstadoRemisionProveedor tp = lista[i];
//            R[i] = new SelectItem(tp, tp.toString());
//        }
//        return R;
//    }
}
