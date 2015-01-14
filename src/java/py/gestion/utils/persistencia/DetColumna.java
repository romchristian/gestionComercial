/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gestion.utils.persistencia;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.enterprise.inject.Stereotype;

/**
 *
 * @author cromero
 */
@Stereotype
@Retention(RUNTIME)
@Target({METHOD, FIELD, TYPE})
public @interface DetColumna {
    public String propiedad();
    public String cabecera();
    public boolean visible();
    public boolean selectOne();
    public boolean fecha();
    public String bean();
}