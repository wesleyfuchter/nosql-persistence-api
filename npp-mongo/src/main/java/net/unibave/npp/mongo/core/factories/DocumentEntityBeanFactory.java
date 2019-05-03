package net.unibave.npp.mongo.core.factories;

import net.unibave.npa.core.persistence.enumeration.BeanType;
import net.unibave.npa.core.persistence.metainf.OneToMany;
import net.unibave.npa.core.persistence.metainf.OneToOne;
import net.unibave.npa.core.persistence.model.*;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import org.bson.Document;

import javax.print.Doc;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public final class DocumentEntityBeanFactory extends AbstractEntityFactory<Document> {

    private final EntityBean entityBean;

    public DocumentEntityBeanFactory(final Class<?> entityClass) throws Exception {
        this.entityBean = new ObjectEntityBeanFactory().factory(ReflectionLookupFacade.getInstance().createInstance(entityClass));
    }

    @Override
    protected Object getEntityInstance() throws Exception {
        return entityBean.getEntityInstance();
    }

    @Override
    protected Class<?> getEntityClass() {
        return entityBean.getEntityClass();
    }

    @Override
    protected String getName() {
        return entityBean.getName();
    }

    @Override
    protected String getComment() {
        return entityBean.getComment();
    }

    @Override
    protected String getDataBase() {
        return entityBean.getDataBase();
    }

    @Override
    protected EntityKeyBean getEntityKeyBean() throws Exception {
        return entityBean.getEntityKey();
    }

    @Override
    protected List<ControllerBean> getControllers() throws Exception {
        return entityBean.getControllers();
    }

    @Override
    protected List<ValidatorBean> getValidators() throws Exception {
        return entityBean.getValidators();
    }

    @Override
    protected List<AttributeBean> getAttributes() {
        final List<AttributeBean> attributes = entityBean.getAttributes();
        attributes.forEach((attribute) -> {
            try {
                final Object value = getDocumentFieldValue(attribute);
                attribute.setValue(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return attributes;
    }

    private Object getDocumentFieldValue(final AttributeBean attributeBean) throws Exception {
        final Object documentValue = getArgument().get(attributeBean.getName());
        if (attributeBean.getField().isAnnotationPresent(OneToOne.class)) {
            DocumentEntityBeanFactory documentEntityBeanFactory = new DocumentEntityBeanFactory(attributeBean.getField().getType());
            EntityBean entityBean = documentEntityBeanFactory.factory((Document) documentValue);
            return entityBean.getEntityInstance();
        } else if (attributeBean.getField().isAnnotationPresent(OneToMany.class)) {
            if (documentValue instanceof List) {
                List<Document> documents = (List) documentValue;
                ParameterizedType parameterizedType = (ParameterizedType) attributeBean.getField().getGenericType();
                Class<?> genericClass = (Class) parameterizedType.getActualTypeArguments()[0];
                final List<Object> returnList = new ArrayList<>();
                documents.forEach((documet) -> {
                    try {
                        DocumentEntityBeanFactory documentEntityBeanFactory = new DocumentEntityBeanFactory(genericClass);
                        EntityBean entityBean = documentEntityBeanFactory.factory(documet);
                        returnList.add(entityBean.getEntityInstance());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
                return returnList;
            } else {
                throw new IllegalArgumentException("The OneToMany must be a instance of "+List.class.getName());
            }
        } else {
            return documentValue;
        }
    }

    @Override
    protected BeanType getBeanType() {
        return entityBean.getBeanType();
    }
}
