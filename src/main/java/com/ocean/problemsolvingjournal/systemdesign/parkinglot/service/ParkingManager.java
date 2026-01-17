package com.ocean.problemsolvingjournal.systemdesign.parkinglot.service;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.component.VehicleFactory;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.ParkingSpot;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Vehicle;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.util.ParkingLotUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ParkingManager {
    private final RedisTemplate<String, Object> redisTemplate;
    private final Map<String, ParkingSpot> vehicleToSpotMap;

    @Autowired
    private VehicleFactory factory;


    // Create Parking Manager
    public ParkingManager(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.vehicleToSpotMap = new HashMap<>();
    }

    public ParkingSpot findSpotForVehicle(Vehicle vehicle) {
        VehicleSize vehicleSize = vehicle.getSize();
        ParkingSpot parkingSpot = null;
        for (int i = 0; i < 2; i++) {
            String redisKey = ParkingLotUtil.buildKey(i, vehicleSize);
            Object spot = redisTemplate.opsForSet().pop(redisKey);
            if (Objects.nonNull(spot)) {
                parkingSpot = factory.createParkingSpot(String.valueOf(spot), i, vehicle);
                return parkingSpot;
            }
        }
        return parkingSpot;
    }

    public ParkingSpot parkVehicle(Vehicle vehicle) {
        ParkingSpot spot = findSpotForVehicle(vehicle);
        if (spot != null) {
            spot.occupy(vehicle);
            // Record the parking spot for the vehicle
            vehicleToSpotMap.put(vehicle.getLicensePlate(), spot);
            return spot; // Parking successful
        }
        return null; // No spot found for this vehicle
    }

    public void unparkVehicle(Vehicle vehicle) {
        ParkingSpot spot = vehicleToSpotMap.remove(vehicle.getLicensePlate());
        if (spot != null) {
            spot.vacate();
            String spotNumber = spot.getSpotNumber();
            int floor = spot.getFloor().getFloorNumber();
            String redisKey = ParkingLotUtil.buildKey(floor, vehicle.getSize());
            redisTemplate.opsForSet().add(redisKey, spotNumber);
        }
    }

}
