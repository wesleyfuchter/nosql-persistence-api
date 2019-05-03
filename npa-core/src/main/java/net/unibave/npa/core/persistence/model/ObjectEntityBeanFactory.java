package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.enumeration.BeanType;
import net.unibave.npa.core.persistence.exceptions.InvalidEntityPersistenceRuntimeException;
import net.unibave.npa.core.persistence.metainf.*;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wesley on 31/07/16.
 */
public final class ObjectEntityBeanFactory extends AbstractEntityFactory<Object> {

    @Override
    protected Object getEntityInstance() {
        return getArgument();
    }

    @Override
    protected Class<?> getEntityClass() {
        return getArgument().getClass();
    }

    @Override
    protected String getName() {
        return getRepresentativeAnnotation().name();
    }

    @Override
    protected String getComment() {
        return getRepresentativeAnnotation().comment();
    }

    @Override
    protected String getDataBase() {
        return getRepresentativeAnnotation().dataBase();
    }

    @Override
    protected EntityKeyBean getEntityKeyBean() throws Exception {
        if (getEntityClass().isAnnotationPresent(Entity.class)) {
            List<AttributeBean> list = getAttributes().stream().filter((attributeBean) -> attributeBean.getField().isAnnotationPresent(EntityKey.class)).
                    collect(Collectors.toList());
            if (list.size() <= 0) {
                throw new InvalidEntityPersistenceRuntimeException("Entity key not found");
            } else if (list.size() == 1) {
                return new EntityKeyFactoryImpl().factory(list.get(0));
            } else if (list.size() > 1) {
                throw new InvalidEntityPersistenceRuntimeException("Entity must have just one key");
            } else {
                throw new IllegalStateException("Invalid entity");
            }
        } else {
            return null;
        }
    }

    @Override
    protected List<ControllerBean> getControllers() throws Exception {
        final List<ControllerBean> controllers = new ArrayList<>();
        getGenericControllers(controllers);
        getSpecificControllers(controllers);
        if (getEntityClass().isAnnotationPresent(Controller.class)) {
            controllers.add(new GenericControllerFactory().factory(getEntityClass().getAnnotation(Controller.class)));
        }
        return controllers;
    }

    private void getSpecificControllers(List<ControllerBean> controllers) {
        Arrays.stream(getEntityClass().getAnnotations()).filter(ControllerBean::isController).forEach((annotation) -> {
            try {
                controllers.add(new SpecificControllerFactory().factory(annotation));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getGenericControllers(List<ControllerBean> controllers) {
        if (getEntityClass().isAnnotationPresent(Controllers.class)) {
            Arrays.asList(getEntityClass().getAnnotation(Controllers.class)).forEach((annotation) -> {
                Arrays.asList(annotation.value()).forEach((controller) -> {
                    try {
                        controllers.add(new GenericControllerFactory().factory(controller));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

    @Override
    protected List<ValidatorBean> getValidators() throws Exception {
        final List<ValidatorBean> validators = new ArrayList<>();
        getSpecificValidators(validators);
        getGenericValidators(validators);
        if (getEntityClass().isAnnotationPresent(Validator.class)) {
            validators.add(new GenericValidatorFactory().factory(getEntityClass().getAnnotation(Validator.class)));
        }
        return validators;
    }

    private void getSpecificValidators(List<ValidatorBean> controllers) {
        Arrays.stream(getEntityClass().getAnnotations()).filter(ValidatorBean::isValidator).forEach((annotation) -> {
            try {
                controllers.add(new SpecificValidatorFactory().factory(annotation));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getGenericValidators(List<ValidatorBean> controllers) {
        if (getEntityClass().isAnnotationPresent(Validators.class)) {
            Arrays.asList(getEntityClass().getAnnotation(Validators.class)).forEach((annotation) -> {
                Arrays.asList(annotation.value()).forEach((validator) -> {
                    try {
                        controllers.add(new GenericValidatorFactory().factory(validator));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

    @Override
    protected List<AttributeBean> getAttributes() {
        final List<AttributeBean> attributes = new ArrayList<>();
        final List<Field> fields = ReflectionLookupFacade.getInstance().getFields(getEntityClass(), (field) -> field.isAnnotationPresent(Attribute.class));
        final FieldAttributeFactory fieldAttributeFactory = new FieldAttributeFactory(getObjectFactory());
        fields.forEach((field) -> {
            try {
                final AttributeBean attributeBean = fieldAttributeFactory.factory(field);
                attributes.add(attributeBean);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return attributes;
    }

    @Override
    protected BeanType getBeanType() {
        if (getEntityClass().isAnnotationPresent(Entity.class)) {
            return BeanType.ENTITY;
        } else if (getEntityClass().isAnnotationPresent(EmbeddedEntity.class)) {
            return BeanType.EMBEDDED;
        }
        return null;
    }
}
