/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.persistencia.eventos.cliente;

import py.gestion.clientes.persistencia.Cliente;

/**
 *
 * @author cromero
 */
public class ClienteEventoCreacion {

    private String mensaje;
    private Cliente cliente;

    public ClienteEventoCreacion(String mensaje, Cliente cliente) {
        this.mensaje = mensaje;
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
