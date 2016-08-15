package bg.softuni.framework.object.component;

import bg.softuni.framework.object.contract.ObjectDefinition;

public class ComponentDefinition implements ObjectDefinition {
    private String name;
    private Class<?> componentClass;

    public ComponentDefinition(Class<?> componentClass) {
        this(null, componentClass);
    }

    public ComponentDefinition(String name, Class<?> componentClass) {
        this.name = (name == null || name.equals("")) ? componentClass.getSimpleName() : name;
        this.componentClass = componentClass;
    }

    public Class<?> getObjectClass() {
        return componentClass;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "name: " + name +  "; class: " + componentClass;
    }
}
