package bg.softuni.model;

import bg.softuni.contract.Waste;

public class WasteImpl implements Waste {

    private String name;
    private double volumePerKg;
    private double weight;

    public WasteImpl(String name, double volumePerKg, double weight) {
        this.name = name;
        this.volumePerKg = volumePerKg;
        this.weight = weight;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getVolumePerKg() {
        return this.volumePerKg;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

}
