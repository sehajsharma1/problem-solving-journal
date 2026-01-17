package com.ocean.problemsolvingjournal.systemdesign.parkinglot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.component.VehicleFactory;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.ParkingSpot;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Ticket;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Vehicle;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.dto.TicketDTO;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.util.TicketIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private VehicleFactory factory;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public TicketService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Ticket generateTicket(Vehicle vehicle, ParkingSpot spot) {
        Ticket ticket = new Ticket(TicketIdGenerator.generateTicketId(), vehicle, spot, LocalDateTime.now(), null);
        //  Save ticket in Redis
        saveTicket(ticket);
        return ticket;
    }

    public Ticket getTicket(String ticketId) {
        Object obj = redisTemplate.opsForValue().get("ticket:" + ticketId);
        TicketDTO dto = objectMapper.convertValue(obj, TicketDTO.class);
        return toTicket(dto);
    }

    public void saveTicket(Ticket ticket) {
        String key = "ticket:" + ticket.getTicketId();
        redisTemplate.opsForValue().set(key, ticket);
    }

    public Ticket toTicket(TicketDTO dto) {
        Vehicle vehicle = factory.createVehicle(dto.getVehicle());
        ParkingSpot parkingSpot = factory.createParkingSpot(dto.getParkingSpot());
        return new Ticket(
                dto.getTicketId(),
                vehicle,
                parkingSpot,
                dto.getEntryTime(),
                dto.getExitTime()
        );
    }
}
