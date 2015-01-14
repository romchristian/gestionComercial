/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.clientes.persistencia;

import py.gestion.clientes.persistencia.enums.EstadoCivil;
import py.gestion.clientes.persistencia.enums.Sexo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import py.gestion.adm.persistencia.Direccion;
import py.gestion.adm.persistencia.Nacionalidad;
import py.gestion.adm.persistencia.Pais;
import py.gestion.adm.persistencia.Persona;
import py.gestion.clientes.persistencia.validadores.SoloLetras;
import py.gestion.prestamos.persistencia.Prestamo;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Cliente.TODOS, query = "select c from Cliente c")})
@PrimaryKeyJoinColumn(name="ID", referencedColumnName="")
public class Cliente extends Persona {

    public static final String TODOS = "package py.gestionpymes.clientes.persistencia.Cliente.TODOS";
    @SoloLetras(message = "Primer Nombre debe tener solo letras")
    private String primerNombre;
    @SoloLetras(message = "Segundo Nombre debe tener solo letras")
    private String segundoNombre;
    @SoloLetras(message = "Primer Apellido debe tener solo letras")
    private String primerApellido;
    @SoloLetras(message = "Segundo Apellido debe tener solo letras")
    private String segundoApellido;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private EstadoCivil estadoCivil;
    @ManyToOne
    private Pais pais;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNac;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Direccion> direcciones;
    @OneToMany(mappedBy = "cliente")
    private List<ReferenciaCliente> referenciaClientes;
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<ContactoTelefonico> contactoTelefonicos;
    @OneToMany(mappedBy = "cliente")
    private List<ActividadLaboral> actividadesLaborales;
    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;
    //datos requeridos para solicitud y analisis
    private double lineaDeCredito;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Nacionalidad nacionalidad;

    public Cliente() {
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<ContactoTelefonico> getContactoTelefonicos() {
        return contactoTelefonicos;
    }

    public void setContactoTelefonicos(List<ContactoTelefonico> contactoTelefonicos) {
        this.contactoTelefonicos = contactoTelefonicos;
    }

    public List<ReferenciaCliente> getReferenciaClientes() {
        return referenciaClientes;
    }

    public void setReferenciaClientes(List<ReferenciaCliente> referenciaClientes) {
        this.referenciaClientes = referenciaClientes;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public List<Direccion> getDirecciones() {
        if (direcciones == null) {
            direcciones = new ArrayList<Direccion>();
        }
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public List<ActividadLaboral> getActividadesLaborales() {
        return actividadesLaborales;
    }

    public void setActividadesLaborales(List<ActividadLaboral> actividadesLaborales) {
        this.actividadesLaborales = actividadesLaborales;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    //modifica para que corra por debajo
    public double getLineaDeCredito() {
        return lineaDeCredito;
    }

    public void setLineaDeCredito(double lineaDeCredito) {
        this.lineaDeCredito = lineaDeCredito;
    }

    //modificar para que corra por debajo
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    

    @Override
    public String toString() {
        return primerApellido + ", " + primerNombre;
    }
}
