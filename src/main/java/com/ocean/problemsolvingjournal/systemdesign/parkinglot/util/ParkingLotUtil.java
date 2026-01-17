package com.ocean.problemsolvingjournal.systemdesign.parkinglot.util;


import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.enumeration.VehicleSize;

public class ParkingLotUtil {
    
    public static String buildKey(int floorNumber, VehicleSize size) {
        return "parking:floor:" + floorNumber + ":" + size.name();
    }
}
