/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.persistencia.enums;

/**
 *
 * @author cromero
 */
public enum EstadoOC {

    PENDIENTE_AUTORIZACION("Pendiente de Autorización", "red", false, true, false, true),
    AUTORIZADO("Autorizado", "green", true, false, true, true),
    EN_PROCESO("En proceso", "blue", true, false, false, true),
    CERRADA("Cerrada", "black", false, false, false, false);
    private String color;
    private boolean renderPendiente;
    private boolean renderAutorizado;
    private boolean renderEnProceso;
    private boolean renderCerrada;
    private boolean cerrada;
    private boolean pendiente;
    private String colorPediente;
    private String colorAutorizado;
    private String colorEnProceso;
    private String colorCerrada;
    private String descripcion;

    private EstadoOC(String descripcion, String color, boolean renderPendiente, boolean renderAutorizado, boolean renderEnProceso, boolean renderCerrada) {
        this.descripcion = descripcion;
        this.color = color;
        this.renderPendiente = renderPendiente;
        this.renderAutorizado = renderAutorizado;
        this.renderEnProceso = renderEnProceso;
        this.renderCerrada = renderCerrada;
        this.colorPediente = "black";
        this.colorAutorizado = "black";
        this.colorEnProceso = "black";
        this.colorCerrada = "black";
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getColor() {
        return color;
    }

    public boolean isRenderAutorizado() {
        return renderAutorizado;
    }

    public boolean isRenderCerrada() {
        return renderCerrada;
    }

    public boolean isRenderEnProceso() {
        return renderEnProceso;
    }

    public boolean isRenderPendiente() {
        return renderPendiente;
    }

    public boolean isCerrada() {
        if (renderAutorizado == false && renderPendiente == false && renderEnProceso == false && renderCerrada == false) {
            cerrada = true;
        } else {
            cerrada = false;
        }
        return cerrada;
    }

    public boolean isPendiente() {
        if (renderPendiente == false && renderAutorizado == true && renderEnProceso == false && renderCerrada == true) {
            pendiente = true;
        } else {
            pendiente = false;
        }
        return pendiente;
    }

    public String getColorAutorizado() {
        return colorAutorizado;
    }

    public String getColorCerrada() {
        return colorCerrada;
    }

    public String getColorEnProceso() {
        return colorEnProceso;
    }

    public String getColorPediente() {
        return colorPediente;
    }

    public void setColorPendiente(String color) {
        colorPediente = color;
    }
}
