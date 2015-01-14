/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.proveedores.web;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.utils.web.jsf.componentes.DiccionarioProveedor;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.proveedores.servicios.ProveedorService;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;

/**
 *
 * @author christian
 */
@Named
@SessionScoped
public class ProveedorBean extends BeanGenerico<Proveedor> {

    @EJB
    private ProveedorService ejb;
    @Inject
    private DiccionarioProveedor dicProveedor;
    private Long timbrado;
    private String cosEst;
    private String cosSuc;
    private Date vigencia;

    @Override
    public AbstractDAO<Proveedor> getEjb() {
        return ejb;
    }

    @Override
    public Proveedor getNuevo() {
        return new Proveedor();
    }

    @Override
    public String create() {
        String R = super.create();
        dicProveedor.carga();
        return R;
    }

    public Date getVigencia() {
        return vigencia;
    }

    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }

    public Long getTimbrado() {
        return timbrado;
    }

    public void setTimbrado(Long timbrado) {
        this.timbrado = timbrado;
    }

    public String getCosEst() {
        return cosEst;
    }

    public void setCosEst(String cosEst) {
        this.cosEst = cosEst;
    }

    public String getCosSuc() {
        return cosSuc;
    }

    public void setCosSuc(String cosSuc) {
        this.cosSuc = cosSuc;
    }

    public String agregaTimbrado() {
        if (getActual().getTimbrados() == null) {
            getActual().setTimbrados(new ArrayList<ProveedorTimbrado>());
        }
        ProveedorTimbrado t = new ProveedorTimbrado();
        t.setProveedor(getActual());
        t.setTimbrado(timbrado);
        t.setCodEst(cosEst);
        t.setCodSuc(cosSuc);
        t.setVigencia(vigencia);
        getActual().getTimbrados().add(t);
        return null;
    }

    public void quitaTimbrado(ProveedorTimbrado t) {
        getActual().getTimbrados().remove(t);
    }
}
