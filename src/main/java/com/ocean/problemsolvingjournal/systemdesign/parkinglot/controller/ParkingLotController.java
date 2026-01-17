package com.ocean.problemsolvingjournal.systemdesign.parkinglot.controller;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.component.VehicleFactory;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.ParkingSpot;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Ticket;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Vehicle;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.VehicleRequest;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.service.FareCalculator;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.service.ParkingManager;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/parkinglot")
public class ParkingLotController {
    //  Manages parking spots and vehicle assignments
    private final ParkingManager parkingManager;
    // Calculates fare for parking sessions
    private final FareCalculator fareCalculator;
    private final TicketService ticketService;
    private final VehicleFactory vehicleFactory;


    public ParkingLotController(VehicleFactory vehicleFactory, ParkingManager parkingManager, FareCalculator fareCalculator, TicketService ticketService) {
        this.parkingManager = parkingManager;
        this.fareCalculator = fareCalculator;
        this.ticketService = ticketService;
        this.vehicleFactory = vehicleFactory;

    }

    @PostMapping("/vehicle/entry")
    public Ticket enterVehicle(@RequestBody VehicleRequest request) {
        // Delegate parking logic to ParkingManager
        Vehicle vehicle = vehicleFactory.createVehicle(request);
        ParkingSpot spot = parkingManager.parkVehicle(vehicle);
        if (spot != null) {
            // Create ticket with entry time
            return ticketService.generateTicket(vehicle, spot);
        } else {
            return null; // No spot available
        }
    }

    // Method to handle vehicle exit from the parking lot
    @GetMapping("/vehicle/leave")
    public ResponseEntity<BigDecimal> leaveVehicle(@RequestParam String ticketId) {
        // Ensure the ticket is valid and the vehicle hasn't already left
        Ticket ticket = ticketService.getTicket(ticketId);

        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }
        if (ticket.getExitTime() != null) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        // Set exit time
        ticket.setExitTime(LocalDateTime.now());
        ticketService.saveTicket(ticket);

        // Delegate unparking logic to ParkingManager
        parkingManager.unparkVehicle(ticket.getVehicle());

        // Calculate the fare
        BigDecimal fare = fareCalculator.calculateFare(ticket);
        return ResponseEntity.ok(fare);
    }
}
