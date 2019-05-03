/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used by set metainf on controller entity class
 *
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE, ElementType.FIELD})
public @interface Controllers {

    /**
     * Array of controller annotation
     *
     * @see Controller
     */
    Controller[] value();

}
