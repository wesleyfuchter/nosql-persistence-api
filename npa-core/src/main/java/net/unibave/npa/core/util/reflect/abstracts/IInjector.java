package net.unibave.npa.core.util.reflect.abstracts;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wesley on 17/05/16.
 */
public interface IInjector {

    Object inject(Map<String,Object> inputValue, Object instance, Object key, Field field) throws Exception;

}
