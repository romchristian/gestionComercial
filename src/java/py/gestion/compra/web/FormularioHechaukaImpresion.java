/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.web;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import py.gestion.compra.persistencia.DetFormularioHechauka;
import py.gestion.compra.persistencia.FormularioHechauka;

/**
 *
 * @author christian
 */
public class FormularioHechaukaImpresion implements Serializable {

    private int indice;
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String col8;
    private String col9;
    private String col10;
    private String col11;
    private String col12;
    private String col13;
    private String col14;

    public FormularioHechaukaImpresion(FormularioHechauka c, int indice) {
        col1 = c.getTipoReg() + "";
        col2 = c.getPeriodoNombre();
        col3 = c.getTipoDDJJ() + "";
        col4 = c.getTipoInf() + "";
        col5 = c.getFormulario() + "";
        col6 = c.getAgenteInf();
        col7 = c.getDvAgenteInf() + "";
        col8 = c.getNombreAgenteInf();
        col9 = c.getRucRep();
        col10 = c.getDvRep() + "";
        col11 = c.getNombreRep();
        col12 = c.getCantReg() + "";
        col13 = c.getMontoTotal() + "";
        col14 = c.getExportador().toString();
        this.indice = indice;
    }

    public FormularioHechaukaImpresion(DetFormularioHechauka d,int indice) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        col1 = d.getTipo() + "";
        col2 = d.getRucProveedor();
        col3 = d.getDvProveedor() + "";
        col4 = d.getNombreProveedor();
        col5 = d.getTimbradoProveedor() + "";
        col6 = d.getTipoDocumento() + "";
        col7 = d.getNumeroDocumento();
        col8 = sdf.format(d.getFechaDocumento());
        col9 = d.getMontoTasa10() + "";
        col10 = d.getIva10() + "";
        col11 = d.getMontoTasa05() + "";
        col12 = d.getIva05() + "";
        col13 = d.getMontoExenta() + "";
        col14 = d.getTipoOperacion() + "";
        this.indice = indice;

    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    
    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getCol8() {
        return col8;
    }

    public void setCol8(String col8) {
        this.col8 = col8;
    }

    public String getCol9() {
        return col9;
    }

    public void setCol9(String col9) {
        this.col9 = col9;
    }

    public String getCol10() {
        return col10;
    }

    public void setCol10(String col10) {
        this.col10 = col10;
    }

    public String getCol11() {
        return col11;
    }

    public void setCol11(String col11) {
        this.col11 = col11;
    }

    public String getCol12() {
        return col12;
    }

    public void setCol12(String col12) {
        this.col12 = col12;
    }

    public String getCol13() {
        return col13;
    }

    public void setCol13(String col13) {
        this.col13 = col13;
    }

    public String getCol14() {
        return col14;
    }

    public void setCol14(String col14) {
        this.col14 = col14;
    }
}
