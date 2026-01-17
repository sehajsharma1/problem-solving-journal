# Parking Lot (systemdesign.parkinglot)

This package contains a simple parking-lot system (example implementation) used in the project to demonstrate a system-design style service backed by Redis.

Overview
- Purpose: manage parking spots per floor/vehicle-size, issue tickets on vehicle entry, calculate fares on exit.
- Persistence: Redis is used to store available spot sets and serialized Ticket objects.

Key components
- Controller
  - `ParkingLotController` — REST API for vehicle entry and exit.

- Services
  - `ParkingManager` — assigns and releases parking spots (uses Redis sets & an in-memory map).
  - `TicketService` — creates, saves and retrieves `Ticket` objects from Redis.
  - `FareCalculator` — composes `FareStrategy` implementations to compute final fare.

- Fare strategies
  - `BaseFareStrategy` — applies a per-minute rate by vehicle size.
  - `PeakHoursFareStrategy` — applies a peak-hour multiplier (currently 1.5x based on entry time).

- Models
  - `Ticket`, `ParkingSpot`/`SmallSpot`, `Vehicle`/`Bike`, `Floor`, `FareStrategy`, `VehicleSize` enum.
  - DTOs: `TicketDTO`, `VehicleDTO`, `ParkingSpotDTO`, `FloorDTO` (used for Redis serialization/deserialization).

- Utilities & config
  - `TicketIdGenerator` — compact unique ticket ids.
  - `ParkingLotUtil` — builds Redis keys for floor + size.
  - `ParkingLotConfig` — configures Jackson `ObjectMapper` for Java time.
  - `RedisStartupConfig` — populates parking spot sets on application startup and provides a `RedisTemplate<String,Object>` bean.
  - `VehicleFactory` — builds domain objects from DTOs or primitives.

How it works (high-level)
- Startup: `RedisStartupConfig` clears existing ticket keys and preloads Redis sets for floors (example loads floors 0 and 1 and spots for SMALL and MEDIUM sizes).
- Vehicle entry (`POST /api/v1/parkinglot/vehicle/entry` with `VehicleRequest`):
  1. `ParkingLotController` creates a `Vehicle` via `VehicleFactory`.
  2. `ParkingManager` pops a spot from the Redis set for the given size (atomic pop) and marks it occupied.
  3. `TicketService` generates a `Ticket` (with unique id and entry time) and saves it to Redis under `ticket:{ticketId}`.
  4. Ticket is returned to caller.
- Vehicle exit (`POST/GET /api/v1/parkinglot/vehicle/leave?ticketId=`):
  1. Controller loads the ticket from Redis and sets `exitTime`.
  2. `ParkingManager` vacates the spot and pushes the spot id back to the Redis set.
  3. `FareCalculator` runs configured `FareStrategy` beans to compute the final fare and returns it.

Redis layout
- Available spots: `parking:floor:{floorNumber}:{VehicleSize}` — Redis Set containing spot identifiers (e.g. `A1`, `B12`).
- Tickets: `ticket:{ticketId}` — stored value is the serialized `Ticket` object (JSON via configured serializer).

REST endpoints (examples)
- Entry:
  - POST /api/v1/parkinglot/vehicle/entry
  - Body: JSON { "licensePlate": "KA01AB1234", "size": "SMALL" }
- Exit (current implementation uses GET but this is state-changing; consider POST in future):
  - GET /api/v1/parkinglot/vehicle/leave?ticketId={ticketId}

Example curl (replace host/port as appropriate):

```bash
# vehicle entry
curl -X POST -H "Content-Type: application/json" \
  -d '{"licensePlate":"KA01AB1234","size":"SMALL"}' \
  http://localhost:8080/api/v1/parkinglot/vehicle/entry

# vehicle exit
curl "http://localhost:8080/api/v1/parkinglot/vehicle/leave?ticketId=T-..."
```

Known assumptions & limitations
- Only `SMALL` vehicles and `SmallSpot` are fully implemented; `MEDIUM` and `LARGE` types exist in the enum but do not have concrete Vehicle or Spot implementations in the current code.
- `ParkingManager` currently loops a hard-coded set of floors (0 and 1) — not configurable via properties.
- The in-memory `vehicleToSpotMap` is a plain `HashMap` and is not cluster-safe or thread-safe; concurrent requests can cause issues. Consider `ConcurrentHashMap` or storing mapping in Redis.
- `RedisStartupConfig` uses `redisTemplate.keys("ticket*")` which can be slow with many keys; consider SCAN for production.
- `FareCalculator` applies strategies in the autowired list order. Because `PeakHoursFareStrategy` multiplies the incoming fare, order matters. To fix deterministically either:
  - ensure `BaseFareStrategy` runs before `PeakHoursFareStrategy` (use `@Order`), or
  - make peak strategy apply its multiplier after the base fare is computed.

Suggested improvements
- Implement Medium/Large vehicle & spot classes if those sizes are needed.
- Persist vehicle->spot mappings in Redis (or derive from ticket) instead of keeping them in-memory.
- Return proper HTTP status codes for entry (e.g., 201 or 409) and change the leave endpoint to use POST for state changes.
- Add unit tests for fare calculation (cover peak/non-peak and different sizes) and concurrency tests for parking.

Contact / notes
- This README is a quick reference — see individual classes in the package for implementation details and TODOs.
