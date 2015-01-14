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
import py.gestion.bancos.servicios.CuentaBancariaDAO;
import py.gestion.bancos.persistencia.CuentaBancaria;

/**
 *
 * @author christian
 */
@ManagedBean
@RequestScoped
public class CuentaBancariaBean extends BeanGenerico<CuentaBancaria>{

    @EJB private CuentaBancariaDAO ejb;
    
    
    @Override
    public AbstractDAO<CuentaBancaria> getEjb() {
        return ejb;
    }

    @Override
    public CuentaBancaria getNuevo() {
        return new CuentaBancaria();
    }

    
}
