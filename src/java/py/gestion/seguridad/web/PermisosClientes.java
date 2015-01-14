/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class PermisosClientes implements Serializable {

    private TreeNode root;
    private TreeNode[] selectedNodes;

    public PermisosClientes() {
    }

    @PostConstruct
    private void init() {
        creaArbolPermisos();
    }

    public TreeNode getRoot() {
        return root;
    }

    public TreeNode[] getSelectedNodes() {
        return selectedNodes;
    }

    public void setSelectedNodes(TreeNode[] selectedNodes) {
        this.selectedNodes = selectedNodes;
    }

    
    private void creaArbolPermisos() {
        root = new DefaultTreeNode("root", null);
        TreeNode node0 = new DefaultTreeNode("", root);
        node0.setExpanded(true);



        TreeNode node00 = new DefaultTreeNode("General", node0);
        TreeNode node01 = new DefaultTreeNode("Seguridad", node0);
        TreeNode node02 = new DefaultTreeNode("Parametros", node0);

        TreeNode node001 = new DefaultTreeNode("Empresa", node00);
        TreeNode node002 = new DefaultTreeNode("Sucursal", node00);
        TreeNode node003 = new DefaultTreeNode("Obra", node00);
        TreeNode node004 = new DefaultTreeNode("Moneda", node00);
        TreeNode node005 = new DefaultTreeNode("Cotización", node00);

        TreeNode node011 = new DefaultTreeNode("Usuario", node01);
        TreeNode node012 = new DefaultTreeNode("Rol", node01);



        TreeNode node0011 = new DefaultTreeNode("VisualizaEmpresa", node001);
        TreeNode node0012 = new DefaultTreeNode("CreaEmpresa", node001);
        TreeNode node0013 = new DefaultTreeNode("EditaEmpresa", node001);

        TreeNode node0021 = new DefaultTreeNode("VisualizaSucursal", node002);
        TreeNode node0022 = new DefaultTreeNode("CreaSucursal", node002);
        TreeNode node0023 = new DefaultTreeNode("EditaSucursal", node002);

        TreeNode node0031 = new DefaultTreeNode("VisualizaObra", node003);
        TreeNode node0032 = new DefaultTreeNode("CreaObra", node003);
        TreeNode node0033 = new DefaultTreeNode("EditaObra", node003);

        TreeNode node0041 = new DefaultTreeNode("VisualizaMoneda", node004);
        TreeNode node0042 = new DefaultTreeNode("CreaMoneda", node004);
        TreeNode node0043 = new DefaultTreeNode("EditaMoneda", node004);

        TreeNode node0051 = new DefaultTreeNode("VisualizaCotizacion", node005);
        TreeNode node0052 = new DefaultTreeNode("CreaCotizacion", node005);
        TreeNode node0053 = new DefaultTreeNode("EditaCotizacion", node005);


        TreeNode node0111 = new DefaultTreeNode("VisualizaUsuario", node011);
        TreeNode node0112 = new DefaultTreeNode("CreaUsuario", node011);
        TreeNode node0113 = new DefaultTreeNode("EditaUsuario", node011);

        TreeNode node0121 = new DefaultTreeNode("VisualizaRol", node012);
        TreeNode node0122 = new DefaultTreeNode("CreaRol", node012);
        TreeNode node0123 = new DefaultTreeNode("EditaRol", node012);

        TreeNode node021 = new DefaultTreeNode("Red", node02);

    }
}
