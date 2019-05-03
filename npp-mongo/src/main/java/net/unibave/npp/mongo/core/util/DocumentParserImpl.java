package net.unibave.npp.mongo.core.util;

import net.unibave.npa.core.persistence.metainf.EntityKey;
import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.IObjectParser;
import net.unibave.npp.mongo.core.factories.DocumentFactory;
import org.bson.Document;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by wesley on 20/05/16.
 */
public class DocumentParserImpl {

    private static DocumentParserImpl instance;

    public static DocumentParserImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DocumentParserImpl();

        }
        return instance;
    }

    private DocumentParserImpl() {

    }

    @Deprecated
    public EntityBean write(Document document, EntityBean entityBean) throws Exception {
        return null;
    }

    public static final String KEY_FIELD_NAME = DocumentFactory.DOCUMENT_KEY_PATTERN_NAME;

    public Document read(EntityBean o) throws Exception {
        return new DocumentFactory().factory(o);
    }
}
