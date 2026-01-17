package com.ocean.problemsolvingjournal.systemdesign.parkinglot.util;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


public class TicketIdGenerator {
    // thread-safe counter to avoid collisions when called rapidly
    private static final AtomicLong counter = new AtomicLong(0);

    /**
     * Generate a compact, unique ticket id.
     * Format: T-{timestampMillis}-{hexCounter}-{uuid8}
     */
    public static String generateTicketId() {
        return generateTicketId(null);
    }

    /**
     * Generate a unique ticket id with optional prefix.
     * If prefix is null or empty, 'T' is used.
     */
    public static String generateTicketId(String prefix) {
        String p = (prefix == null || prefix.isEmpty()) ? "T" : prefix;
        long ts = Instant.now().toEpochMilli();
        long seq = counter.incrementAndGet();
        String uuidPart = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return String.format("%s-%d-%x-%s", p, ts, seq, uuidPart);
    }
}
