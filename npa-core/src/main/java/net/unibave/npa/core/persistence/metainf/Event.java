package net.unibave.npa.core.persistence.metainf;

import net.unibave.npa.core.persistence.enumeration.ControllerEventsEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wesley on 02/11/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Event {

    ControllerEventsEnum value();

}

