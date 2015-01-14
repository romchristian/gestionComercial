/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.servicios;

import py.gestion.seguridad.persistencia.Accion;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.utils.servicios.ABMService;
import static py.gestion.utils.servicios.QueryParameter.where;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AccionDAO {

    @EJB(beanName="ABMServiceBean")
    private ABMService abmService;

    public Accion create(Accion a) {
        return abmService.create(a);
    }

    public Accion edit(Accion a) {
        return abmService.update(a);
    }

    public Accion find(Object id) {
        return abmService.find(id, Accion.class);
    }

    public Accion find(String nombre) {
        return abmService.findByNamedQuerySingleResult(Accion.POR_NOMBRE,
                where("nombre", nombre).
                parameters());
    }

    public List<Accion> findAll() {
        return abmService.findByNamedQuery(Accion.TODOS);
    }

    public Accion findInsert(String nombre) {
        Accion R = null;
        if (nombre != null && !nombre.isEmpty()) {
            R = find(nombre);
            if (R == null) {
                R = new Accion(nombre);
                create(R);
            }
            System.out.println(R);
        }
        return R;
    }
}
