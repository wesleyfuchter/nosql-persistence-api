package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.impl.crud.AbstractCRUD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wesley on 25/06/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.TYPE)
public @interface NextCrudClass {

    Class<? extends AbstractCRUD<?,?>> value();

}
