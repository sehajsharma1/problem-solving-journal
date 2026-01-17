package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;

public class Bike implements Vehicle {
    private String licensePlate;

    public Bike() {
    }
    public Bike(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public VehicleSize getSize() {
        return VehicleSize.SMALL;
    }
}
