package bg.softuni.strategy.model;

import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.ProcessingData;
import bg.softuni.contract.Waste;
import bg.softuni.framework.annotation.Component;

@Component
public class BurnableWaste implements GarbageDisposalStrategy {


    @Override
    public ProcessingData processGarbage(Waste garbage) {
        return null;
    }

}
