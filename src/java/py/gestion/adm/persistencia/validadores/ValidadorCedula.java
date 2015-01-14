/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia.validadores;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.Sucursal;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.clientes.persistencia.enums.TipoDocumento;
import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.seguridad.web.Credencial;

/**
 *
 * @author christian
 */
@Named
@Stateless
public class ValidadorCedula implements Serializable {

    @PersistenceContext(unitName = "SYSCVSAPU")
    private EntityManager em;
    @Inject
    Credencial credencial;

    public void validateCedula(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (hayDocumento((String) value, TipoDocumento.CI, credencial.getEmpresa(), credencial.getSucursal())) {

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"La cédula " + ((String) value) + " ya existe!",
                    "La cédula " + ((String) value) + " ya existe!");
            
            throw new ValidatorException(message);
        }
    }

    public boolean hayDocumento(String nro, TipoDocumento tipoDocumento, Empresa empresa, Sucursal sucursal) {

        boolean R = false;
        try {

            Cliente c = (Cliente) em.createQuery("select c from Cliente c where c.nroDocumento = ?1 and c.tipoDocumento = ?2 "
                    + " and c.empresa = ?3 and c.sucursal = ?4").
                    setParameter(1, nro).
                    setParameter(2, tipoDocumento).
                    setParameter(3, empresa).
                    setParameter(4, sucursal).
                    getSingleResult();
            R = true;
        } catch (Exception e) {
        }
        return R;
    }

    public boolean hayDocumento(Cliente cliente) {
        return hayDocumento(cliente.getNroDocumento(), cliente.getTipoDocumento(), cliente.getEmpresa(), cliente.getSucursal());
    }

    public boolean hayDocumento(Usuario usuario) {
        boolean R = false;
        try {

            Usuario u = (Usuario) em.createQuery("select u from Usuario u where u.nroDocumento = ?1 and u.tipoDocumento = ?2").
                    setParameter(1, usuario.getNroDocumento()).
                    setParameter(2, usuario.getTipoDocumento()).
                    getSingleResult();
            R = true;
        } catch (Exception e) {
        }
        return R;
    }
}
