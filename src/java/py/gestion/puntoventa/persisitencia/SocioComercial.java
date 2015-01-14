/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.puntoventa.persisitencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

/**
 *
 * @author Acer
 */
@Entity
public class SocioComercial implements Serializable {

    @OneToMany(mappedBy = "socio")
    private List<Contacto> contactos;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    private byte[] imagen;
    private boolean esEmpresa;
    private String nombre;
    private String nombreFantasia;
    private String ruc;
    private String digitoVericador;

    //Direccion principal
    private String calle1;
    private String calle2;
    private String ciudad;
    private String barrio;
    //Telefono pro
    private String cargo;
    private String telefono;
    private String movil;
    private String fax;
    private String mail;

    private String sitioWeb;
    private String titulo;

    @OneToMany(mappedBy = "socio")
    private List<Timbrado> timbrados;

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public boolean isEsEmpresa() {
        return esEmpresa;
    }

    public void setEsEmpresa(boolean esEmpresa) {
        this.esEmpresa = esEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreFantasia() {
        return nombreFantasia;
    }

    public void setNombreFantasia(String nombreFantasia) {
        this.nombreFantasia = nombreFantasia;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDigitoVericador() {
        return digitoVericador;
    }

    public void setDigitoVericador(String digitoVericador) {
        this.digitoVericador = digitoVericador;
    }

    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Timbrado> getTimbrados() {
        return timbrados;
    }

    public void setTimbrados(List<Timbrado> timbrados) {
        this.timbrados = timbrados;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SocioComercial)) {
            return false;
        }
        SocioComercial other = (SocioComercial) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "py.syscvsa.ventas.persisitencia.SocioComercial[ id=" + id + " ]";
    }

}
