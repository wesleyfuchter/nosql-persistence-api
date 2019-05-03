package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.abstracts.IConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wesley on 18/09/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface EnumValue {

    Class<? extends IConverter> converter();

}
