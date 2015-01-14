/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.persistencia.Consulta;
import py.gestion.utils.persistencia.Filtro;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ConsultaDAO extends AbstractDAO<Consulta> {

    @Inject
    private Credencial credencial;
    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    @Override
    public Consulta create(Consulta entity) {
        entity.setUsuario(credencial.getUsuario());
        return abmService.create(entity);
    }

    @Override
    public Consulta edit(Consulta entity) {
        Consulta nuevo = new Consulta();
        entity.setUsuario(credencial.getUsuario());

        if (entity.getId() != null) {
            Consulta c = abmService.getEM().find(Consulta.class, entity.getId());
            if (!c.getNombre().equalsIgnoreCase(entity.getNombre())) {
                nuevo.setNombre(entity.getNombre());
                nuevo.setNombreClase(entity.getNombreClase());
                nuevo.setUsuario(credencial.getUsuario());
                nuevo.setFiltros(new ArrayList<Filtro>());

                for (Filtro f : entity.getFiltros()) {
                    Filtro fn = f.getNuevo();
                    fn.setConsulta(nuevo);
                    nuevo.getFiltros().add(fn);
                }


                entity = nuevo;
            }
        }

        List<Filtro> nuevos = new ArrayList<Filtro>();
        for (Filtro f : entity.getFiltros()) {
            try {
                Filtro fe = (Filtro) abmService.getEM()
                        .createQuery("SELECT f from Filtro f where f.consulta = :consulta and f.nombre = :nombre")
                        .setParameter("consulta", entity)
                        .setParameter("nombre", f.getNombre()).getSingleResult();
                f.setId(fe.getId());
                nuevos.add(f);
            } catch (Exception e) {
                nuevos.add(f);
            }
        }
        entity.getFiltros().clear();
        entity.getFiltros().addAll(nuevos);
        return abmService.update(entity);
    }

    @Override
    public void remove(Consulta entity) {
        abmService.delete(entity);
    }

    @Override
    public Consulta find(Object id) {
        return abmService.find(id, Consulta.class);
    }

    @Override
    public List<Consulta> findAll() {
        return abmService.findByNamedQuery(Consulta.TODOS);
    }

    public List<Consulta> findAll(String nombreClase) {
        return abmService.findByNamedQuery(Consulta.POR_CLASE, QueryParameter.where("nombreClase", nombreClase).and("usuario", credencial.getUsuario()).parameters());
    }

    @Override
    public List<Consulta> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
    
    public Double findTotal(String consulta, QueryParameter params){
        return abmService.findTotal(consulta, params.parameters());
    }
}
