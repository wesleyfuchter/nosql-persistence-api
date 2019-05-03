package net.unibave.npa.core.persistence.metainf;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface EmbeddedEntity {

    Class<? extends Serializable> principalEntityClass();

    /**
     * The entity comment on database
     */
    String comment() default "";

}
