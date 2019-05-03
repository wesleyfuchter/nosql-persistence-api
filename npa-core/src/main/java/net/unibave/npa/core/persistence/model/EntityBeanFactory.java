/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
import net.unibave.npa.core.persistence.exceptions.InvalidEntityPersistenceRuntimeException;
import net.unibave.npa.core.persistence.metainf.*;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Factories a <code> EntityBean </code>
 *
 * @author wesley
 */
@Deprecated
public class EntityBeanFactory implements IFactory<Object, EntityBean> {

    private Class<?> entityClass;
    private EntityBean entityBean;
    private Entity entityAnnotation;
    private Object arg;
    private EntityKeyBean entityKey;
    private List<ControllerBean> controllers;
    private List<ValidatorBean> validators;
    private List<AttributeBean> attributes;

    @Override
    public EntityBean factory(final Object e) throws Exception {
        setArg(e);
        entityBean = new EntityBean();
        entityBean.setEntityClass(getEntityClass());
        entityBean.setEntityInstance(getArg());
        entityBean.setName(getEntityAnnotation().name());
        entityBean.setComment(getEntityAnnotation().comment());
        entityBean.setDataBase(getEntityAnnotation().dataBase());
        entityBean.setControllers(getControllers());
        entityBean.setValidators(getValidators());
        entityBean.setAttributes(getAttributes());
        entityBean.setEntityKey(getEntityKey());
        return entityBean;
    }

    public Class<?> getEntityClass() {
        if (Objects.isNull(entityClass)) {
            entityClass = arg.getClass();
        }
        return entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityBean getEntityBean() {
        return entityBean;
    }

    public void setEntityBean(EntityBean entityBean) {
        this.entityBean = entityBean;
    }

    public Entity getEntityAnnotation() {
        if (Objects.isNull(entityAnnotation)) {
            if (entityClass.isAnnotationPresent(Entity.class)) {
                entityAnnotation = entityClass.getAnnotation(Entity.class);
            } else {
                throw new InvalidEntityPersistenceRuntimeException(InvalidEntityPersistenceRuntimeException.DEFAULT_ERR_MESSAGE);
            }
        }
        return entityAnnotation;
    }

    public void setEntityAnnotation(Entity entityAnnotation) {
        this.entityAnnotation = entityAnnotation;
    }

    public Object getArg() {
        return arg;
    }

    public void setArg(Object arg) {
        this.arg = arg;
    }

    public EntityKeyBean getEntityKey() throws Exception {
        if (Objects.isNull(entityKey)) {
            EntityKeyBean entityKeyBean = null;
            final EntityKeyBeanFactory entityKeyBeanFactory = new EntityKeyBeanFactory();
            for (AttributeBean row : getAttributes()) {
                if (row.getField().isAnnotationPresent(EntityKey.class)) {
                    entityKeyBean = entityKeyBeanFactory.factory(row);
                    break;
                }
            }
            this.entityKey = entityKeyBean;
        }
        return entityKey;
    }

    public void setEntityKey(EntityKeyBean entityKey) {
        this.entityKey = entityKey;
    }

    public List<ControllerBean> getControllers() throws Exception {
        if (Objects.isNull(this.controllers)) {
            final List<ControllerBean> controllers = new ArrayList<>();
            final ControllerBeanFactory controllerBeanFactory = new ControllerBeanFactory();
            if (entityClass.isAnnotationPresent(Controller.class)) {
                ControllerBean controller = null;
                controller = controllerBeanFactory.factory(entityClass.getAnnotation(Controller.class));
                if (!Objects.isNull(controller)) {
                    controllers.add(controller);
                }
            } else if (entityClass.isAnnotationPresent(Controllers.class)) {
                Arrays.asList(entityClass.getAnnotation(Controllers.class).value()).stream().forEach((row) -> {
                    try {
                        ControllerBean controller = controllerBeanFactory.factory(row);
                        if (!Objects.isNull(controller)) {
                            controllers.add(controller);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            final Predicate<Annotation> annotationFilter = (row) -> row.annotationType().isAnnotationPresent(ControllerRule.class);
            final List<Annotation> annotations = Arrays.asList(entityClass.getAnnotations()).stream().filter(annotationFilter).collect(Collectors.toList());
            annotations.forEach((row) -> {
                ControllerBean controller = null;
                try {
                    final Class<? extends Function<Annotation, Controller>> parserImplement =
                            row.annotationType().getAnnotation(ControllerRule.class).parserImplement();
                    final Function<Annotation, Controller> parserInstance = parserImplement.newInstance();
                    controller = controllerBeanFactory.factory(parserInstance.apply(row));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                controllers.add(controller);
            });
            this.controllers = controllers;
        }
        return controllers;
    }

    public void setControllers(List<ControllerBean> controllers) {
        this.controllers = controllers;
    }

    public List<ValidatorBean> getValidators() throws Exception {
        if (Objects.isNull(this.validators)) {
            final List<ValidatorBean> validators = new ArrayList<>();
            final ValidatorBeanFactory validatorBeanFactory = new ValidatorBeanFactory();
            if (entityClass.isAnnotationPresent(Validator.class)) {
                ValidatorBean validator = null;
                validator = validatorBeanFactory.factory(entityClass.getAnnotation(Validator.class));
                if (!Objects.isNull(validator)) {
                    validators.add(validator);
                }
            } else if (entityClass.isAnnotationPresent(Validators.class)) {
                Arrays.asList(entityClass.getAnnotation(Validators.class).value()).stream().forEach((row) -> {
                    try {
                        ValidatorBean validator = validatorBeanFactory.factory(row);
                        if (!Objects.isNull(validator)) {
                            validators.add(validator);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
            final Predicate<Annotation> annotationFilter = (row) -> row.annotationType().isAnnotationPresent(ValidatorRule.class);
            final List<Annotation> annotations = Arrays.asList(entityClass.getAnnotations()).stream().filter(annotationFilter).collect(Collectors.toList());
            annotations.forEach((row) -> {
                ValidatorBean validator = null;
                try {
                    final Class<? extends Function<Annotation, Validator>> parserImplement =
                            row.annotationType().getAnnotation(ValidatorRule.class).parserImplement();
                    final Function<Annotation, Validator> parserInstance = parserImplement.newInstance();
                    validator = validatorBeanFactory.factory(parserInstance.apply(row));
                    if (!Objects.isNull(validator)) {
                        validators.add(validator);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            this.validators = validators;
        }
        return validators;
    }

    public void setValidators(List<ValidatorBean> validators) {
        this.validators = validators;
    }

    public List<AttributeBean> getAttributes() {
        if (Objects.isNull(this.attributes)) {
            final List<AttributeBean> attributes = new ArrayList<>();
            final Predicate<Field> filterAttributes = (row) -> row.isAnnotationPresent(Attribute.class);
            final List<Field> fields = ReflectionLookupFacade.getInstance().getFields(entityClass,filterAttributes);
            final AttributeBeanFactory attributeBeanFactory = new AttributeBeanFactory(this.getEntityBean());
            fields.forEach((row) -> {
                try {
                    AttributeBean attributeBean;
                    attributeBean = attributeBeanFactory.factory(row);
                    if (!Objects.isNull(attributeBean)) {
                        attributes.add(attributeBean);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            this.attributes = attributes;
        }
        return attributes;
    }

    public void setAttributes(List<AttributeBean> attributes) {
        this.attributes = attributes;
    }

}
