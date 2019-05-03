/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.abstracts.AbstractController;

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
@Target(value = {ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
public @interface Controller {

    /**
     * AbstractController implement class
     *
     * @author wesley
     */
    Class<? extends AbstractController<?, ?>> implementClass();

    /**
     * Sequence of execution
     */
    int sequence() default 0;

}
