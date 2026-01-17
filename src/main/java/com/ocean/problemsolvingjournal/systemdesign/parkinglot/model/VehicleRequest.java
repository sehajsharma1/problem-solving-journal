package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;

public class VehicleRequest {

    private String licensePlate;

    private VehicleSize size;

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleSize getSize() {
        return size;
    }

    public void setSize(VehicleSize size) {
        this.size = size;
    }
}
