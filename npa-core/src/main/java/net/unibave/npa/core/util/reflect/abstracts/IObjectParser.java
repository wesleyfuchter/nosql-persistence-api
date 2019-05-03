package net.unibave.npa.core.util.reflect.abstracts;

/**
 * Created by wesley on 17/05/16.
 */
public interface IObjectParser<I, O> {

    O write(final I i, final Class<?> classToWrite) throws Exception;

    I read(final O o) throws Exception;

}


