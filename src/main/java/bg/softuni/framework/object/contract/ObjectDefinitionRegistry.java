package bg.softuni.framework.object.contract;

import java.util.Collection;

public interface ObjectDefinitionRegistry {
    void register(ObjectDefinition objectDefinition);
    ObjectDefinition getObjectDefinition(String name);
    int getObjectCount();
    Collection<ObjectDefinition> getObjectDefinitions();
}
