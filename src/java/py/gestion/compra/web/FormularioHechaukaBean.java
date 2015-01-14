/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.event.SelectEvent;
import py.gestion.compra.persistencia.DetFormularioHechauka;
import py.gestion.compra.persistencia.FormularioHechauka;
import py.gestion.compra.persistencia.LibroCompraHechauka;
import py.gestion.compra.persistencia.Periodo;
import py.gestion.compra.servicios.FormularioHechaukaDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.JsfUtil;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class FormularioHechaukaBean extends BeanGenerico<FormularioHechauka> {

    @EJB
    private FormularioHechaukaDAO ejb;
   
    
    private String mes;
    private String anio;
    private Double cotizacionSet;
    private List<FormularioHechaukaImpresion> lista = new ArrayList<FormularioHechaukaImpresion>();
    private FormularioHechaukaImpresion detalleElegido;

    @Override
    public AbstractDAO<FormularioHechauka> getEjb() {
        return ejb;
    }

    @Override
    public FormularioHechauka getNuevo() {
        return new LibroCompraHechauka();
    }

    @Override
    public String create() {
        FormularioHechauka f = ejb.creaFormularioHechauka(new Periodo(Integer.parseInt(anio), Integer.parseInt(mes)), cotizacionSet);
        lista = new ArrayList<FormularioHechaukaImpresion>();

        lista.add(new FormularioHechaukaImpresion(f,0));

        int indice = 1;
        for (DetFormularioHechauka d : f.getDetalles()) {
            lista.add(new FormularioHechaukaImpresion(d,indice));
            indice++;
        }
        //JsfUtil.addSuccessMessage("Se creó exitosamente!");

        //setActual(null);
        return null;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Double getCotizacionSet() {
        return cotizacionSet;
    }

    public void setCotizacionSet(Double cotizacionSet) {
        this.cotizacionSet = cotizacionSet;
    }

    public List<FormularioHechaukaImpresion> getLista() {
        return lista;
    }

    public void setLista(List<FormularioHechaukaImpresion> lista) {
        this.lista = lista;
    }

    public void postProcessXLS(Object document) {
        HSSFWorkbook wb = (HSSFWorkbook) document;
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow header = sheet.getRow(0);
        sheet.removeRow(header);
        

//    HSSFCellStyle cellStyle = wb.createCellStyle();    
//    cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);  
//    cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//      
//    for(int i=0; i < header.getPhysicalNumberOfCells();i++) {  
//        HSSFCell cell = header.getCell(i);  
//          
//        cell.setCellStyle(cellStyle);  
//    }  
    }

    public FormularioHechaukaImpresion getDetalleElegido() {
        return detalleElegido;
    }

    public void setDetalleElegido(FormularioHechaukaImpresion detalleElegido) {
        this.detalleElegido = detalleElegido;
    }
    
    

  
    
}
