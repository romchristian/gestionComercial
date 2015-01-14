/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.bancos.web;


import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.bancos.servicios.BancoDAO;
import py.gestion.bancos.persistencia.Banco;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class BancoBean extends BeanGenerico<Banco>{

    @EJB private BancoDAO ejb;
    
    
    @Override
    public AbstractDAO<Banco> getEjb() {
        return ejb;
    }

    @Override
    public Banco getNuevo() {
        return new Banco();
    }

    
}
