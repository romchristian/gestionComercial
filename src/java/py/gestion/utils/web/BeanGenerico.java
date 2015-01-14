/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.web;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;
import py.gestion.utils.persistencia.ColumnModel;
import py.gestion.utils.persistencia.Columna;
import py.gestion.utils.persistencia.Consulta;
import py.gestion.utils.persistencia.Filtro;
import py.gestion.utils.servicios.AbstractDAO;
import py.gestion.utils.servicios.ColumnModelDAO;
import py.gestion.utils.servicios.ConsultaDAO;
import py.gestion.utils.servicios.QueryParameter;

/**
 *
 * @author christian
 */
public abstract class BeanGenerico<T> implements Serializable {

    @EJB
    private ColumnModelDAO columnModelDAO;
    private T actual;
    //Para el autocomplete
    private List<String> columnasElegidas = new ArrayList<String>();
    private List<ColumnModel> columnas = new ArrayList<ColumnModel>();
    private List<ColumnModel> columnasDisponibles = new ArrayList<ColumnModel>();
    private List<T> listado = new ArrayList<T>();
    @EJB
    private ConsultaDAO consultaDAO;
    public static final String OBJ = "obj";
    private List<Filtro> filtrosDisponibles;
    private List<Filtro> filtrosSeleccionados;
    private UIComponent dataGridDisponibles;
    private Consulta consultaActual;
    private Map<String, Double> mapTotales = new HashMap<String, Double>();
    private boolean muestraTotales = false;

    //Para el autocomplete
    public List<T> completar(String query) {
        List<T> sugerencias = new ArrayList<T>();

        for (T p : getEjb().findAll()) {
            if (p.toString().toUpperCase().startsWith(query.toUpperCase())) {
                sugerencias.add(p);
            }
        }
        

        return sugerencias;
    }

    //Para el autocomplete
    public void siCambiaAutocomplete(SelectEvent event) {
    }

    public List<String> getColumnasElegidas() {

        return columnasElegidas;
    }

    public void setColumnasElegidas(List<String> columnasElegidas) {
        this.columnasElegidas = columnasElegidas;
    }

    public void guardaColumnas() {
        List<ColumnModel> lista = columnModelDAO.findAll(getNuevo().getClass().getName());
        for (ColumnModel c : lista) {
            c.setVisible(false);
            columnModelDAO.edit(c);
        }
        for (ColumnModel c : lista) {
            for (String ce : columnasElegidas) {

                if (ce.equals(c.getHeader())) {
                    c.setVisible(true);
                    columnModelDAO.edit(c);
                }
            }
        }

    }

    public SelectItem[] getItemsColumnas() {
        if (columnasDisponibles.isEmpty()) {
            createDynamicColumns();
        }
        return JsfUtil.getSelectItems(columnasDisponibles, false);
    }

    public List<ColumnModel> getColumnas() {
        columnas = columnModelDAO.findAll(getNuevo().getClass().getName(), true);
        return columnas;
    }

    public void setColumnas(List<ColumnModel> columnas) {
        this.columnas = columnas;
    }

    public void createDynamicColumns() {
        columnasDisponibles = columnModelDAO.findAll(getNuevo().getClass().getName());
        if (columnasDisponibles == null || columnasDisponibles.isEmpty()) {
            columnasDisponibles.clear();
            int indice = 0;
            for (Field field : getNuevo().getClass().getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                String tipo = field.getType().getSimpleName();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Columna) {
                        Columna c = (Columna) annotation;
                        if (c.visible()) {
                            ColumnModel cm = new ColumnModel(c.cabecera(), c.propiedad(), false, getNuevo().getClass().getName(), tipo, "admin", indice);
                            cm.setTotalizable(c.totalizable());
                            cm = columnModelDAO.edit(cm);
                            columnasDisponibles.add(cm);
                        }
                    }
                }
                indice++;
            }
        }
    }

    public T getActual() {
        if (actual == null) {
            actual = getNuevo();
        }
        return actual;
    }

    public void setActual(T actual) {
        this.actual = actual;
    }

    public abstract AbstractDAO<T> getEjb();

    public abstract T getNuevo();

    public String create() {
        if (getEjb().create(getActual()) != null) {
            JsfUtil.addSuccessMessage("Se creó exitosamente!");
            setActual(null);
            return "listado.xhtml";
        } else {
            return null;
        }

    }

    public String edit() {
        if (getEjb().edit(getActual()) == null) {
            JsfUtil.addErrorMessage("Otro usuario realizó una modificación sobre el mismo dato,y pruebe de nuevo");
            return null;
        }

        JsfUtil.addSuccessMessage("Se guardó exitosamente!");
        setActual(null);
        return "listado.xhtml";
    }

    public String remove() {
        getEjb().remove(getActual());
        setActual(null);
        JsfUtil.addSuccessMessage("Se removió exitosamente!");
        return "listado.xhtml";
    }

    public T find(Object id) {
        return (T) getEjb().find(id);
    }

    public List<T> findAll() {
        return getEjb().findAll();
    }

    public List<T> findAllFiltros() {
        if (listado == null) {
            listado = new ArrayList<T>();
        }


        return listado;
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(findAll(), true);
    }

    public String preparaEdicion(T obj) {
        setActual(obj);
        return "edita.xhtml";
    }
    
    public String preparaCreacion() {
        setActual(null);
        return "nuevo.xhtml";
    }

    public String nuevo() {
        setActual(null);
        return "nuevo.xhtml";
    }

    public UIComponent getDataGridDisponibles() {
        return dataGridDisponibles;
    }

    public void setDataGridDisponibles(UIComponent dataGridDisponibles) {
        this.dataGridDisponibles = dataGridDisponibles;
    }

    public List<Filtro> getFiltrosDisponibles() {
        if (filtrosDisponibles == null) {
            filtrosDisponibles = new ArrayList<Filtro>();
            filtrosDisponibles.addAll(getFiltros());
        }
        return filtrosDisponibles;
    }

    public void setFiltrosDisponibles(List<Filtro> filtrosDisponibles) {
        this.filtrosDisponibles = filtrosDisponibles;
    }

    public List<Filtro> getFiltrosSeleccionados() {
        if (filtrosSeleccionados == null) {
            filtrosSeleccionados = new ArrayList<Filtro>();
        }

        return filtrosSeleccionados;
    }

    public void setFiltrosSeleccionados(List<Filtro> filtrosSeleccionados) {
        this.filtrosSeleccionados = filtrosSeleccionados;
    }

    public void onCarDrop(DragDropEvent ddEvent) {
        Filtro filtro = ((Filtro) ddEvent.getData());
        filtrosSeleccionados.add(filtro);
        filtrosDisponibles.remove(filtro);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute(filtro.getOpenDialog());
    }

    public void quitarFiltro(Filtro f) {
        filtrosDisponibles.add(f);
        filtrosSeleccionados.remove(f);
        busca();
    }

    public boolean hayFiltros() {
        boolean R = false;
        if (filtrosSeleccionados != null && !filtrosSeleccionados.isEmpty()) {
            R = true;
        }
        return R;
    }

    public String getFiltro(QueryParameter queryParameter) {
        StringBuilder sb = new StringBuilder("select ")
                .append(OBJ)
                .append(" from ")
                .append(getNombreClase())
                .append(" ")
                .append(OBJ)
                .append(" where 1=1 ");

        for (Filtro f : getFiltrosSeleccionados()) {
            f.getFiltro(sb, queryParameter);
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getTotalesQuery(QueryParameter queryParameter, String property) {
        StringBuilder sb = new StringBuilder("select ");

        sb.append("SUM(").append(OBJ).append(".").append(property).append(") ");
        sb.append(" from ");
        sb.append(getNombreClase());
        sb.append(" ");
        sb.append(OBJ);
        sb.append(" where 1=1 ");

        for (Filtro f : getFiltrosSeleccionados()) {
            f.getFiltro(sb, queryParameter);
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public String getNombreClase() {
        return getNuevo().getClass().getSimpleName();
    }

    public List<Filtro> getFiltros() {
        List<Filtro> R = new ArrayList<Filtro>();
        return R;
    }

    public List<Consulta> getConsultas() {
        return consultaDAO.findAll(getNombreClase());
    }

    public Consulta getConsultaActual() {
        if (consultaActual == null) {
            consultaActual = new Consulta();
            consultaActual.setNombreClase(getNombreClase());
        }
        return consultaActual;
    }

    public void setConsultaActual(Consulta consultaActual) {
        this.consultaActual = consultaActual;
    }

    public String guardaConsulta() {
        for (Filtro f : filtrosSeleccionados) {
            getConsultaActual().asociaFiltro(f);
        }
        setConsultaActual(consultaDAO.edit(getConsultaActual()));


        for (Filtro f : getConsultaActual().getFiltros()) {
            for (Filtro fs : getFiltrosSeleccionados()) {
                if (fs.getNombre().compareTo(f.getNombre()) == 0) {
                    fs = f;
                }
            }
        }
        return null;
    }

    public List<T> getListado() {
        return listado;
    }

    public void setListado(List<T> listado) {
        this.listado = listado;
    }

    public String busca() {
        QueryParameter queryParameter = new QueryParameter();
        if (hayFiltros()) {
            listado = getEjb().findAll(getFiltro(queryParameter), queryParameter);
        } else {
            listado = new ArrayList<T>();
        }

        totaliza();

        return null;
    }

    public void toogleTotaliza() {
        if (!muestraTotales) {
            muestraTotales = true;

        } else {
            muestraTotales = false;
        }
        totaliza();
    }

    public String totaliza() {
        if (muestraTotales && hayFiltros()) {
            if (columnas != null) {
                for (ColumnModel c : columnas) {
                    if (c.isTotalizable()) {
                        QueryParameter queryParameter = new QueryParameter();
                        Double valor = (Double) consultaDAO.findTotal(getTotalesQuery(queryParameter, c.getProperty()), queryParameter);
                        mapTotales.put(c.getProperty(), valor);
                    }
                }
            }
        } else {
            mapTotales = new HashMap<String, Double>();
        }

        return null;
    }

    public Map<String, Double> getMapTotales() {
        return mapTotales;
    }

    public void setMapTotales(Map<String, Double> mapTotales) {
        this.mapTotales = mapTotales;
    }

    public boolean isMuestraTotales() {
        return muestraTotales;
    }

    public void setMuestraTotales(boolean muestraTotales) {
        this.muestraTotales = muestraTotales;
    }

    public void createPDF() {
        try { //catch better your exceptions, this is just an example
            FacesContext context = FacesContext.getCurrentInstance();
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            if (!document.isOpen()) {
                document.open();
            }

            PdfPTable pdfTable = exportPDFTable();
            document.add(pdfTable);

            //Keep modifying your pdf file (add pages and more)

            document.close();
            String fileName = "PDFFile";

            writePDFToResponse(context.getExternalContext(), baos, fileName);

            context.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PdfPTable exportPDFTable() throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int numberOfColumns = columnas.size();

        PdfPTable pdfTable = new PdfPTable(numberOfColumns);
        pdfTable.setWidthPercentage(100);
        BaseFont helvetica = null;

        try {
            helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.EMBEDDED);
        } catch (Exception e) {
            //font exception
        }

        Font font = new Font(helvetica, 8, Font.NORMAL);

        for (ColumnModel c : columnas) {
            PdfPCell celda;
            celda = new PdfPCell(new Phrase(c.getHeader(), new Font(helvetica, 8, Font.BOLD)));
            celda.setVerticalAlignment(Element.ALIGN_MIDDLE);
            System.out.println("Tipo: "+c.getTipo());
            if (c.getTipo().equals("Date")) {
                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
            }else if (c.getTipo().equals("BigDecimal") ||
                    c.getTipo().equals("Double") ||
                    c.getTipo().equals("Long") ||
                    c.getTipo().equals("Integer") ||
                    c.getTipo().equals("Number") ||
                    c.getTipo().equals("BigInteger") ||
                    c.getTipo().equals("double") ||
                    c.getTipo().equals("float") ||
                    c.getTipo().equals("long") ||
                    c.getTipo().equals("int") ) {
                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
            } else {
                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
            }
            celda.setPadding(3f);
            
            pdfTable.addCell(celda);
        }

        int fila = 0;
        for (T item : listado) {
            for (ColumnModel c : columnas) {
                String p = c.getProperty();
                Method m = item.getClass().getMethod("get" + Character.toUpperCase(p.charAt(0)) + p.substring(1), new Class[]{});
                Object ret = m.invoke(item, new Object[]{});
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                NumberFormat nf = NumberFormat.getInstance(new Locale("es", "py"));

                PdfPCell celda;

                if (ret instanceof Date) {
                    ret = sdf.format((Date) ret);
                    celda = new PdfPCell(new Phrase(ret.toString(), font));
                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                } else if (ret instanceof Number) {
                    ret = nf.format(ret);
                    celda = new PdfPCell(new Phrase(ret.toString(), font));
                    celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                } else {
                    celda = new PdfPCell(new Phrase(ret.toString(), font));
                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                }

                celda.setBorder(0);
                if (fila % 2 != 0) {
                    celda.setBackgroundColor(Color.decode("#dddddd"));
                }
                pdfTable.addCell(celda);
            }
            fila++;
        }
        return pdfTable;
    }

    private void writePDFToResponse(ExternalContext externalContext, ByteArrayOutputStream baos, String fileName) {
        try {
            externalContext.responseReset();
            externalContext.setResponseContentType("application/pdf");
            externalContext.setResponseHeader("Expires", "0");
            externalContext.setResponseHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            externalContext.setResponseHeader("Pragma", "public");
            externalContext.setResponseHeader("Content-disposition", "attachment;filename=" + fileName + ".pdf");
            externalContext.setResponseContentLength(baos.size());
            OutputStream out = externalContext.getResponseOutputStream();
            baos.writeTo(out);
            externalContext.responseFlushBuffer();
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}
