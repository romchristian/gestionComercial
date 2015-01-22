/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.gestion.stock.persistencia.Familia;
import py.gestion.stock.servicios.FamiliaDAO;
import py.gestion.utils.web.BeanGenerico;
import py.gestion.utils.servicios.AbstractDAO;

/**
 *
 * @author christian
 */
@Named
@ViewScoped
public class FamiliaTreeBean extends BeanGenerico<Familia> {

    @EJB
    private FamiliaDAO ejb;

    private TreeNode rootNode;
    
    @Override
    public String cargaDatos() {
        List<Familia> roots = ejb.findAllRoots();
        rootNode = new DefaultTreeNode(new Familia("Familias", ""), null);
        for (Familia f : roots) {
            TreeNode t1 = createTree(f, rootNode);
        }
        return null;
    }

    public TreeNode createTree(Familia treeObj, TreeNode rootNode) {
        TreeNode newNode = new DefaultTreeNode(treeObj, rootNode);

        List<Familia> childNodes1 = ejb.findAllChildren(treeObj);

        for (Familia tt : childNodes1) {
            TreeNode newNode2 = createTree(tt, newNode);
        }

        return newNode;
    }

    @Override
    public AbstractDAO<Familia> getEjb() {
        return ejb;
    }

    @Override
    public Familia getNuevo() {
        return new Familia();
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }

    
    
}
