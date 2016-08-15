package bg.softuni.framework.object.component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import bg.softuni.framework.object.contract.ObjectDefinition;
import bg.softuni.framework.object.contract.ObjectDefinitionRegistry;

public class ComponentDefinitionsRegistry implements ObjectDefinitionRegistry {
    Map<String, ObjectDefinition> componentDefinitions;

    public ComponentDefinitionsRegistry() {
        componentDefinitions = new ConcurrentHashMap<String, ObjectDefinition>();
    }

    public void register(ObjectDefinition componentDefinition) {
        ObjectDefinition cd = componentDefinitions.get(componentDefinition.getName());

        if (cd != null && componentDefinition.getObjectClass().equals(cd.getClass())) {
            throw new IllegalArgumentException("Component with name " + componentDefinition.getName() + "already exists!");
        }

        componentDefinitions.put(componentDefinition.getName(), componentDefinition);
    }

    public ObjectDefinition getObjectDefinition(String name) {
        return componentDefinitions.get(name);
    }

    public int getObjectCount() {
        return componentDefinitions.size();
    }

    public Collection<ObjectDefinition> getObjectDefinitions() {
        return Collections.unmodifiableCollection(componentDefinitions.values());
    }
}
