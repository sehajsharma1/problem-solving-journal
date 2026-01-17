package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;


public class Ticket {
    // Unique ticket identifier
    private String ticketId;
    // The vehicle associated with the ticket
    private Vehicle vehicle;
    // The parking spot where the vehicle is parked
    private ParkingSpot parkingSpot;
    // // The time the vehicle entered the parking lot
    private LocalDateTime entryTime;
    // The time the vehicle exited the parking lot
    private LocalDateTime exitTime;


    public Ticket(
            String ticketId, Vehicle vehicle, ParkingSpot parkingSpot, LocalDateTime entryTime, LocalDateTime exitTime) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.parkingSpot = parkingSpot;
        this.entryTime = entryTime;
        // Initially, exitTime is null because the vehicle is still parked
        this.exitTime = exitTime;
    }

    public Ticket() {
    }

    public BigDecimal calculateParkingDuration() {
        return new BigDecimal(
                Duration.between(
                                entryTime,
                                Objects.requireNonNullElseGet(exitTime, LocalDateTime::now))
                        .toMinutes());
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}

