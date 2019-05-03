/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface Attribute {

    String name();

    String comment() default "";

    String prompt() default "";

}
