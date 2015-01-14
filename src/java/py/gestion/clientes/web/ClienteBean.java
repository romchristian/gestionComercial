/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.web;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import py.gestion.adm.persistencia.Direccion;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.clientes.persistencia.ContactoTelefonico;
import py.gestion.clientes.servicios.ClienteDAO;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.web.jsf.componentes.CompContactoTelefonico;
import py.gestion.utils.web.jsf.componentes.CompDirecciones;

/**
 *
 * @author christian
 */
@ManagedBean
@SessionScoped
public class ClienteBean extends BeanGenerico<Cliente> {

    @EJB
    private ClienteDAO ejb;
    @Inject
    private CompDirecciones compDirecciones;
    @Inject
    private CompContactoTelefonico compContactoTelefonico;
    private Cliente actual;

    @Override
    public AbstractDAO<Cliente> getEjb() {
        return ejb;
    }

    @Override
    public Cliente getNuevo() {
        return new Cliente();
    }

    @Override
    public Cliente getActual() {
        if (actual == null) {
            actual = getNuevo();
        }
        cargaDetalles();
        return actual;

    }

    @Override
    public void setActual(Cliente actual) {
        this.actual = actual;
    }

    private void cargaDetalles() {
        if (actual != null && actual.getId() != null) {
            compContactoTelefonico.setLista(actual.getContactoTelefonicos());
            compDirecciones.setLista(actual.getDirecciones());
        } else {

            for (Direccion d : compDirecciones.getLista()) {
                d.setCliente(actual);
            }

            actual.setDirecciones(compDirecciones.getLista());

            for (ContactoTelefonico c : compContactoTelefonico.getLista()) {
                c.setCliente(actual);
            }

            actual.setContactoTelefonicos(compContactoTelefonico.getLista());
            
            compDirecciones.setDireccion(null);
            compContactoTelefonico.setLista(null);
        }
        setActual(actual);


    }
}
