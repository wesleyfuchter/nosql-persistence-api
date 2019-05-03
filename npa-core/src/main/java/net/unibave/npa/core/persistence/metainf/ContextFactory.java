/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.abstracts.IContextFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface ContextFactory {

    Class<? extends IContextFactory> value();

}
