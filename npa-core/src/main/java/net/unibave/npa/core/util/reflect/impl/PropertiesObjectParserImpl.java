package net.unibave.npa.core.util.reflect.impl;

import net.unibave.npa.core.persistence.exceptions.MissingPropertyException;
import net.unibave.npa.core.persistence.model.DeploymentDescriptorBean;
import net.unibave.npa.core.util.reflect.ReflectionLookupFacade;
import net.unibave.npa.core.util.reflect.abstracts.IInjector;
import net.unibave.npa.core.util.reflect.abstracts.ILoader;
import net.unibave.npa.core.util.reflect.abstracts.IObjectParser;
import net.unibave.npa.core.util.reflect.metainf.Property;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by wesley on 17/05/16.
 */
public class PropertiesObjectParserImpl implements IObjectParser<Map<String,Object>, Object> {

    private final Predicate<Field> justPropertyFieldsPredicate = (row) -> row.isAnnotationPresent(Property.class);

    @Override
    public Object write(final Map<String, Object> stringObjectMap, final Class<?> classToWrite) throws Exception {
        final Object returnObject = ReflectionLookupFacade.getInstance().createInstance(classToWrite);
        final List<Field> fields = ReflectionLookupFacade.getInstance().getFields(classToWrite, justPropertyFieldsPredicate);
        final Consumer<Field> consumer = (row) -> {
            final Property property = row.getAnnotation(Property.class);
            final IInjector injector;
            try {
                if (!property.nullable() && !stringObjectMap.containsKey(property.key())) {
                    throw new MissingPropertyException("The property " + property.key() + " must be set!");
                }
                injector = (IInjector) ReflectionLookupFacade.getInstance().createInstance(property.injector());
                final Object valueToInject = injector.inject(stringObjectMap, returnObject, property.key(), row);
                if (!property.nullable() && Objects.isNull(valueToInject)) {
                    throw new NullPointerException("Property "+property.key()+" must have a value!");
                }
                ReflectionLookupFacade.getInstance().setFieldValue(row,valueToInject,returnObject);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        fields.forEach(consumer);
        return returnObject;
    }

    @Override
    public Map<String, Object> read(final Object o) throws Exception {
        final Map<String,Object> properties = new HashMap<>();
        final List<Field> fields = ReflectionLookupFacade.getInstance().getFields(o.getClass(), justPropertyFieldsPredicate);
        final Consumer<Field> consumer = (row) -> {
            final Property property = row.getAnnotation(Property.class);
            final ILoader loader;
            try {
                loader = (ILoader) ReflectionLookupFacade.getInstance().createInstance(property.loader());
                final Object loadedValue = loader.load(o,row);
                if (!property.nullable() && Objects.isNull(loadedValue)) {
                    throw new NullPointerException("Property "+property.key()+" must have a value!");
                }
                properties.put(property.key(),loadedValue);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
        fields.forEach(consumer);
        return properties;
    }

}
