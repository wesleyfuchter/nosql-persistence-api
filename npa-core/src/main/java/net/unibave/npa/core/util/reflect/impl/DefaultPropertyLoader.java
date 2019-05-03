package net.unibave.npa.core.util.reflect.impl;

import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.ILoader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public class DefaultPropertyLoader implements ILoader {

    @Override
    public Object load(Object inputValue, Field fieldToLoad) throws Exception {
        return ReflectionLookupFacade.getInstance().getFieldValue(fieldToLoad,inputValue);
    }

}
