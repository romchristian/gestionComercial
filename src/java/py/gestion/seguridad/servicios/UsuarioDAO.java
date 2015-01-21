/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.servicios;

import py.gestion.seguridad.persistencia.Usuario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
//import py.gestion.adm.persistencia.validadores.ValidadorCedula;
import py.gestion.utils.servicios.excepciones.DocumentoDuplicadoExcepction;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;
import py.gestion.utils.web.JsfUtil;
import static py.gestion.utils.servicios.QueryParameter.*;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UsuarioDAO extends AbstractDAO<Usuario> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @Inject
    private Credencial credencial;
//    @Inject
//    private ValidadorCedula validadorDocumento;

    @Override
    public Usuario create(Usuario entity) {
        Usuario R = null;
//        try {

            entity.setEmpresa(credencial.getEmpresa());
            entity.setSucursal(credencial.getSucursal());
//            if (validadorDocumento.hayDocumento(entity)) {
//                throw new DocumentoDuplicadoExcepction(entity);
//            }

            R = abmService.create(entity);

//        } catch (DocumentoDuplicadoExcepction e) {
//            JsfUtil.addErrorMessage(e.getMessage());
//        }
        return R;
    }

    public Usuario find() {
        Usuario R = null;
        if (credencial.getUsuario() != null) {
            System.out.println("usuario: " + credencial.getUsuario().getUsuario());

            R = (Usuario) abmService.findByNamedQuerySingleResult(Usuario.POR_CREDENCIAL,
                    where("usuario", credencial.getUsuario().getUsuario()).parameters());
        }
        return R;
    }

    public Usuario find(String nombre) {
        Usuario R;

        R = (Usuario) abmService.findByNamedQuerySingleResult(Usuario.POR_CREDENCIAL,
                where("usuario", nombre).parameters());

        return R;
    }

    @Override
    public Usuario edit(Usuario entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(Usuario entity) {
        abmService.delete(entity);
    }

    @Override
    public Usuario find(Object id) {
        return abmService.find(id, Usuario.class);
    }

    @Override
    public List<Usuario> findAll() {
        return abmService.findByNamedQuery(Usuario.TODOS);
    }

    @Override
    public List<Usuario> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
