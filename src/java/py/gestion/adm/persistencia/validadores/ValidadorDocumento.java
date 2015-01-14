/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.persistencia.validadores;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.gestion.clientes.persistencia.Cliente;
import py.gestion.seguridad.persistencia.Usuario;

/**
 *
 * @author christian
 */
@Named
@Stateless
public class ValidadorDocumento implements Serializable{
    @PersistenceContext(unitName = "SYSCVSAPU")
    private EntityManager em;
    
    public boolean hayDocumento(Cliente cliente) {
        boolean R = false;
        try {
            
             Cliente c = (Cliente) em.createQuery("select c from Cliente c where c.nroDocumento = ?1 and c.tipoDocumento = ?2 "
                    + " and c.empresa = ?3 and c.sucursal = ?4").
                    setParameter(1, cliente.getNroDocumento()).
                    setParameter(2, cliente.getTipoDocumento()).
                    setParameter(3, cliente.getEmpresa()).
                    setParameter(4, cliente.getSucursal()).
                    getSingleResult();
            R = true;
        } catch (Exception e) {
            
        }
        return R;
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
