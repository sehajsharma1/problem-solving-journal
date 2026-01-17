package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingSpotDTO {
    private String spotNumber;
    private FloorDTO floor;
    private VehicleDTO vehicle;

    public ParkingSpotDTO() {
    }
}
