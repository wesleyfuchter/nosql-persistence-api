package net.unibave.npa.core.util.reflect.impl;

import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public class DefaultPropertyInjector implements IInjector {

    @Override
    public Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception {
        return inputValue.get(key);
    }
}
