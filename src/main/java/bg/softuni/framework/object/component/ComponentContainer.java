package bg.softuni.framework.object.component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import bg.softuni.framework.annotation.Inject;
import bg.softuni.framework.object.contract.AnnotationScanner;
import bg.softuni.framework.object.contract.ObjectContainer;
import bg.softuni.framework.object.contract.ObjectDefinition;
import bg.softuni.framework.object.contract.ObjectDefinitionRegistry;


public class ComponentContainer implements ObjectContainer {
    private Map<Class<?>, Object> instances;
    private Map<Class<?>, Class<?>> mapping;

    private ObjectDefinitionRegistry registry;

    public ComponentContainer() {
        this(new ComponentScanner(new ComponentDefinitionsRegistry()));
    }

    public ComponentContainer(AnnotationScanner scanner) {
        this.registry = scanner.getRegistry();

        instances = new ConcurrentHashMap<Class<?>, Object>();
        mapping = new ConcurrentHashMap<Class<?>, Class<?>>();

        scanner.scan("src/main/java/", "bg.softuni");

        instances.put(ComponentContainer.class, this);
        mapping.put(ObjectContainer.class, ComponentContainer.class);
        init();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> t) {
        Class<T> t1 = t;
        if (t.isInterface()) {
            t1 = (Class<T>) mapping.get(t);
        }

        return (T) instances.get(t1);
    }


    private void init() {
        Queue<ObjectDefinition> components = new ConcurrentLinkedQueue<ObjectDefinition>();
        components.addAll(registry.getObjectDefinitions());

        ComponentDefinition temp = new ComponentDefinition("temp", String.class);
        int counter = components.size();
        components.add(temp);


        while(!components.isEmpty()) {
            if(components.size() == 1) return;

            ObjectDefinition componentDefinition = components.poll();

            if (temp.getName().equals(componentDefinition.getName())) {
                if (counter == components.size()) {
                    throw new RuntimeException("Circle dependencies found:" + components.toString());
                }

                counter = components.size();
                components.add(temp);
            }

            if (!initializeComponent(componentDefinition)) {
                components.add(componentDefinition);
            }
        }
    }

    private boolean initializeComponent(ObjectDefinition componentDefinition) {
        Class<?> compClass = componentDefinition.getObjectClass();

        Constructor<?> ctor;
        try {
            ctor = compClass.getConstructor();
        } catch (NoSuchMethodException | SecurityException e) {
            throw new RuntimeException(e);
        }

        ctor.setAccessible(true);

        Object instance;
        try {
            instance = ctor.newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        if (!injectFields(compClass, instance)) return false;

        instances.put(compClass, instance);

        return true;
    }

    private boolean injectFields(Class<?> compClass, Object instance) {
        Field exeClassField[] = compClass.getDeclaredFields();

        if (!injectFields(exeClassField, instance)) return false;

        if (compClass.getSuperclass() != null) {
            if (!injectFields(compClass.getSuperclass(), instance)) return false;
        }

        return true;
    }

    private boolean injectFields(Field exeClassField[], Object instance) {
        for (Field field : exeClassField) {
            if (!field.isAnnotationPresent(Inject.class)) {
                continue;
            }

            field.setAccessible(true);
            Class<?> fieldClass = field.getType();
            Object impl = null;

            if (fieldClass.isInterface()) {
                Class<?> classType = mapping.get(field.getType());

                if (classType == null) {
                    impl = autowire(fieldClass);

                    if (impl == null) {
                        return false;
                    }

                    mapping.put(fieldClass, impl.getClass());
                } else {
                    impl = instances.get(classType);
                }

            } else {
                impl = instances.get(fieldClass);

                if (impl == null) {
                    return false;
                }
            }

            try {
                field.set(instance, impl);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }

        return true;
    }

    private Object autowire(Class<?> interf) {
        Object obj = null;

        for (Class<?> c : instances.keySet()) {
            Class<?> [] interfaces = c.getInterfaces();

            for (Class<?> class1 : interfaces) {
                if (class1.equals(interf)) {
                    if (obj != null) {
                        throw new IllegalArgumentException("There is more than one implementation of " + interf);
                    }
                    obj = instances.get(c);
                }
            }
        }

        return obj;
    }
}
