package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private String licensePlate;
    private String size;   // SMALL, MEDIUM, LARGE

    public VehicleDTO() {
    }
}
