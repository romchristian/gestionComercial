/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.SessionScoped;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
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
@SessionScoped
public class ProductoBean extends BeanGenerico<Producto> implements Serializable {

    @EJB
    private ProductoDAO ejb;
    @Inject
    private CompDetFamilia compDetFamilia;
    @Inject
    private Conversation conversation;

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        System.out.println("sssss");
        if (file != null) {
            try {
                System.out.println("HOLAA: " + file.getFileName());
                file.getInputstream();
                getActual().setImagen(readImageOldWay(file.getInputstream()));
            } catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
            }
        } else {

        }
    }

    public byte[] readImageOldWay(InputStream is) throws IOException {

        // Get the size of the file
        long length = file.getSize();
        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int) length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file " + file.getFileName());
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }

    public StreamedContent obtImageStreamed() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else if (getActual().getImagen() == null) {
            return new DefaultStreamedContent();
        } else {
            DefaultStreamedContent df = new DefaultStreamedContent(new ByteArrayInputStream(getActual().getImagen()));
           
            return df;
        }
    }
    
    public StreamedContent obtImageStreamedById() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the HTML. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
            // So, browser is requesting the image. Return a real StreamedContent with the image bytes.
            String productoId = context.getExternalContext().getRequestParameterMap().get("productoId");
            Producto p = ejb.find(Long.parseLong(productoId));
            return new DefaultStreamedContent(new ByteArrayInputStream(p.getImagen()));
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

        return R;
    }

    @Override
    public String edit() {
        getActual().setFamilias(compDetFamilia.getFamilias());
        String R = super.edit();
        compDetFamilia.inicializa();
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
