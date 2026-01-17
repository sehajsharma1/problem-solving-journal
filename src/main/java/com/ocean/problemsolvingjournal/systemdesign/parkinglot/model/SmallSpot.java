package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

public class SmallSpot implements ParkingSpot {
    private String spotNumber;
    private Floor floor;
    // The vehicle currently occupying this spot
    private Vehicle vehicle;

    public SmallSpot() {
    }

    public SmallSpot(String spotNumber, Floor floor) {
        this.spotNumber = spotNumber;
        this.vehicle = null;// No vehicle occupying initially
        this.floor = floor;
    }

    @Override
    public String getSpotNumber() {
        return spotNumber;
    }

    @Override
    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public Floor getFloor() {
        return floor;
    }

    @Override
    public void occupy(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void vacate() {
        this.vehicle = null; // Make the spot available
    }
}
