package net.unibave.npa.core.util.reflect.metainf;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.common.abstracts.IFactoryArgs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wesley on 16/07/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.TYPE})
public @interface FactoryArgs {

    Class<? extends IFactoryArgs<?,?>> value();

}
