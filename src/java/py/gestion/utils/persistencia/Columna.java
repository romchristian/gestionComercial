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
import javax.enterprise.util.Nonbinding;

/**
 *
 * @author cromero
 */
@Stereotype
@Retention(RUNTIME)
@Target({METHOD, FIELD, TYPE})
public @interface Columna {

    public String propiedad();

    public String cabecera();

    @Nonbinding
    public boolean visible() default true;

    @Nonbinding
    public boolean totalizable() default false;
}