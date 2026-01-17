package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

public interface ParkingSpot {

    void occupy(Vehicle vehicle);

    void vacate();

    String getSpotNumber();

    Vehicle getVehicle();

    Floor getFloor();
}
