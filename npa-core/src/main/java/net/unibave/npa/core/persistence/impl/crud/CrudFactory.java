package net.unibave.npa.core.persistence.impl.crud;

import net.unibave.npa.core.common.abstracts.IFactoryArgs;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

/**
 * Created by wesley on 25/06/16.
 */
public class CrudFactory implements IFactoryArgs<Class<? extends  AbstractCRUD<?,?>>,AbstractCRUD<?,?>> {

    protected CrudFactory() {}

    @Override
    public AbstractCRUD<?, ?> factory(Class<? extends  AbstractCRUD<?,?>> aClass, Object ... args) throws Exception {
        return ReflectionLookupFacade.getInstance().<AbstractCRUD<?, ?>>createTypedInstance(aClass, args);
    }
}
