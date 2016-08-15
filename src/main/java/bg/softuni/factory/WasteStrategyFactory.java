package bg.softuni.factory;


import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.annotation.Inject;
import bg.softuni.framework.object.contract.ObjectContainer;

@Component
public class WasteStrategyFactory {


    @Inject
    ObjectContainer container;

    public GarbageDisposalStrategy createGarbageTypeStrategi(String typeName) throws ClassNotFoundException {

        return (GarbageDisposalStrategy) container.getInstance(Class.forName(typeName));
    }
}
