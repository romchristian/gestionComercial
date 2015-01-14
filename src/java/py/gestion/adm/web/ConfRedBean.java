/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.adm.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import py.gestion.adm.servicios.ConfRedDAO;
import py.gestion.adm.persistencia.ConfRed;

/**
 *
 * @author christian
 */
@Named
public class ConfRedBean implements Serializable {

    @EJB
    private ConfRedDAO confRedDAO;
    private ConfRed confRed;

    public ConfRed getConfRed() {
        if (confRed == null) {
            confRed = confRedDAO.findPorNombre("confproxy");
        }
        return confRed;
    }

    public void setConfRed(ConfRed confRed) {
        this.confRed = confRed;
    }

    public String edit() {
        confRedDAO.edit(confRed);
        return "/home.xhtml";
    }

    public void siCambiaCheck() {
        
    }
}
