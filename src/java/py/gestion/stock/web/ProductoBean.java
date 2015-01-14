/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import py.gestion.utils.web.jsf.componentes.CompDetFamilia;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.stock.persistencia.Producto;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.web.BeanGenerico;

/**
 *
 * @author christian
 */
@Named
@ConversationScoped
public class ProductoBean extends BeanGenerico<Producto> implements Serializable {

    @EJB
    private ProductoDAO ejb;
    @Inject
    private CompDetFamilia compDetFamilia;
    @Inject
    private Conversation conversation;
    
    @PostConstruct
    private void init(){
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    @Override
    public AbstractDAO<Producto> getEjb() {
        return ejb;
    }

    @Override
    public Producto getNuevo() {
        return new Producto();
    }

    @Override
    public String create() {
        getActual().setFamilias(compDetFamilia.getFamilias());
        String R = super.create();
        compDetFamilia.inicializa();
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return R;
    }

    @Override
    public String edit() {
        getActual().setFamilias(compDetFamilia.getFamilias());
        String R = super.edit();
        compDetFamilia.inicializa();
        if (!conversation.isTransient()) {
            conversation.end();
        }
        return R;
    }

    @Override
    public String preparaEdicion(Producto obj) {
        compDetFamilia.inicializa();
        return super.preparaEdicion(obj);
    }

    @Override
    public Producto getActual() {
        

        Producto R = super.getActual();
        if (R.getId() != null && R.getId() > 0) {
            if (compDetFamilia.getFamilias().isEmpty() && R.getFamilias() != null && !R.getFamilias().isEmpty()) {
                compDetFamilia.setFamilias(R.getFamilias());
            }
        }
        return R;
    }
}
