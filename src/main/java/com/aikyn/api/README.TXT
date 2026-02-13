## Bonus Task – In-Memory Caching Layer

A simple in-memory caching mechanism was implemented for frequently accessed data.

The list of vehicles is cached in memory to avoid repeated database queries.

### Implementation details

- The cache is implemented using a simple in-memory structure.
- A Singleton pattern is used to ensure that only one cache instance exists.
- The cache is accessed only from the service layer.
- After create, update, or delete operations, the cache is automatically invalidated.

### Cached method

- VehicleService.getAll()

### Cache class

- VehicleCache (Singleton)

This implementation follows layered architecture and does not break separation of concerns.
