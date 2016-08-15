package bg.softuni.service;

import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.Repository;
import bg.softuni.contract.Service;
import bg.softuni.contract.Waste;
import bg.softuni.factory.GarbageFactory;
import bg.softuni.factory.WasteStrategyFactory;
import bg.softuni.framework.annotation.Component;
import bg.softuni.framework.annotation.Inject;
import bg.softuni.model.Garbage;
import bg.softuni.model.WasteImpl;

@Component
public class ServiceImpl implements Service {
    @Inject
    private Repository garbageRepository;
    @Inject
    WasteStrategyFactory wasteStrategyFactory;
    @Inject
    GarbageFactory garbageFactory;


    private double energy;
    private double capital;


    @Override
    public String getStatusOfStation() {

        return String.format("Energy: %.2f Capital: %.2f", this.getEnergyBalance(), this.getCapitalBalance());
    }

    @Override
    public String addNewGarbage(String command) {
        String commandInfo[] = command.split("\\|");
        String wasteName = commandInfo[0].split(" ")[1];
        double wasteWeight = Double.parseDouble(commandInfo[1]);
        double wasteVolumePerKg = Double.parseDouble(commandInfo[2]);
        String typeName = commandInfo[3];

        Waste waste = null;
        GarbageDisposalStrategy type = null;
        try {
            type = wasteStrategyFactory.createGarbageTypeStrategi(typeName);
            waste = new WasteImpl(wasteName, wasteVolumePerKg, wasteWeight);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Garbage garbage = garbageFactory.createGarbage(waste, type);

        garbageRepository.addGarbage(garbage);
        this.updateCurrentFieldValue(garbage);

        return String.format("%2.f kg of %s successfully processed!", wasteWeight, wasteName);
    }

    private void updateCurrentFieldValue(Garbage garbage) {

        this.energy += garbage.getGarbageEnergy();
        this.capital += garbage.getGarbageCapital();

    }

    @Override
    public double getEnergyBalance() {
        return this.energy;
    }

    @Override
    public double getCapitalBalance() {
        return this.capital;
    }

}
