package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;

import java.lang.annotation.Annotation;
import java.util.Objects;

/**
 * Created by wesley on 31/07/16.
 */
public abstract class AbstractFactory<E, O> implements IFactory<E, O> {

    private E argument;

    private O objectFactory;

    public final E getArgument() {
        return argument;
    }

    public final void setArgument(E argument) {
        Objects.requireNonNull(argument);
        this.argument = argument;
    }

    public final O getObjectFactory() {
        return objectFactory;
    }

    public final void setObjectFactory(O objectFactory) {
        this.objectFactory = objectFactory;
    }

    protected abstract Annotation getRepresentativeAnnotation();




}
