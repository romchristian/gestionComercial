/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import py.gestion.adm.persistencia.Cotizacion;
import py.gestion.adm.web.ConfRedBean;

import py.gestion.adm.servicios.MonedaDAO;


/**
 *
 * @author cromero
 */
@Named
@SessionScoped
public class ServicioCotizacion implements Serializable {

    private boolean cargarDeInternet;
    private Cotizacion cotizacionDolar;
    @EJB
    private MonedaDAO monedaDAO;
    @Inject
    private ConfRedBean confRedBean;
    @Inject
    private InternetChecker internetChecker;

    @PostConstruct
    private void init() {
         cotizacionDolar = new Cotizacion();
//        cargarDeInternet = true;
//
//        configuraProxy();
//        if (internetChecker.isConnectionPresent()) {
//            cargaCotizacion();
//        }
    }

    public boolean isCargarDeInternet() {
        return cargarDeInternet;
    }

    public void setCargarDeInternet(boolean cargarDeInternet) {
        this.cargarDeInternet = cargarDeInternet;
    }

    public Cotizacion getCotizacionDolar() {
        return cotizacionDolar;
    }

    public void setCotizacionDolar(Cotizacion cotizacionDolar) {
        this.cotizacionDolar = cotizacionDolar;
    }

    private void cargaCotizacion() {
        InputStream in = null;
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();


            URL url = new URL("http://www.maxicambios.com.py/maxicambios.xml");
            in = url.openStream();
            Document doc = docBuilder.parse(in);
            in.close();

            doc.getDocumentElement().normalize();

            NodeList monedas = doc.getElementsByTagName("moneda");

            NumberFormat nf = NumberFormat.getInstance(new Locale("es", "py"));


            for (int s = 0; s < monedas.getLength(); s++) {

                Node moneda = monedas.item(s);

                if (moneda.getNodeType() == Node.ELEMENT_NODE) {


                    Element fstElmnt = (Element) moneda;
                    NodeList nombres = fstElmnt.getElementsByTagName("nombre");
                    Element nombre = (Element) nombres.item(0);
                    NodeList n = nombre.getChildNodes();
                    String nom = ((Node) n.item(0)).getNodeValue();
                    if (nom.equalsIgnoreCase("dólar")) {

                        NodeList compras = fstElmnt.getElementsByTagName("compra");
                        Element compra = (Element) compras.item(0);
                        NodeList c = compra.getChildNodes();
                        Double cmp = new Double(((Node) c.item(0)).getNodeValue());



                        NodeList ventas = fstElmnt.getElementsByTagName("venta");
                        Element venta = (Element) ventas.item(0);
                        NodeList v = venta.getChildNodes();
                        Double vta = new Double(((Node) v.item(0)).getNodeValue());

                      //  cotizacionDolar.setMoneda(monedaDAO.find("Dólar"));
                        cotizacionDolar.setCompra(cmp);
                        cotizacionDolar.setVenta(vta);

                        break;
                    }

                }
            }

        } catch (SAXException ex) {
            Logger.getLogger(ServicioCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServicioCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ServicioCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ServicioCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void configuraProxy() {
        System.setProperty("http.proxyHost", "172.16.8.11");
            System.setProperty("http.proxyPort", "880");
//        if (confRedBean.getConfRed().isTieneProxy()) {
//            System.setProperty("http.proxyHost", confRedBean.getConfRed().getServidor());
//            System.setProperty("http.proxyPort", confRedBean.getConfRed().getPuerto() + "");
            
//        }
    }
}
