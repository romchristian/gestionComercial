/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import py.gestion.adm.persistencia.Empresa;
import py.gestion.compra.persistencia.DetFormularioHechauka;
import py.gestion.compra.persistencia.DetLibroCompraFactura;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.FormularioHechauka;
import py.gestion.compra.persistencia.LibroCompraHechauka;
import py.gestion.compra.persistencia.Periodo;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.seguridad.web.Credencial;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class FormularioHechaukaDAO extends AbstractDAO<FormularioHechauka> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;
    @EJB(beanName = "ABMServiceDiestra")
    private ABMService abmServiceDiestra;
    @Inject
    private Credencial credencial;

    @Override
    public FormularioHechauka create(FormularioHechauka entity) {
        return abmService.create(entity);
    }

    @Override
    public FormularioHechauka edit(FormularioHechauka entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(FormularioHechauka entity) {
        abmService.delete(entity);
    }

    @Override
    public FormularioHechauka find(Object id) {
        return abmService.find(id, FormularioHechauka.class);
    }

    @Override
    public List<FormularioHechauka> findAll() {
        return abmService.findByNamedQuery(FormularioHechauka.TODOS);
    }

    public FormularioHechauka creaFormularioHechauka(Periodo periodo, Double cotizacionSet) {
        return creaFormularioHechauka(periodo, credencial.getEmpresa(), credencial.getUsuario(), true);
    }

    public FormularioHechauka creaFormularioHechauka(Periodo periodo, Empresa empresa, Usuario usuario, boolean desdeDiestra) {
        //abmService.getEM().persist(periodo);

        FormularioHechauka R = new LibroCompraHechauka(periodo, empresa);
        R.setUsuarioResponsable(usuario);

        List<DetFormularioHechauka> detalles;
        if (desdeDiestra) {
            detalles = null;
        } else {
            detalles = creaDetLibroCompraFactura(periodo, R);
        }

        R.setDetalles(detalles);
        BigDecimal montoTotal = new BigDecimal(0d).setScale(0, RoundingMode.HALF_EVEN);

        for (DetFormularioHechauka d : detalles) {
            montoTotal = montoTotal.add((d.getMontoExenta().add(d.getMontoTasa05().add(d.getMontoTasa10()))));
        }
        R.setMontoTotal(montoTotal);
        R.setCantReg(detalles.size());
        //create(R);
        return R;
    }

    public List<DetFormularioHechauka> creaDetLibroCompraFactura(Periodo periodo, FormularioHechauka cabecera) {
        List<FacturaProveedor> lista = abmService
                .findByQuery("select fp from FacturaProveedor fp where "
                + " (fp.estado = :estado1 or fp.estado = :estado2 or fp.estado = :estado3) "
                + " and fp.emision between :inicio and :fin",
                QueryParameter.where("estado1", EstadoFacturaProveedor.CONFORMADA)
                .and("estado2", EstadoFacturaProveedor.CANCELADA)
                .and("estado3", EstadoFacturaProveedor.PAGADA_PARCIALMENTE)
                .and("inicio", periodo.getInicio())
                .and("fin", periodo.getFin())
                .parameters());

        List<DetFormularioHechauka> R = new ArrayList<DetFormularioHechauka>();
        for (FacturaProveedor f : lista) {
            R.add(new DetLibroCompraFactura(f, cabecera));
        }
        return R;
    }

   

    @Override
    public List<FormularioHechauka> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
