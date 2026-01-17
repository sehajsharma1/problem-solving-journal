package com.ocean.problemsolvingjournal.systemdesign.parkinglot.model;

import java.math.BigDecimal;

public interface FareStrategy {
    BigDecimal calculateFare(Ticket ticket, BigDecimal inputFare);
}
