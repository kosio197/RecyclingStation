package bg.softuni.model;

import bg.softuni.contract.GarbageDisposalStrategy;
import bg.softuni.contract.Waste;

public class Garbage {

    private Waste waste;
    private GarbageDisposalStrategy type;
    private double garbageEnergy;
    private Double garbageCapital;

    public Garbage(Waste waste, GarbageDisposalStrategy type) {
        setWaste(waste);
        setType(type);
    }

    public Waste getWaste() {
        return waste;
    }

    public void setWaste(Waste waste) {
        this.waste = waste;
    }

    public GarbageDisposalStrategy getType() {
        return type;
    }

    public void setType(GarbageDisposalStrategy type) {
        this.type = type;
    }

    public double getGarbageEnergy() {
        return garbageEnergy;
    }

    public void setGarbageEnergy(double garbageEnergy) {

        // TODO calculate typeAfection and set value
        this.garbageEnergy = garbageEnergy;
    }

    public Double getGarbageCapital() {
        return garbageCapital;
    }

    public void setGarbageCapital(Double garbageCapital) {
        // TODO calculate typeAfection and set value
        this.garbageCapital = garbageCapital;
    }

}
