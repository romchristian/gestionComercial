/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.persistencia.eventos.proveedor;

import py.gestion.proveedores.persistencia.Proveedor;

/**
 *
 * @author cromero
 */
public class ProveedorEvento {

    private String mensaje;
    private Proveedor proveedor;

    public ProveedorEvento(String mensaje, Proveedor proveedor) {
        this.mensaje = mensaje;
        this.proveedor = proveedor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
}
