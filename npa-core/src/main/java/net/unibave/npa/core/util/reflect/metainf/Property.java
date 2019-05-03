package net.unibave.npa.core.util.reflect.metainf;

import net.unibave.npa.core.util.reflect.abstracts.IInjector;
import net.unibave.npa.core.util.reflect.abstracts.ILoader;
import net.unibave.npa.core.util.reflect.impl.DefaultPropertyInjector;
import net.unibave.npa.core.util.reflect.impl.DefaultPropertyLoader;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wesley on 17/05/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD})
public @interface Property {

    String key();

    Class<? extends ILoader> loader() default DefaultPropertyLoader.class;

    Class<? extends IInjector> injector() default DefaultPropertyInjector.class;

    boolean nullable() default true;

}
