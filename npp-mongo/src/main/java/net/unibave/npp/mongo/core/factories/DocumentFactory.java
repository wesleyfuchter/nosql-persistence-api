package net.unibave.npp.mongo.core.factories;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.metainf.EntityKey;
import net.unibave.npa.core.persistence.metainf.OneToMany;
import net.unibave.npa.core.persistence.metainf.OneToOne;
import net.unibave.npa.core.persistence.model.AbstractEntityFactory;
import net.unibave.npa.core.persistence.model.AttributeBean;
import net.unibave.npa.core.persistence.model.EntityBean;
import net.unibave.npa.core.persistence.model.ObjectEntityBeanFactory;
import org.bson.Document;

import java.util.*;

public class DocumentFactory implements IFactory<EntityBean, Document> {

    public static final String DOCUMENT_KEY_PATTERN_NAME = "_id";

    @Override
    public Document factory(EntityBean entityBean) throws Exception {
        final Document document = new Document();
        document.putAll(getMapFromEntity(entityBean.getAttributes()));
        return document;
    }

    private Map<String,Object> getMapFromEntity(List<AttributeBean> attributes) {
        final Map<String, Object> returnMap = new HashMap<>();
        attributes.forEach((attributeBean) -> {
            try {
                returnMap.put(getKeyFromAttribute(attributeBean), getValueFromAttribute(attributeBean));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return returnMap;

    }

    private String getKeyFromAttribute(AttributeBean attributeBean) {
        if (attributeBean.getField().isAnnotationPresent(EntityKey.class)) {
            return DOCUMENT_KEY_PATTERN_NAME;
        } else {
            return attributeBean.getName();
        }
    }

    private Object getValueFromAttribute(AttributeBean attributeBean) throws Exception {
        if (attributeBean.getField().isAnnotationPresent(OneToOne.class)) {
            return getMapFromEntity(getEmbeddebEntityAttributes(attributeBean));
        } else if (attributeBean.getField().isAnnotationPresent(OneToMany.class)) {
            final List<Map<String, Object>> mapList = new ArrayList<>();
            getEmbeddebEntityList(attributeBean).forEach((row) -> {
                mapList.add(getMapFromEntity(row.getAttributes()));
            });
            return mapList;
        }
        return attributeBean.getValue();
    }

    private List<AttributeBean> getEmbeddebEntityAttributes(AttributeBean attributeBean) throws Exception {
        final AbstractEntityFactory<Object> objectEntityBeanFactory = new ObjectEntityBeanFactory();
        final EntityBean entityBean = objectEntityBeanFactory.factory(attributeBean.getValue());
        return entityBean.getAttributes();
    }

    private List<EntityBean> getEmbeddebEntityList(AttributeBean attributeBean) throws Exception {
        final List<EntityBean> entityBeanList = new ArrayList<>();
        if (attributeBean.getField().getType().isAssignableFrom(List.class)) {
            List collection = (List) attributeBean.getValue();
            collection.forEach((row) -> {
                final AbstractEntityFactory<Object> objectEntityBeanFactory = new ObjectEntityBeanFactory();
                try {
                    entityBeanList.add(objectEntityBeanFactory.factory(row));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        } else {
            throw new IllegalArgumentException("The OneToMany must be a instance of "+List.class.getName());
        }
        return entityBeanList;
    }



}
