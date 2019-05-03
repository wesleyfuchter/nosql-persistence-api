package net.unibave.npa.core.util.reflect;

import net.unibave.npa.core.util.reflect.abstracts.IObjectParser;
import net.unibave.npa.core.util.reflect.impl.PropertiesObjectParserImpl;
import net.unibave.npa.core.util.text.TextLookupFacade;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author wesley
 * */
public class ReflectionLookupFacade {

    private volatile static ReflectionLookupFacade instance;

    public static ReflectionLookupFacade getInstance() {
        if (instance == null) {
            synchronized (ReflectionLookupFacade.class) {
                if (instance == null) {
                    instance = new ReflectionLookupFacade();
                }
            }
        }
        return instance;
    }

    private ReflectionLookupFacade() {

    }

    public List<Field> getFields(Class<?> clazz) {
        List<Field> returnList = new ArrayList<Field>();
        returnList.addAll(Arrays.asList(clazz.getFields()));
        returnList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        return returnList;
    }

    public List<Field> getFields(Class<?> clazz, Predicate<? super Field> filter) {
        return getFields(clazz).stream().filter(filter).collect(Collectors.toList());
    }

    public List<Method> getMethods(Class<?> clazz) {
        List<Method> returnList = new ArrayList<>();
        returnList.addAll(Arrays.asList(clazz.getDeclaredMethods()));
        return returnList;
    }

    public List<Method> getMethods(Class<?> clazz, Predicate<? super Method> filter) {
        return getMethods(clazz).stream().filter(filter).collect(Collectors.toList());
    }

    public Object setFieldValue(Field field, Object value, Object values) throws InvocationTargetException, Exception {
        if (field.isAccessible()) {
            field.set(values, value);
        } else {
            Method setter = getSetter(values.getClass(), field);
            if (!Objects.isNull(setter)) {
                setter.invoke(values, value);
            } else {
                throw new IllegalArgumentException("Method set not found!");
            }
        }
        return values;
    }

    public Object getFieldValue(Field field, Object values, Boolean force) throws InvocationTargetException, Exception {
        Object value = null;
        Method getter = getGetter(values.getClass(), field);
        if (Objects.isNull(getter)) {
            if (field.isAccessible()) {
                value = field.get(values);
            } else {
                if (force) {
                    field.setAccessible(true);
                    try {
                        value = field.get(values);
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        field.setAccessible(false);
                    }
                } else {
                    throw new IllegalAccessException();
                }
            }
        } else {
            value = getter.invoke(values);
        }
        return value;
    }

    public Object setFieldValue(Field field, Object value, Object values, Boolean force) throws InvocationTargetException, Exception {
        Method setter = getSetter(values.getClass(), field);
        if (Objects.isNull(setter)) {
            if (field.isAccessible()) {
                field.set(values, value);
            } else {
                if (force) {
                    field.setAccessible(true);
                    try {
                        field.set(values, value);
                    } catch (Exception e) {
                        throw e;
                    } finally {
                        field.setAccessible(false);
                    }
                } else {
                    throw new IllegalAccessException();
                }
            }
        } else {
            setter.invoke(values, value);
        }
        return values;
    }

    public Object getFieldValue(Field field, Object values) throws InvocationTargetException, Exception {
        return getFieldValue(field, values, false);
    }

    public Object getFieldValue(String fieldName, Object values) throws InvocationTargetException, Exception {
        return getFieldValue(fieldName, values, false);
    }

    public Object getFieldValue(String fieldName, Object values, Boolean force) throws InvocationTargetException, Exception {
        Field field = ReflectionLookupFacade.getInstance().getField(values.getClass(), fieldName);
        return getFieldValue(field, values);
    }

    public Field getField(Class<?> clazz, String name) {
        List<Field> fields = getFields(clazz).stream().filter((row) -> row.getName().equals(name)).collect(Collectors.toList());
        if (fields.size() > 0) {
            return fields.get(0);
        } else {
            throw new IllegalArgumentException("Field not found!");
        }
    }

    public Method getGetter(Class<?> clazz, Field field) throws Exception {
        return getGetter(clazz, field.getName());
    }

    public Method getGetter(Class<?> clazz, String fieldName) throws Exception {
        if (isFieldExists(clazz, fieldName)) {
            for (Method actualMethod : clazz.getMethods()) {
                if (isGetter(actualMethod) && actualMethod.getName().endsWith(TextLookupFacade.getInstance().setFirstToUpper(fieldName))) {
                    return actualMethod;
                }
            }
        }
        throw new Exception("Method not found!");
    }

    public Method getSetter(Class<?> clazz, Field field) throws Exception {
        return getSetter(clazz, field.getName());
    }

    public Method getSetter(Class<?> clazz, String fieldName) throws Exception {
        if (isFieldExists(clazz, fieldName)) {
            for (Method actualMethod : clazz.getMethods()) {
                if (isSetter(actualMethod) && actualMethod.getName().endsWith(TextLookupFacade.getInstance().setFirstToUpper(fieldName))) {
                    return actualMethod;
                }
            }
        }
        throw new Exception("Method not found!");
    }

    public Boolean isGetter(Method method) {
        return ((method.getName().startsWith("get") &&
                method.getParameters().length == 0 &&
                !method.getReturnType().equals(void.class)));
    }

    public Boolean isSetter(Method method) {
        return ((method.getName().startsWith("set") &&
                method.getReturnType().equals(void.class) &&
                method.getParameters().length > 0));
    }

    public Boolean isFieldExists(Class<?> clazz, String field) {
        return !Objects.isNull(getField(clazz, field));
    }

    public Constructor<?> getConstructor(Class<?> clazz, Object... args) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameters = constructor.getParameterTypes();
            if (parameters.length == args.length) {

                Boolean constr = true;
                for (Integer control = 0; control < args.length && constr; control++) {
                    constr = parameters[control].isInstance(args[control]);
                }
                if (constr) {
                    return constructor;
                }
                return constructor;
            }
        }
        return null;
    }

    public Constructor<?> getConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            Class<?>[] parameters = constructor.getParameterTypes();
            if (parameters.length == 0) {
                return constructor;
            }
        }
        return null;
    }

    public Boolean isDefaultConstructor(Constructor<?> constructor) {
        return constructor.getParameters().length == 0 && constructor.getModifiers() == Modifier.PUBLIC;
    }

    public Object createInstance(Class<?> clazz) throws Exception {
        List<Constructor<?>> list = Arrays.asList(clazz.getConstructors()).stream().filter(this::isDefaultConstructor).collect(Collectors.toList());
        if (list.size() > 0) {
            return list.get(0).newInstance();
        }
        throw new IllegalArgumentException("Default constructor is overwritten!");
    }

    public Object createInstance(Class<?> clazz, Object... args) throws Exception {
        return getConstructor(clazz, args).newInstance(args);
    }

    public <T> T createTypedInstance(Class<?> clazz, Object... args) throws Exception {
        return (T) createInstance(clazz,args);
    }

    public <T> T createTypedInstance(Class<?> clazz) throws Exception {
        return (T) createInstance(clazz);
    }


    public StackTraceElement getCallerStackTraceElement(String actualClass) {
        List<StackTraceElement> stackTrace = Arrays.asList(Thread.currentThread().getStackTrace());
        StackTraceElement element = null;
        for (StackTraceElement actualElement : stackTrace) {
            if (actualElement.getClassName().equals(actualClass)) {
                element = stackTrace.get(stackTrace.indexOf(actualElement) - 1);
            }
        }
        return element;
    }

    public String getCallerClassName(String actualClass) {
        return getCallerStackTraceElement(actualClass).getClassName();
    }

    public Class<?> getCallerClass(String actualClass) throws ClassNotFoundException {
        return Class.forName(getCallerStackTraceElement(actualClass).getClassName());
    }

    public Boolean checkGenericType(Class<?> classToVerify, Class<?> verifyClass, Integer genericIndex) {
        Class<?> genericTypeClass = getGenericClazz(verifyClass, genericIndex);
        return genericTypeClass.getClass().equals(classToVerify.getClass());
    }

    @Deprecated
    public Class<?> getGenericClazz(Class<?> clazz, Integer position) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        return (Class<?>) parameterizedType.getActualTypeArguments()[position];
    }

    public <T> Class<T> getGenericClass(Class<?> clazz, Integer position) {
        ParameterizedType parameterizedType = (ParameterizedType) clazz.getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[position];
    }

    public <T> Class<T> getGenericClass(Class<?> clazz) {
        return this.<T>getGenericClass(clazz,0);
    }

    @Deprecated
    public Class<?> getGenericClazz(Class<?> clazz) {
        return getGenericClazz(clazz, 0);
    }

    private IObjectParser<Map<String,Object>, Object> parser;

    public IObjectParser<Map<String, Object>, Object> getParser() {
        if (Objects.isNull(parser)) {
            parser = new PropertiesObjectParserImpl();
        }
        return parser;
    }

    public void setParser(IObjectParser<Map<String, Object>, Object> parser) {
        this.parser = parser;
    }

    public Map<String,Object> objectToProperties(final Object object) throws Exception {
        return getParser().read(object);
    }

    public Object propertiesToObject(final Map<String,Object> properties, final Class<?> objectClass) throws Exception {
        return getParser().write(properties,objectClass);
    }

}
