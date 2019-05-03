/**
 *
 */
package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.common.abstracts.IFactory;
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
 * @author wesley
 */
@Deprecated
public class AttributeBeanFactory implements IFactory<Field, AttributeBean> {

    private final EntityBean entityBean;
    private final Object entityInstance;
    private List<ControllerBean> controllers;
    private List<ValidatorBean> validators;
    private Field field;

    public AttributeBeanFactory(EntityBean entityBean) {
        this.entityInstance = entityBean.getEntityInstance();
        this.entityBean = entityBean;
    }

    @Override
    public AttributeBean factory(final Field e) throws Exception {
        setField(e);
        final AttributeBean attributeBean = new AttributeBean();
        final Attribute attributeAnnotation = e.getAnnotation(Attribute.class);
        attributeBean.setName(attributeAnnotation.name());
        attributeBean.setComment(attributeAnnotation.comment());
        attributeBean.setPrompt(attributeAnnotation.prompt());
        attributeBean.setField(e);
        attributeBean.setControllers(getControllers());
        attributeBean.setValidators(getValidators());
        attributeBean.setEntityBean(this.entityBean);
        final Object value = ReflectionLookupFacade.getInstance().getFieldValue(e, entityInstance);
        if (!Objects.isNull(value)) {
            attributeBean.setValue(value);
        }
        return attributeBean;
    }

    /**
     * @return the controllers
     * @throws Exception
     */
    public List<ControllerBean> getControllers() throws Exception {
        if (Objects.isNull(controllers)) {
            final List<ControllerBean> controllers = new ArrayList<>();
            final ControllerBeanFactory controllerBeanFactory = new ControllerBeanFactory();
            if (getField().isAnnotationPresent(Controller.class)) {
                ControllerBean controller = null;
                controller = controllerBeanFactory.factory(getField().getAnnotation(Controller.class));
                if (!Objects.isNull(controller)) {
                    controllers.add(controller);
                }
            } else if (getField().isAnnotationPresent(Controllers.class)) {
                Arrays.asList(getField().getAnnotation(Controllers.class).value()).stream().forEach((row) -> {
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
            final List<Annotation> annotations = Arrays.asList(getField().getAnnotations()).stream().filter(annotationFilter).collect(Collectors.toList());
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

    /**
     * @param controllers the controllers to set
     */
    public void setControllers(List<ControllerBean> controllers) {
        this.controllers = controllers;
    }

    /**
     * @return the validators
     */
    public List<ValidatorBean> getValidators() throws Exception {
        if (Objects.isNull(this.validators)) {
            final List<ValidatorBean> validators = new ArrayList<>();
            final ValidatorBeanFactory validatorBeanFactory = new ValidatorBeanFactory();
            if (getField().isAnnotationPresent(Validator.class)) {
                ValidatorBean validator = null;
                validator = validatorBeanFactory.factory(getField().getAnnotation(Validator.class));
                if (!Objects.isNull(validator)) {
                    validators.add(validator);
                }
            } else if (getField().isAnnotationPresent(Validators.class)) {
                Arrays.asList(getField().getAnnotation(Validators.class).value()).stream().forEach((row) -> {
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
            final List<Annotation> annotations = Arrays.asList(getField().getAnnotations()).stream().filter(annotationFilter).collect(Collectors.toList());
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

    /**
     * @param validators the validators to set
     */
    public void setValidators(List<ValidatorBean> validators) {
        this.validators = validators;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void setField(Field field) {
        this.field = field;
    }

}
