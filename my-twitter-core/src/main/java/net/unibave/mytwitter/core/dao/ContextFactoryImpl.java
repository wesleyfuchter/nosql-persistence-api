package net.unibave.mytwitter.core.dao;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.IContextFactory;
import net.unibave.npa.core.persistence.impl.PersistenceContextFactory;

import java.util.Objects;

/**
 * Created by wesley on 31/05/16.
 */
public class ContextFactoryImpl implements IContextFactory {

    private static AbstractPersistenceContext abstractPersistenceContext;

    @Override
    public AbstractPersistenceContext factory() throws Exception {
        if (Objects.isNull(abstractPersistenceContext)) {
            abstractPersistenceContext = new
                    PersistenceContextFactory().factory("twitterUnit", "/home/wesley/Documentos/tcc/npa-context.properties");
        }
        return abstractPersistenceContext;
    }

}
