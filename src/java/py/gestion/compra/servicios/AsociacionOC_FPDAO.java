/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.compra.servicios;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import py.gestion.compra.persistencia.AsociacionOC_FP;
import py.gestion.compra.persistencia.DetFacturaProveedor;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.DetRemisionProveedor;
import py.gestion.utils.servicios.ABMService;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class AsociacionOC_FPDAO extends AbstractDAO<AsociacionOC_FP> {

    @EJB(beanName = "ABMServiceBean")
    private ABMService abmService;

    public void asociaOC_FP(DetOrdenCompra doc, DetFacturaProveedor dfp) {
        AsociacionOC_FP a = new AsociacionOC_FP(doc, dfp);
        create(a);
    }

    public void asociaOC_RE(DetOrdenCompra doc, DetRemisionProveedor drp) {
        AsociacionOC_FP a = new AsociacionOC_FP(doc, drp);
        create(a);
    }

    public void asociaFP_RE(DetFacturaProveedor dfp, DetRemisionProveedor drp) {
        AsociacionOC_FP a = new AsociacionOC_FP(dfp, drp);
        create(a);
    }

    public void asociaA_OC(AsociacionOC_FP a, DetOrdenCompra doc) {
        //cargar a con dfp
        a.setDetOrdenCompra(doc);
        edit(a);
    }

    public void asociaA_FP(AsociacionOC_FP a, DetFacturaProveedor dfp) {
        //cargar a con dfp
        a.setDetFacturaProveedor(dfp);
        edit(a);
    }

    public void asociaA_RE(AsociacionOC_FP a, DetRemisionProveedor drp) {
        //cargar a con dfp
        a.setDetRemisionProveedor(drp);
        edit(a);
    }

    @Override
    public AsociacionOC_FP create(AsociacionOC_FP entity) {
        abmService.create(entity);
        return entity;
    }

    @Override
    public AsociacionOC_FP edit(AsociacionOC_FP entity) {
        return abmService.update(entity);
    }

    @Override
    public void remove(AsociacionOC_FP entity) {
        abmService.delete(entity);
    }

    @Override
    public AsociacionOC_FP find(Object id) {
        return abmService.find(id, AsociacionOC_FP.class);
    }

    @Override
    public List<AsociacionOC_FP> findAll() {
        return abmService.findByNamedQuery(AsociacionOC_FP.TODOS);
    }

    @Override
    public List<AsociacionOC_FP> findAll(String query, QueryParameter params) {
        return abmService.findByQuery(query, params.parameters());
    }
}
