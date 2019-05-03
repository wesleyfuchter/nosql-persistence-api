package net.unibave.npp.mongo.core.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import net.unibave.npa.core.persistence.abstracts.IKeyGenerator;
import net.unibave.npa.core.persistence.model.SessionBean;
import net.unibave.npp.mongo.core.util.DocumentParserImpl;
import org.bson.Document;

import java.math.BigDecimal;

/**
 * Created by wesley on 10/10/16.
 */
public class MongoDBKeyGenerator implements IKeyGenerator<Long, Object> {

    private MongoDatabase getMongoDatabase(SessionBean sessionBean) {
        return ((MongoDBPersistenceContext) sessionBean.getContextBean().getAbstractPersistenceContext()).getDataBase();
    }

    @Override
    public Long generate(Object o, SessionBean sessionBean) throws Exception {
        MongoCollection<Document> collection = getMongoDatabase(sessionBean).getCollection(sessionBean.getEntityBean().getName());
        FindIterable<Document> limit = collection.find().sort(Filters.eq(DocumentParserImpl.KEY_FIELD_NAME, -1)).limit(1);
        if (limit.iterator().hasNext()) {
            Object maxId = limit.iterator().next().get("_id");
            return Long.parseLong(maxId.toString()) + 1l;
        }
        return 1l;
    }
}
