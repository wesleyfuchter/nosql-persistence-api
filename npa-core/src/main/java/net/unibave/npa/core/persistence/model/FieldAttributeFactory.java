package net.unibave.npa.core.persistence.model;

import net.unibave.npa.core.persistence.abstracts.IConverter;
import net.unibave.npa.core.persistence.metainf.*;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FieldAttributeFactory extends AbstractAttributeFactory<Field> {

    private final EntityBean entityBean;

    public FieldAttributeFactory(final EntityBean entityBean) {
        this.entityBean = entityBean;
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
    protected Field getField() {
        return getArgument();
    }

    @Override
    protected String getPrompt() {
        return getRepresentativeAnnotation().prompt();
    }

    @Override
    protected List<ControllerBean> getControllers() throws Exception {
        final List<ControllerBean> controllers = new ArrayList<>();
        getGenericControllers(controllers);
        getSpecificControllers(controllers);
        if (getField().isAnnotationPresent(Controller.class)) {
            controllers.add(new GenericControllerFactory().factory(getField().getAnnotation(Controller.class)));
        }
        return controllers;
    }

    private void getSpecificControllers(List<ControllerBean> controllers) {
        Arrays.asList(getField().getAnnotations()).stream().filter(ControllerBean::isController).forEach((annotation) -> {
            try {
                controllers.add(new SpecificControllerFactory().factory(annotation));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getGenericControllers(List<ControllerBean> controllers) {
        if (getField().getClass().isAnnotationPresent(Controllers.class)) {
            Arrays.asList(getField().getAnnotation(Controllers.class)).forEach((annotation) -> {
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
        if (getField().isAnnotationPresent(Validator.class)) {
            validators.add(new GenericValidatorFactory().factory(getField().getAnnotation(Validator.class)));
        }
        return validators;
    }

    private void getSpecificValidators(List<ValidatorBean> validators) {
        Arrays.asList(getField().getAnnotations()).stream().filter(ValidatorBean::isValidator).forEach((annotation) -> {
            try {
                validators.add(new SpecificValidatorFactory().factory(annotation));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void getGenericValidators(List<ValidatorBean> validators) {
        if (getField().isAnnotationPresent(Validators.class)) {
            Arrays.asList(getField().getAnnotation(Validators.class)).forEach((annotation) -> {
                Arrays.asList(annotation.value()).forEach((validator) -> {
                    try {
                        validators.add(new GenericValidatorFactory().factory(validator));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

    @Override
    protected EntityBean getEntityBean() {
        return entityBean;
    }

    @Override
    protected IConverter getConverter() throws Exception {
        if (getField().isAnnotationPresent(Converter.class)) {
            final Converter converter = getField().getAnnotation(Converter.class);
            final IConverter iConverter = ReflectionLookupFacade.getInstance().<IConverter>createTypedInstance(converter.implementClass());
            return iConverter;
        }
        return null;
    }

    @Override
    protected Object getValue() throws Exception {
        return ReflectionLookupFacade.getInstance().getFieldValue(getField(),getEntityBean().getEntityInstance());
    }
}
