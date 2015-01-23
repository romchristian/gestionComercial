/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.servicios.ProductoDAO;

/**
 *
 * @author Christian
 */
@Named
@RequestScoped
public class ProductoImageBean implements Serializable {

    @EJB
    private ProductoDAO ejb;
   
   
    public StreamedContent obtImageStreamedById() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        StreamedContent defaultFileContent = new DefaultStreamedContent();

        // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
        String productoId = context.getExternalContext().getRequestParameterMap().get("productoId");
        
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return defaultFileContent;
        } else if (productoId == null || productoId.equals("")) {
            return defaultFileContent;
        } else {
            System.out.println("Hola 1");
            Long id = Long.parseLong(productoId);
            if (id > 0) {
                System.out.println("Hola 2");
                Producto p = ejb.find(id);
                if (p.getImagen() == null) {
                    System.out.println("Hola 3");
                    return defaultFileContent;
                } else {
                    System.out.println("Hola 4");
                    return new DefaultStreamedContent(new ByteArrayInputStream(p.getImagen()));
                }
            } else {
                System.out.println("Hola 5");
                return defaultFileContent;
            }
        }

    }

}
