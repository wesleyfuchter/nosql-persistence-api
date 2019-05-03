package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.enumeration.KeyType;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

/**
 * Created by wesley on 02/08/16.
 */
public final class EntityKeyFactoryImpl extends AbstractEntityKeyFactory<AttributeBean> {

    @Override
    protected AttributeBean getAttribute() {
        return getArgument();
    }

    @Override
    protected KeyType getKeyType() {
        return getRepresentativeAnnotation().keyType();
    }

    @Override
    protected Class<? extends IKeyGenerator<?, ?>> getKeyGeneratorClass() {
        return getRepresentativeAnnotation().iKeyGenerator();
    }

    @Override
    protected IKeyGenerator<?, ?> getKeyGenerator() throws Exception {
        return ReflectionLookupFacade.getInstance().<IKeyGenerator<?,?>>createTypedInstance(getKeyGeneratorClass());
    }
}
