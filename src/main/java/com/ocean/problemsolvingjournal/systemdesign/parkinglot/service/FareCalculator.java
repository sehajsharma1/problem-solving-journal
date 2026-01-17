package com.ocean.problemsolvingjournal.systemdesign.parkinglot.service;

import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.FareStrategy;
import com.ocean.problemsolvingjournal.systemdesign.parkinglot.model.Ticket;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FareCalculator {
    private final List<FareStrategy> fareStrategies;

    public FareCalculator(List<FareStrategy> fareStrategies) {
        this.fareStrategies = fareStrategies;
    }

    public BigDecimal calculateFare(Ticket ticket) {
        BigDecimal fare = BigDecimal.ZERO;
        for (FareStrategy strategy : fareStrategies) {
            fare = strategy.calculateFare(ticket, fare);
        }
        return fare;
    }
}
