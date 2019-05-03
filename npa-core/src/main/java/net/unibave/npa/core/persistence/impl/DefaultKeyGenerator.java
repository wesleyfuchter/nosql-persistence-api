package net.unibave.npa.core.persistence.impl;

import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.model.SessionBean;

public class DefaultKeyGenerator implements IKeyGenerator<Object, Object> {

    @Override
    public Object generate(Object o, SessionBean sessionBean) throws Exception {
        final IKeyGenerator keyGeneratorInstance = sessionBean.getAbstractPersistenceProvider().getKeyGeneratorInstance();
        if (!(keyGeneratorInstance instanceof DefaultKeyGenerator)) {
            final Object value = keyGeneratorInstance.generate(o, sessionBean);
            return value;
        }
        throw new IllegalArgumentException("Class "+DefaultKeyGenerator.class.getName()+" can't referenced by provider!");
    }
}
