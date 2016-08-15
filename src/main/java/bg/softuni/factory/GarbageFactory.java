package bg.softuni.factory;

import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.Waste;
import bg.softuni.framework.annotation.Component;
import bg.softuni.model.Garbage;

@Component
public class GarbageFactory {

    public Garbage createGarbage(Waste waste, GarbageDisposalStrategy type) {
        return new Garbage(waste, type);
    }
}
