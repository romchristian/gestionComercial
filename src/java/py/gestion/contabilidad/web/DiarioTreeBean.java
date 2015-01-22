/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.contabilidad.web;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.component.tree.Tree;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.gestion.contabilidad.persistencia.Diario;
import py.gestion.contabilidad.persistencia.TipoDiario;
import py.gestion.contabilidad.servicio.DiarioDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class DiarioTreeBean extends BeanGenerico<Diario> {

    @EJB
    private DiarioDAO ejb;

    private TreeNode rootNode;
    
    @Override
    public String cargaDatos() {
        List<Diario> roots = ejb.findAllRoots();
        rootNode = new DefaultTreeNode(new Diario("Diarios", TipoDiario.VENTA), null);
        for (Diario d : roots) {
            TreeNode t1 = createTree(d, rootNode);
        }
        return null;
    }

    public TreeNode createTree(Diario treeObj, TreeNode rootNode) {
        TreeNode newNode = new DefaultTreeNode(treeObj, rootNode);

        List<Diario> childNodes1 = ejb.findAllChildren(treeObj);

        for (Diario tt : childNodes1) {
            TreeNode newNode2 = createTree(tt, newNode);
        }

        return newNode;
    }

    @Override
    public AbstractDAO<Diario> getEjb() {
        return ejb;
    }

    @Override
    public Diario getNuevo() {
        return new Diario();
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    
    
}
