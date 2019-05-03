package net.unibave.npa.core.persistence.metainf;

import java.lang.annotation.*;
import java.util.function.Function;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.ANNOTATION_TYPE})
public @interface ValidatorRule {

    Class<? extends Function<Annotation, Validator>> parserImplement();

}
