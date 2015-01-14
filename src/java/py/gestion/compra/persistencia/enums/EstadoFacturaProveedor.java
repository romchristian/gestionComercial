/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.enums;

/**
 *
 * @author christian
 */
public enum EstadoFacturaProveedor {
    CREADA("Creada","red",false,true,false,false,true), 
    CONFORMADA ("Conformada","green",false,false,true,false,true), 
    PAGADA_PARCIALMENTE("Pagada Parcialmente","blue",false,false,true,false,true), 
    CANCELADA("Cancelada","black",false,false,false,false,false), 
    ANULADA("Anulada","black",false,false,false,false,false);

    private String color;
    private boolean renderCreada;
    private boolean renderConformada;
    private boolean renderPagadaParcialmente;
    private boolean renderCancelada;
    private boolean renderAnulada;
    private String colorCreada;
    private String colorConformada;
    private String colorPagadaParcialmente;
    private String colorCancelada;
    private String colorAnulada;
    private String descripcion;

    private EstadoFacturaProveedor(String descripcion,String color, boolean renderCreada, boolean renderConformada, boolean renderPagadaParcialmente, boolean renderCancelada, boolean renderAnulada) {
        this.color = color;
        this.renderCreada = renderCreada;
        this.renderConformada = renderConformada;
        this.renderPagadaParcialmente = renderPagadaParcialmente;
        this.renderCancelada = renderCancelada;
        this.renderAnulada = renderAnulada;
        this.descripcion = descripcion;
        this.colorCreada = "black";
        this.colorConformada = "black";
        this.colorPagadaParcialmente = "black";
        this.colorCancelada = "black";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRenderCreada() {
        return renderCreada;
    }

    public void setRenderCreada(boolean renderCreada) {
        this.renderCreada = renderCreada;
    }

    public boolean isRenderConformada() {
        return renderConformada;
    }

    public void setRenderConformada(boolean renderConformada) {
        this.renderConformada = renderConformada;
    }

    public boolean isRenderPagadaParcialmente() {
        return renderPagadaParcialmente;
    }

    public void setRenderPagadaParcialmente(boolean renderPagadaParcialmente) {
        this.renderPagadaParcialmente = renderPagadaParcialmente;
    }

    public boolean isRenderCancelada() {
        return renderCancelada;
    }

    public void setRenderCancelada(boolean renderCancelada) {
        this.renderCancelada = renderCancelada;
    }

    public boolean isRenderAnulada() {
        return renderAnulada;
    }

    public void setRenderAnulada(boolean renderAnulada) {
        this.renderAnulada = renderAnulada;
    }

    public String getColorCreada() {
        return colorCreada;
    }

    public void setColorCreada(String colorCreada) {
        this.colorCreada = colorCreada;
    }

    public String getColorConformada() {
        return colorConformada;
    }

    public void setColorConformada(String colorConformada) {
        this.colorConformada = colorConformada;
    }

    public String getColorPagadaParcialmente() {
        return colorPagadaParcialmente;
    }

    public void setColorPagadaParcialmente(String colorPagadaParcialmente) {
        this.colorPagadaParcialmente = colorPagadaParcialmente;
    }

    public String getColorCancelada() {
        return colorCancelada;
    }

    public void setColorCancelada(String colorCancelada) {
        this.colorCancelada = colorCancelada;
    }

    public String getColorAnulada() {
        return colorAnulada;
    }

    public void setColorAnulada(String colorAnulada) {
        this.colorAnulada = colorAnulada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
