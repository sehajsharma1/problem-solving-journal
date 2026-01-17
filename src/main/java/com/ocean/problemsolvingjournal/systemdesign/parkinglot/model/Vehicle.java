package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;

public interface Vehicle {

    String getLicensePlate();

    VehicleSize getSize();
}
