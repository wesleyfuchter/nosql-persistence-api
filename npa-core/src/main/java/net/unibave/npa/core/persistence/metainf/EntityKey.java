/**
 *
 */
package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.enumeration.KeyType;
import net.unibave.npa.core.persistence.impl.DefaultKeyGenerator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wesley
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface EntityKey {

    KeyType keyType() default KeyType.SIMPLE;

    Class<? extends IKeyGenerator<?, ?>> iKeyGenerator() default DefaultKeyGenerator.class;

}
