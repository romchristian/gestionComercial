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
import java.util.Date;
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
//import py.gestion.cobranza.servicios.CobranzaService;
//import py.gestion.cobranza.servicios.PagoDAO;

import py.gestion.stock.servicios.ProductoDAO;
import py.gestion.adm.persistencia.ConfRed;
import py.gestion.adm.persistencia.Cotizacion;

import py.gestion.adm.persistencia.Empresa;
import py.gestion.adm.persistencia.ImpuestoIVA;
import py.gestion.adm.persistencia.Moneda;
import py.gestion.adm.persistencia.Nacionalidad;
import py.gestion.adm.persistencia.Sucursal;
import py.gestion.adm.persistencia.enums.TipoDocumento;
import py.gestion.adm.servicios.CotizacionDAO;

import py.gestion.adm.servicios.PaisDAO;
import py.gestion.contabilidad.persistencia.Diario;
import py.gestion.contabilidad.persistencia.MetodoPago;
import py.gestion.contabilidad.persistencia.TipoDiario;
import py.gestion.puntoventa.persisitencia.PuntoVenta;
import py.gestion.puntoventa.persisitencia.Secuencia;
import py.gestion.puntoventa.persisitencia.SesionTPV;
import py.gestion.puntoventa.persisitencia.TipoMetodoPago;
import py.gestion.contabilidad.servicio.DiarioDAO;
import py.gestion.contabilidad.servicio.MetodoPagoDAO;
import py.gestion.puntoventa.persisitencia.TipoSecuencia;
import py.gestion.puntoventa.servicio.PuntoVentaDAO;
import py.gestion.puntoventa.servicio.SecuenciaDAO;
import py.gestion.puntoventa.servicio.SesionTPVDAO;
import py.gestion.puntoventa.servicio.ValorMonedaDAO;
import py.gestion.stock.persistencia.Producto;
import py.gestion.stock.persistencia.UnidadMedida;
import py.gestion.stock.servicios.UnidadMedidaDAO;

//import py.gestion.cobranza.persistencia.CobroCuota;
//import py.gestion.cobranza.persistencia.DetCobroCuota;
//import py.gestion.cobranza.persistencia.Efectivo;


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
    private PaisDAO paisDAO;
    
    @EJB
    private UnidadMedidaDAO unidadMedidaDAO;
    
    private List<Producto> productos;
    
    private List<Rol> roles;
    private List<Usuario> usuarios;
    private List<ImpuestoIVA> impuestoIVAs;
    private List<Moneda> monedas;
    private Cotizacion cotizacion;
    private List<Empresa> empresas;
    private List<Sucursal> sucursales;
    private ConfRed confRed;
    private UnidadMedida unidadMedida;
    
    private Nacionalidad nacionalidad;
    
    
      @EJB
    private DiarioDAO diarioDAO;
    @EJB
    private MetodoPagoDAO metodoPagoDAO;
    @EJB
    private SecuenciaDAO secuenciaDAO;
    @EJB
    private PuntoVentaDAO puntoVentaDAO;
    @EJB
    private ValorMonedaDAO valorMonedaDAO;
    @EJB
    private SesionTPVDAO sesionTPVDAO;
    
    
    @PersistenceContext(unitName = "GestionComercialPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    public Mock() {
    }

    public void creaDatos() {

        limpiaUsuarios();
    
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
    

        if (unidadMedida == null) {
            creaUnidadMedida();
        }
        
        creaVista();
        
        creaDatosTPV();

    }

    public void creaDatosTPV() {
        Diario diario = new Diario();
        diario.setDescripcion("Diario Ventas");
        diario.setTipo(TipoDiario.VENTA);
        diarioDAO.create(diario);

        MetodoPago mp = new MetodoPago();
        mp.setControlEfectivo(true);
        mp.setNombre("Efectivo");
        mp.setTipoMetodoPago(TipoMetodoPago.EFECTIVO);
        mp.setDiario(diario);
        mp.setMoneda(monedaDAO.find("Guaraníes"));
        metodoPagoDAO.create(mp);

        Secuencia s = new Secuencia();
        s.setTipoSecuencia(TipoSecuencia.TICKET);
        s.setNombre("Ticket");
        s.setValorInicial(1L);
        
        secuenciaDAO.create(s);

        PuntoVenta pv = new PuntoVenta();
        pv.setDiario(diario);
        pv.setNombre("TPV1");
        pv.setSecuencia(s);
        puntoVentaDAO.create(pv);

        SesionTPV stpv = new SesionTPV();
        stpv.setPuntoVenta(pv);
        sesionTPVDAO.create(stpv);

    }

    private void creaProductos(ImpuestoIVA i) {
        productos = new ArrayList<Producto>();
        productos.add(new Producto(1L, "01", "Jugo Naranja 200ml Watts", i, new BigDecimal(10000)));
        productos.add(new Producto(2L, "02", "Coca cola 1000ml", i, new BigDecimal(5000)));
        productos.add(new Producto(3L, "03", "Pepsi Cola 2000ml", i, new BigDecimal(8000)));
        productos.add(new Producto(4L, "04", "Pilsen 1000ml", i, new BigDecimal(5000)));
        productos.add(new Producto(5L, "05", "Coca 2000ml", i, new BigDecimal(7000)));
        productos.add(new Producto(6L, "06", "Chocalate 100 grs", i, new BigDecimal(4000)));
        productos.add(new Producto(7L, "07", "Papa fritas 500 grs", i, new BigDecimal(4500)));
        productos.add(new Producto(8L, "08", "Galletitas Tippi 500 grs", i, new BigDecimal(2500)));
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

    

    private void creaUnidadMedida() {
        unidadMedida = new UnidadMedida("Kilo");
        unidadMedidaDAO.create(unidadMedida);
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

}
