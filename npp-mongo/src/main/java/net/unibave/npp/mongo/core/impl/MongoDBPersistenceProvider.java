/**
 * 
 */
package net.unibave.npp.mongo.core.impl;

import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceContext;
import net.unibave.npa.core.persistence.abstracts.AbstractPersistenceProvider;
import net.unibave.npa.core.persistence.abstracts.AbstractProviderCrud;
import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;

/**
 * @author wesley
 *
 */
public class MongoDBPersistenceProvider extends AbstractPersistenceProvider {

	@Override
	public Class<? extends AbstractPersistenceContext> getContextClass() throws Exception {
		return MongoDBPersistenceContext.class;
	}

	@Override
	public Class<? extends AbstractProviderCrud> getCrudImplClass() throws Exception {
		return null;
	}

	@Override
	public Class<? extends net.unibave.npa.core.persistence.impl.crud.AbstractCRUD<?, ?>> getCRUD() throws Exception {
		return MongoDBCRUD.class;
	}

	@Override
	public Class<? extends IKeyGenerator> getKeyGenerator() throws Exception {
		return MongoDBKeyGenerator.class;
	}
}
