/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import javax.persistence.Entity;
import py.gestion.utils.servicios.QueryParameter;
import py.gestion.utils.web.filtros.Filtros;

/**
 *
 * @author cromero
 */
@Entity
public abstract class FiltroCadena extends Filtro {

    private String cadena;

    public FiltroCadena() {
    }

    public FiltroCadena(String nombre) {
        super(nombre);
        recarga();
    }

    private void recarga() {
        cadena = "";
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    @Override
    public String getFiltro(StringBuilder sb, QueryParameter queryParameter) {
        sb.append(" and ").append(Filtros.OBJ).append(".").append(getCampo());
        sb.append(" like :cadena ");
        queryParameter.and("cadena", "%" + cadena + "%");
        return sb.toString();
    }

    public abstract String getCampo();
}
