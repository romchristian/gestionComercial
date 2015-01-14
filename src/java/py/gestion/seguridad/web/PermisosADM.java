/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.seguridad.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class PermisosADM implements Serializable {

    private TreeNode root;
    private TreeNode[] selectedNodes;
    private String[] seleccionados;
    private Map<String,String> mapa;

    public PermisosADM() {
    }

    @PostConstruct
    private void init() {
        creaArbolPermisos();
        cargaMapa();
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

    public String[] getSeleccionados() {
        if (selectedNodes != null && selectedNodes.length > 0) {
            seleccionados = new String[selectedNodes.length];

            for(int i = 0; i < selectedNodes.length ; i++){
                TreeNode node = selectedNodes[i];
                String accion = node.getData().toString(); 
                if(mapa.containsKey(accion)){
                    seleccionados[i] = mapa.get(accion);
                    System.out.println(mapa.get(accion));
                }
                
            }

      
        }
        return seleccionados;
    }

    private void creaArbolPermisos() {
        root = new DefaultTreeNode("root", null);
        TreeNode node0 = new DefaultTreeNode("Todos", root);
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



        TreeNode node0011 = new DefaultTreeNode("Visualiza Empresa", node001);
        TreeNode node0012 = new DefaultTreeNode("Crea Empresa", node001);
        TreeNode node0013 = new DefaultTreeNode("Edita Empresa", node001);

        TreeNode node0021 = new DefaultTreeNode("Visualiza Sucursal", node002);
        TreeNode node0022 = new DefaultTreeNode("Crea Sucursal", node002);
        TreeNode node0023 = new DefaultTreeNode("Edita Sucursal", node002);

        TreeNode node0031 = new DefaultTreeNode("Visualiza Obra", node003);
        TreeNode node0032 = new DefaultTreeNode("Crea Obra", node003);
        TreeNode node0033 = new DefaultTreeNode("Edita Obra", node003);

        TreeNode node0041 = new DefaultTreeNode("Visualiza Moneda", node004);
        TreeNode node0042 = new DefaultTreeNode("Crea Moneda", node004);
        TreeNode node0043 = new DefaultTreeNode("Edita Moneda", node004);

        TreeNode node0051 = new DefaultTreeNode("Visualiza Cotizacion", node005);
        TreeNode node0052 = new DefaultTreeNode("Crea Cotizacion", node005);
        TreeNode node0053 = new DefaultTreeNode("Edita Cotizacion", node005);


        TreeNode node0111 = new DefaultTreeNode("Visualiza Usuario", node011);
        TreeNode node0112 = new DefaultTreeNode("Crea Usuario", node011);
        TreeNode node0113 = new DefaultTreeNode("Edita Usuario", node011);

        TreeNode node0121 = new DefaultTreeNode("Visualiza Rol", node012);
        TreeNode node0122 = new DefaultTreeNode("Crea Rol", node012);
        TreeNode node0123 = new DefaultTreeNode("Edita Rol", node012);

        TreeNode node021 = new DefaultTreeNode("Red", node02);

    }

    private void cargaMapa() {
        mapa = new HashMap<String, String>();
        mapa.put("Visualiza Empresa", "VisualizaEmpresa");
        mapa.put("Edita Empresa", "EditaEmpresa");
        mapa.put("Crea Empresa", "CreaEmpresa");
        
        mapa.put("Visualiza Sucursal", "VisualizaSucursal");
        mapa.put("Edita Sucursal", "EditaSucursal");
        mapa.put("Crea Sucursal", "CreaSucursal");
        
        mapa.put("Visualiza Obra", "VisualizaObra");
        mapa.put("Edita Obra", "EditaObra");
        mapa.put("Crea Obra", "CreaObra");
        
        mapa.put("Visualiza Moneda", "VisualizaMoneda");
        mapa.put("Edita Moneda", "EditaMoneda");
        mapa.put("Crea Moneda", "CreaMoneda");
        
        mapa.put("Visualiza Cotizacion", "VisualizaCotizacion");
        mapa.put("Edita Cotizacion", "EditaCotizacion");
        mapa.put("Crea Cotizacion", "CreaCotizacion");
        
        mapa.put("Visualiza Usuario", "VisualizaUsuario");
        mapa.put("Edita Usuario", "EditaUsuario");
        mapa.put("Crea Usuario", "CreaUsuario");
        
        mapa.put("Visualiza Rol", "VisualizaRol");
        mapa.put("Edita Rol", "EditaRol");
        mapa.put("Crea Rol", "CreaRol");
        
        mapa.put("Red", "ConfRed");
    }
}
