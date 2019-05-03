package net.unibave.npa.core.util.reflect.impl;

import net.unibave.npa.core.common.abstracts.IFactory;

import java.util.Objects;

/**
 * Created by wesley on 16/07/16.
 */
@Deprecated
public final class DynamicFactory {

    private DynamicFactory() {}

    private static DynamicFactory instance;

    public static DynamicFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DynamicFactory();
        }
        return instance;
    }
    public <E,O> O factory(IFactory<E,O> factoryImpl, E arg) throws Exception {
        return factoryImpl.factory(arg);
    }

    public <T> T factory(Class<T> classToFactory) throws Exception {
        return classToFactory.newInstance();
    }

    public <T> T factory(Class<T> classToFactory, Object args) throws Exception {
        return null;
    }

    public <E, O> O factory(Class<E> classToFactory, Class<? extends IFactory<E,O>> factoryClass, E arg) throws Exception {
        return null;
    }
    
}
