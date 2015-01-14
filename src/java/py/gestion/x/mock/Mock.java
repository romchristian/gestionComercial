/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.x.mock;

import py.gestion.seguridad.servicios.UsuarioDAO;
import py.gestion.seguridad.servicios.RolDAO;
import py.gestion.seguridad.servicios.AccionDAO;
import py.gestion.seguridad.persistencia.Usuario;
import py.gestion.seguridad.persistencia.Rol;
import py.gestion.seguridad.persistencia.Accion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import py.gestion.adm.servicios.ColorDAO;
import py.gestion.adm.servicios.ConfRedDAO;
import py.gestion.adm.servicios.EmpresaDAO;
import py.gestion.adm.servicios.ImpuestoIVADAO;
import py.gestion.adm.servicios.MonedaDAO;
import py.gestion.adm.servicios.SucursalDAO;
import py.gestion.cobranza.servicios.CobranzaService;
import py.gestion.cobranza.servicios.PagoDAO;
import py.gestion.proveedores.servicios.ProveedorService;
import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.adm.persistencia.ConfRed;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.persistencia.Nacionalidad;
import py.gestion.adm.persistencia.Obra;
import py.gestion.adm.persistencia.Sucursal;
import py.gestion.adm.servicios.CotizacionDAO;
import py.gestion.adm.servicios.ObraDAO;
import py.gestion.adm.servicios.PaisDAO;
import py.gestion.clientes.persistencia.DetCuentaCliente;
import py.gestion.clientes.persistencia.OperacionDesembolsoPrestamo;
import py.gestion.clientes.persistencia.enums.TipoDocumento;
import py.gestion.cobranza.persistencia.CobroCuota;
import py.gestion.cobranza.persistencia.DetCobroCuota;
import py.gestion.cobranza.persistencia.Efectivo;
import py.gestion.compra.persistencia.DetFacturaProveedor;
import py.gestion.compra.persistencia.DetFacturaProveedorProducto;
import py.gestion.compra.persistencia.DetOrdenCompra;
import py.gestion.compra.persistencia.FacturaProveedor;
import py.gestion.compra.persistencia.OrdenCompra;
import py.gestion.compra.persistencia.enums.EstadoFacturaProveedor;
import py.gestion.compra.persistencia.enums.EstadoOC;
import py.gestion.compra.servicios.AsociacionOC_FPDAO;
import py.gestion.compra.servicios.FacturaProveedorDAO;
import py.gestion.compra.servicios.FormularioHechaukaDAO;
import py.gestion.compra.servicios.OrdenCompraDAO;
import py.gestion.pagos.persistencia.EstadoOrdenPago;
import py.gestion.pagos.servicios.OrdenPagoDAO;
import py.gestion.proveedores.persistencia.Proveedor;
import py.gestion.prestamos.persistencia.PeriodoPago;
import py.gestion.prestamos.persistencia.Prestamo;
import py.gestion.prestamos.persistencia.SistemaAmortizacion;
import py.gestion.proveedores.persistencia.ProveedorTimbrado;
import py.gestion.stock.persistencia.Color;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.stock.servicios.UnidadMedidaDAO;

/**
 *
 * @author christian
 */

@Named
@ApplicationScoped
public class Mock implements Serializable {

    @EJB
    private ColorDAO colorDAO;
    @EJB
    private ProductoDAO productoDAO;
    @EJB
    private RolDAO rolDAO;
    @EJB
    private UsuarioDAO usuarioDAO;
    @EJB
    private ProveedorService proveedorDAO;
    @EJB
    private AccionDAO accionDAO;
    @EJB
    private ImpuestoIVADAO impuestoIVADAO;
    @EJB
    private MonedaDAO monedaDAO;
    @EJB
    private CotizacionDAO cotizacionDAO;
    @EJB
    private EmpresaDAO empresaDAO;
    @EJB
    private SucursalDAO sucursalDAO;
    @EJB
    private ConfRedDAO confRedDAO;
    @EJB
    private PagoDAO pagoDAO;
    @EJB
    private CobranzaService cobranzaService;
    @EJB
    private PaisDAO paisDAO;
    @EJB
    private ObraDAO obraDAO;
    @EJB
    private UnidadMedidaDAO unidadMedidaDAO;
    @EJB
    private FacturaProveedorDAO facturaProveedorDAO;
    @EJB
    private OrdenCompraDAO ordenCompraDAO;
    @EJB
    private AsociacionOC_FPDAO asociacionOC_FPDAO;
    @EJB
    private FormularioHechaukaDAO formularioHechuakaDAO;
    private List<Producto> productos;
    private List<Color> colores;
    private List<Proveedor> proveedores;
    private List<Rol> roles;
    private List<Usuario> usuarios;
    private List<ImpuestoIVA> impuestoIVAs;
    private List<Moneda> monedas;
    private Cotizacion cotizacion;
    private List<Empresa> empresas;
    private List<Sucursal> sucursales;
    private ConfRed confRed;
    private Prestamo prestamo;
    private Nacionalidad nacionalidad;
    private UnidadMedida unidadMedida;
    private Obra obra;
    @EJB
    private OrdenPagoDAO ordenPagoDAO;
    private FacturaProveedor facturaProveedor;
    @PersistenceContext(unitName = "SYSCVSAPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public Mock() {
    }

    public void creaDatos() {

        limpiaUsuarios();
        if (colores == null) {
            creaColores();
        }

        if (proveedores == null) {
            creaProveedores();
        }
        if (roles == null) {
            creaRoles();
        }
        if (usuarios == null) {
            creaUsuarios();
        }

        if (impuestoIVAs == null) {
            creaImpuestosIVA();
        }

        if (monedas == null) {
            creaMonedas();
        }

        if (cotizacion == null) {
            creaCotizacion();
        }

        if (empresas == null) {
            creaEmpresas();
        }

        if (sucursales == null) {
            creaSucursales();
        }

        if (confRed == null) {
            creaConfRed();
        }
        if (prestamo == null) {
            creaPrestamo();
        }

        if (unidadMedida == null) {
            creaUnidadMedida();
        }
        if (obra == null) {
            creaObra();
        }

        if (facturaProveedor == null) {
            creaFacturaProveedor();
        }

        CreaEstadOrdenPago();
        
        creaVista();

    }

    private void creaColores() {
        colores = new ArrayList<Color>();
        colores.add(new Color("Negro", "Black", "ne", "#000000"));
        colores.add(new Color("Rojo", "Red", "ro", "#990000"));
        colores.add(new Color("Amarillo", "Yellow", "am", "#CCCC00"));
        colores.add(new Color("Verde", "Green", "ve", "#33CC00"));
        colores.add(new Color("Dorado", "Gold", "do", "#FF9900"));
        for (Color c : colores) {
            colorDAO.create(c);
        }
    }

    private void creaProductos(ImpuestoIVA i) {
        productos = new ArrayList<Producto>();
        productos.add(new Producto(1L, "01", "P1"));
        productos.add(new Producto(2L, "02", "P2"));
        productos.add(new Producto(3L, "03", "P3"));
        productos.add(new Producto(4L, "04", "P4"));
        for (Producto p : productos) {
            p.setIva(i);
            productoDAO.create(p);
        }
    }

   
    private void creaImpuestosIVA() {
        impuestoIVAs = new ArrayList<ImpuestoIVA>();
        impuestoIVAs.add(new ImpuestoIVA("IVA 5%", 0.05D));
        impuestoIVAs.add(new ImpuestoIVA("IVA 10%", 0.1D));
        ImpuestoIVA imp = null;
        for (ImpuestoIVA i : impuestoIVAs) {
            impuestoIVADAO.create(i);
            imp = i;
        }

        creaProductos(imp);
    }

    private void creaProveedores() {

        proveedores = new ArrayList<Proveedor>();
        proveedores.add(new Proveedor("Pepe", "123456", "pe", 2));
        proveedores.add(new Proveedor("Pedro", "123", "pd", 3));
        proveedores.add(new Proveedor("Pablo", "456", "pa", 4));
        proveedores.add(new Proveedor("Juan", "874", "ju", 5));
        proveedores.add(new Proveedor("Alberto", "4531", "al", 6));
        proveedores.add(new Proveedor("Martin", "3213", "ma", 7));
        proveedores.add(new Proveedor("Luis", "35123", "lu", 8));

        int cont = 1;
        for (Proveedor p : proveedores) {
            List<ProveedorTimbrado> timbrados = new ArrayList<ProveedorTimbrado>();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(new Date());
            gc.add(Calendar.YEAR, 1);
            timbrados.add(new ProveedorTimbrado(p, 1234567L + cont, "001", "001", gc.getTime()));
            p.setTimbrados(timbrados);
            proveedorDAO.create(p);
            cont++;
        }

    }

    private void creaRoles() {

        roles = new ArrayList<Rol>();
        Rol rolAdmin = new Rol("Admin");
        roles.add(rolAdmin);
        roles.add(new Rol("Invitado"));
        for (Rol r : roles) {
            rolDAO.create(r);
        }

        creaAcciones(rolAdmin);
    }

    private void creaUsuarios() {

        usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario("Administrator",
                "",
                new Date(),
                "admin",
                "admin",
                rolDAO.find("Admin"),
                TipoDocumento.CI,
                "3404671"));

        for (Usuario u : usuarios) {
            usuarioDAO.create(u);
        }
    }

    private void creaAcciones(Rol rol) {
        String[] acciones = {"VisualizaEmpresa",
            "CreaEmpresa",
            "EditaEmpresa",
            "VisualizaProveedor",
            "CreaProveedor",
            "EditaProveedor",
            "CargaDeStock",
            "Inventario",
            "VisualizaUnidadMedida",
            "CreaUnidadMedida",
            "EditaUnidadMedida",
            "VisualizaFamilia",
            "CreaFamilia",
            "EditaFamilia",
            "VisualizaProducto",
            "CreaProducto",
            "EditaProducto",
            "VisualizaSucursal",
            "CreaSucursal",
            "EditaSucursal",
            "VisualizaObra",
            "CreaObra",
            "EditaObra",
            "CreaCliente",
            "ConfRed",
            "VisualizaUsuario",
            "CreaUsuario",
            "EditaUsuario",
            "VisualizaRol",
            "EditaRol",
            "CreaRol",
            "VisualizaMoneda",
            "EditaMoneda",
            "CreaMoneda",
            "VisualizaDeposito",
            "EditaDeposito",
            "CreaDeposito",
            "CreaOrdenCompra",
            "VisualizaOrdenCompra",
            "EditaOrdenCompra",
            "CreaPrestamo",
            "VisualizaPrestamo",
            "CobraCuotaPrestamo",
            "CreaPais",
            "VisualizaPais",
            "EditaPais",
            "CreaDepartamento",
            "VisualizaDepartamento",
            "EditaDepartamento",
            "CreaDistrito",
            "VisualizaDistrito",
            "EditaDistrito",
            "CreaLocalidad",
            "VisualizaLocalidad",
            "EditaLocalidad"};

        for (int i = 0; i < acciones.length; i++) {
            Accion a = new Accion(acciones[i]);
            accionDAO.create(a);

            a.setRoles(new ArrayList<Rol>());
            a.getRoles().add(rol);

            rol.getAcciones().add(a);
        }

        rolDAO.edit(rol);

    }

    private void creaMonedas() {
        monedas = new ArrayList<Moneda>();
        monedas.add(new Moneda("Guaraníes", "Gs", 0, true));
        monedas.add(new Moneda("Dólar", "$", 2, false));
        for (Moneda m : monedas) {
            monedaDAO.create(m);
        }
    }

    private void creaEmpresas() {
        empresas = new ArrayList<Empresa>();
        Empresa emp = new Empresa("CVSA", "123456");
        emp.setDv(2);
        emp.setRucRepLegal("12341678");
        emp.setDvRepLegal(3);
        emp.setNombreRepLegal("Roberto Vuyk");

        empresas.add(emp);

        for (Empresa e : empresas) {
            empresaDAO.create(e);
        }
    }

    private void creaSucursales() {
        Empresa e = empresaDAO.find("CVSA");
        sucursalDAO.create(new Sucursal(e, "Central"));
    }

    private void creaConfRed() {
        confRed = new ConfRed(false, "", 0, "confproxy");
        confRedDAO.create(confRed);
    }

    private void creaPrestamo() {
        prestamo = new Prestamo();

        prestamo.setCapital(1000000);
        prestamo.setGastos(200000);
        prestamo.setPlazo(12);
        prestamo.setTasa(24);
        prestamo.setPeriodoPago(PeriodoPago.MENSUAL);
        prestamo.setSistemaAmortizacion(SistemaAmortizacion.FRANCES);

        DetCuentaCliente de = new OperacionDesembolsoPrestamo(prestamo);

    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void paga(Prestamo prestamo) {
        CobroCuota cc = new CobroCuota(prestamo);
        Efectivo efe = new Efectivo();
        efe.setFecha(new Date());
        efe.setMoneda(monedaDAO.find("Guaraníes"));
        efe.setMonto(50000d);
        pagoDAO.create(efe);

        DetCobroCuota dcc = new DetCobroCuota();
        dcc.setPago(efe);
        dcc.setMonto(efe.getMonto());
        dcc.setCobroCuota(cc);
        dcc.setDetPrestamo(prestamo.getDetalles().get(0));
        dcc.setFecha(new Date());
        List<DetCobroCuota> detallesCobro = new ArrayList<DetCobroCuota>();
        detallesCobro.add(dcc);
        cc.setDetalles(detallesCobro);

        cobranzaService.create(cc);
    }

    private void creaUnidadMedida() {
        unidadMedida = new UnidadMedida("Kilo");
        unidadMedidaDAO.create(unidadMedida);
    }

    private void creaObra() {
        obra = new Obra("Obra1");
        obraDAO.create(obra);
    }

    private void creaFacturaProveedor() {

        Proveedor p = null;
        for (Proveedor pv : proveedores) {
            p = pv;
            break;
        }

        facturaProveedor = new FacturaProveedor();
        facturaProveedor.setCreacion(new Date());
        facturaProveedor.setEmision(new Date());
        facturaProveedor.setNumero("1");
        facturaProveedor.setTimbrado("1234567");
        facturaProveedor.setCodigoEstablecimiento("001");
        facturaProveedor.setCodigoSucursal("001");
        facturaProveedor.setEstado(EstadoFacturaProveedor.CONFORMADA);
        facturaProveedor.setMoneda(monedaDAO.find("Guaraníes"));

        facturaProveedor.setProveedor(p);

        DetFacturaProveedor d1 = new DetFacturaProveedorProducto(productoDAO.findPorCodigoEstricto("01"),
                facturaProveedor, 1, 10, unidadMedida, "producto 1", 0.1d, 15000d, 0d, 0d, 15000d);
        DetFacturaProveedor d2 = new DetFacturaProveedorProducto(productoDAO.findPorCodigoEstricto("02"),
                facturaProveedor, 1, 20, unidadMedida, "producto 2", 0.1d, 20000d, 0d, 0d, 20000d);
        List<DetFacturaProveedor> detalles = new ArrayList<DetFacturaProveedor>();
        detalles.add(d1);
        detalles.add(d2);
        facturaProveedor.setDetalles(detalles);
        facturaProveedor.setTotal(new BigDecimal(35000d));
        facturaProveedorDAO.create(facturaProveedor);

        OrdenCompra oc = new OrdenCompra();
        oc.setCreacion(new Date());
        oc.setEstado(EstadoOC.PENDIENTE_AUTORIZACION);
        oc.setMoneda(monedaDAO.find("Guaraníes"));
        oc.setNumero("1");
        oc.setObra(obra);
        oc.setProveedor(p);

        DetOrdenCompra doc1 = new DetOrdenCompra(oc, productoDAO.findPorCodigoEstricto("01"), "producto 1", unidadMedida, 10d, 18000d);
        DetOrdenCompra doc2 = new DetOrdenCompra(oc, productoDAO.findPorCodigoEstricto("02"), "producto 2", unidadMedida, 18d, 21000d);
        List<DetOrdenCompra> dets = new ArrayList<DetOrdenCompra>();
        dets.add(doc1);
        dets.add(doc2);
        oc.setDetalles(dets);

        ordenCompraDAO.create(oc);

        OrdenCompra oc2 = new OrdenCompra();
        oc2.setCreacion(new Date());
        oc2.setEstado(EstadoOC.PENDIENTE_AUTORIZACION);
        oc2.setMoneda(monedaDAO.find("Guaraníes"));
        oc2.setNumero("2");
        oc2.setObra(obra);
        oc2.setProveedor(p);

        DetOrdenCompra doc11 = new DetOrdenCompra(oc2, productoDAO.findPorCodigoEstricto("01"), "producto 1", unidadMedida, 5d, 50000d);
        DetOrdenCompra doc22 = new DetOrdenCompra(oc2, productoDAO.findPorCodigoEstricto("02"), "producto 2", unidadMedida, 15d, 10000d);
        DetOrdenCompra doc3 = new DetOrdenCompra(oc2, productoDAO.findPorCodigoEstricto("03"), "producto 3", unidadMedida, 20d, 15000d);
        List<DetOrdenCompra> dets2 = new ArrayList<DetOrdenCompra>();
        dets2.add(doc11);
        dets2.add(doc22);
        dets2.add(doc3);
        oc2.setDetalles(dets2);

        ordenCompraDAO.create(oc2);

        asociacionOC_FPDAO.asociaOC_FP(doc1, d1);
        asociacionOC_FPDAO.asociaOC_FP(doc2, d2);

       // formularioHechuakaDAO.creaFormularioHechauka(new Periodo(2013, 6), empresas.get(0), usuarios.get(0));
    }

    private void limpiaUsuarios() {
        delete();
    }

    public void delete() {
        try {
            utx.begin();
            em.createNativeQuery("delete from usuario; delete from rol;").executeUpdate();
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    public void creaVista() {
        try {
            utx.begin();
            em.createNativeQuery("CREATE OR REPLACE VIEW groups AS \n"
                    + " SELECT cast(lower(r.nombre) as character varying(255)) AS groupid, u.usuario AS userid\n"
                    + "   FROM rol r\n"
                    + "   JOIN usuario u ON r.id = u.rol_id;\n"
                    + "\n"
                    + "ALTER TABLE groups OWNER TO postgres;\n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "CREATE OR REPLACE VIEW users AS \n"
                    + " SELECT usuario.usuario AS userid, usuario.clave AS password\n"
                    + "   FROM usuario;\n"
                    + "\n"
                    + "ALTER TABLE users OWNER TO postgres;").executeUpdate();
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private void creaCotizacion() {
        this.cotizacion = new Cotizacion();
        cotizacion.setMoneda(monedaDAO.find("Dólar"));
        cotizacion.setCompra(4500d);
        cotizacion.setVenta(4600d);
        cotizacion.setVigencia(new Date());
        cotizacionDAO.create(cotizacion);
    }

    private void CreaEstadOrdenPago() {
        EstadoOrdenPago estado1 = new EstadoOrdenPago();
        estado1.setNombre("CREADA");
        ordenPagoDAO.create(estado1);
        EstadoOrdenPago estado2 = new EstadoOrdenPago();
        estado2.setNombre("PENDIENTE");
        ordenPagoDAO.create(estado2);
        EstadoOrdenPago estado3 = new EstadoOrdenPago();
        estado3.setNombre("PAGADA");
        ordenPagoDAO.create(estado3);
    }
}
