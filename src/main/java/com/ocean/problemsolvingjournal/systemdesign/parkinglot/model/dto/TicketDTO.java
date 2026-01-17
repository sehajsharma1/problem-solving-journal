package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TicketDTO {
    private String ticketId;
    private VehicleDTO vehicle;
    private ParkingSpotDTO parkingSpot;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public TicketDTO() {
    }
}
