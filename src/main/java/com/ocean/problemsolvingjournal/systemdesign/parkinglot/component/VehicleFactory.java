package com.ocean.problemsolvingjournal.systemdesign.parkinglot.component;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.*;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto.ParkingSpotDTO;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto.VehicleDTO;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;
import org.springframework.stereotype.Component;

@Component
public class VehicleFactory {

    public Vehicle createVehicle(VehicleRequest request) {
        return switch (request.getSize()) {
            case SMALL -> new Bike(request.getLicensePlate());
            default -> throw new IllegalArgumentException("Invalid vehicle size");
        };
    }

    public Vehicle createVehicle(VehicleDTO dto) {
        VehicleRequest request = new VehicleRequest();
        request.setLicensePlate(dto.getLicensePlate());
        request.setSize(VehicleSize.valueOf(dto.getSize()));
        return createVehicle(request);
    }

    public ParkingSpot createParkingSpot(ParkingSpotDTO dto) {
        return switch (VehicleSize.valueOf(dto.getVehicle().getSize())) {
            case SMALL -> {
                Floor floor = new Floor(dto.getFloor().getFloorNumber());
                Vehicle vehicle = createVehicle(dto.getVehicle());
                ParkingSpot spot = new SmallSpot(dto.getSpotNumber(), floor);
                spot.occupy(vehicle);
                yield spot;
            }
            default -> throw new IllegalArgumentException("Invalid vehicle size");
        };
    }

    public ParkingSpot createParkingSpot(String spotNumber, int floorNumber, Vehicle vehicle) {
        return switch (vehicle.getSize()) {
            case SMALL -> {
                Floor floor = new Floor(floorNumber);
                yield new SmallSpot(spotNumber, floor);
            }
            default -> throw new IllegalArgumentException("Invalid vehicle size");
        };
    }
}