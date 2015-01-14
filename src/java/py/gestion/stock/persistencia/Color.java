/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.stock.persistencia;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author christian
 */
@Entity
@NamedQueries({
    @NamedQuery(name = Color.TODOS, query = "select c from Color c")})
public class Color extends Atributo {

    public static final String TODOS = "package py.gestionpymes.jpa.adm.Color.TODOS";
    
    private String nombreIngles;
    private String cod;
    private String codHex;
    

    public Color() {
        
    }

    public Color(String color, String codHex) {
        super(color, TipoAtributoProducto.COLOR);
        
        this.codHex = codHex;
    }

    public Color(String color, String nombreIngles, String cod, String codHex) {
        this(color, codHex);
        this.nombreIngles = nombreIngles;
        this.cod = cod;

    }

    public String getCod() {
        return cod.toUpperCase();
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getNombreIngles() {
        return nombreIngles;
    }

    public void setNombreIngles(String nombreIngles) {
        this.nombreIngles = nombreIngles;
    }

    
    public String getCodHex() {
        return codHex;
    }

    public void setCodHex(String codHex) {
        this.codHex = codHex;
    }


    @Override
    public String toString() {
        return super.getNombre();
    }


}
