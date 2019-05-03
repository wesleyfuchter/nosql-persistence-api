package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.enumeration.KeyType;
import net.unibave.npa.core.persistence.exceptions.InvalidAttributePersistenceRuntimeException;
import net.unibave.npa.core.persistence.metainf.Attribute;
import net.unibave.npa.core.persistence.metainf.EntityKey;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wesley on 31/07/16.
 */
public abstract class AbstractEntityKeyFactory<E> extends AbstractFactory<E,EntityKeyBean> {

    @Override
    public final EntityKeyBean factory(E argument) throws Exception {
        setArgument(argument);
        final EntityKeyBean entityKeyBean = new EntityKeyBean();
        entityKeyBean.setAttribute(getAttribute());
        entityKeyBean.setiKeyGenerator(getKeyGenerator());
        entityKeyBean.setKeyType(getKeyType());
        return entityKeyBean;
    }

    @Override
    protected final EntityKey getRepresentativeAnnotation() {
        if (getAttribute().getField().isAnnotationPresent(EntityKey.class)) {
            return getAttribute().getField().getAnnotation(EntityKey.class);
        }
        throw new InvalidAttributePersistenceRuntimeException(InvalidAttributePersistenceRuntimeException.DEFAULT_ERR_MESSAGE);
    }

    protected abstract AttributeBean getAttribute();

    protected abstract KeyType getKeyType();

    protected abstract Class<? extends IKeyGenerator<?,?>> getKeyGeneratorClass();

    protected abstract IKeyGenerator<?,?> getKeyGenerator() throws Exception;

}

