/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used by set metainf on entity class
 *
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface Entity {

    /**
     * The entity name on database
     */
    String name();

    /**
     * The entity comment on database
     */
    String comment() default "";

    /**
     * The entity database name
     */
    String dataBase() default "";

}
