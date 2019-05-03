package net.unibave.npa.core.util.reflect.abstracts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wesley on 17/05/16.
 */
public interface ILoader {

    Object load(Object inputValue, Field fieldToLoad) throws Exception;

}
