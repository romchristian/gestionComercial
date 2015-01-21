/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.contabilidad.web;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import py.gestion.contabilidad.persistencia.Diario;

/**
 *
 * @author Acer
 */
public class DiarioTreeNode extends DefaultTreeNode {

    public DiarioTreeNode() {
    }

    public DiarioTreeNode(Object data) {
        super(data);
    }

    public DiarioTreeNode(Object data, TreeNode parent) {
        super(data, parent);
    }

    public DiarioTreeNode(String type, Object data, TreeNode parent) {
        super(type, data, parent);
    }

    
    
    @Override
    public Diario getData() {
        return (Diario) super.getData();
    }

}
