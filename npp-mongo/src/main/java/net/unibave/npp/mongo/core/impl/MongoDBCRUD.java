package net.unibave.npp.mongo.core.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import net.unibave.npa.core.persistence.impl.crud.AbstractCRUD;
import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.EntityKeyBean;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npp.mongo.core.factories.DocumentEntityBeanFactory;
import net.unibave.npp.mongo.core.util.DocumentParserImpl;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wesley on 18/05/16.
 */
public class MongoDBCRUD extends AbstractCRUD<EntityKeyBean,EntityBean> {

    private MongoDBPersistenceContext mongoDBPersistenceContext;

    public MongoDBCRUD(SessionBean sessionBean) {
        super(sessionBean);
        this.mongoDBPersistenceContext = (MongoDBPersistenceContext) sessionBean.getContextBean().getAbstractPersistenceContext();
    }

    @Override
    public EntityBean create(final EntityBean entityBean) throws Exception {
        final MongoCollection<Document> collection = mongoDBPersistenceContext.getDataBase().getCollection(entityBean.getName());
        collection.insertOne(DocumentParserImpl.getInstance().read(entityBean));
        return entityBean;
    }

    @Override
    public EntityBean read(final EntityKeyBean entityKeyBean) throws Exception {
        final MongoCollection<Document> collection = mongoDBPersistenceContext.getDataBase().getCollection(getSessionBean().getEntityBean().getName());
        final Bson idValue = Filters.eq(DocumentParserImpl.KEY_FIELD_NAME, entityKeyBean.getAttribute().getValue());
        final Iterator<Document> iterator = collection.find(idValue).iterator();
        if (iterator.hasNext()) {
            final Document returnDocument = iterator.next();
            final EntityBean entityBean = new DocumentEntityBeanFactory(getSessionBean().getEntityBean().getEntityClass()).factory(returnDocument);
            return entityBean;
        }
        return null;
    }

    @Override
    public List<EntityBean> read() throws Exception {
        final List<EntityBean> entityBeanList = new ArrayList<>();
        final MongoCollection<Document> collection = mongoDBPersistenceContext.getDataBase().getCollection(getSessionBean().getEntityBean().getName());
        final Iterator<Document> iterator = collection.find().iterator();
        iterator.forEachRemaining((document) -> {
            try {
                final DocumentEntityBeanFactory documentEntityBeanFactory = new DocumentEntityBeanFactory(getSessionBean().getEntityBean().getEntityClass());
                entityBeanList.add(documentEntityBeanFactory.factory(document));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return entityBeanList;
    }

    @Override
    public EntityBean update(final EntityBean entityBean) throws Exception {
        final MongoCollection<Document> collection = mongoDBPersistenceContext.getDataBase().getCollection(entityBean.getName());
        final AttributeBean attribute = entityBean.getEntityKey().getAttribute();
        final Document document = DocumentParserImpl.getInstance().read(entityBean);
        collection.replaceOne(Filters.eq(DocumentParserImpl.KEY_FIELD_NAME, attribute.getValue()),document);
        return entityBean;
    }

    @Override
    public EntityBean delete(final EntityBean entityBean) throws Exception {
        final MongoCollection<Document> collection = mongoDBPersistenceContext.getDataBase().getCollection(entityBean.getName());
        final AttributeBean attribute = entityBean.getEntityKey().getAttribute();
        collection.deleteOne(Filters.eq(DocumentParserImpl.KEY_FIELD_NAME, attribute.getValue()));
        return entityBean;
    }
}
