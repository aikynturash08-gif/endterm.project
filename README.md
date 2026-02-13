# Endterm Project API (Spring Boot)

This project implements the required **RESTful API** architecture:
**Controller → Service → Repository → Database**, with JDBC access.

## Tech
- Java 25
- Spring Boot 4.0.2
- PostgreSQL
- Maven

## Design Patterns (Required)
- **Singleton**: `AppLogger` (explicit singleton instance used in repositories)
- **Factory**: `VehicleFactory` creates subclasses of the abstract base entity `Vehicle` (`Car` / `Suv`)
- **Builder**: `RentalBuilder` builds `Rental` objects (fluent API)

## Component Principles (REP / CCP / CRP)
- `repository`, `service`, `utils`, `patterns` packages are reusable modules (**REP**).
- Features that change together are grouped: `vehicle/*`, rentals, customers (**CCP**).
- Avoid forcing dependencies: DTOs used only by controllers/services (**CRP**).

## Database
Create database:
```sql
CREATE DATABASE endterm_api;
```

Tables are auto-created on app start via `schema.sql`.

## Run
1. Edit `src/main/resources/application.properties` with your DB credentials
2. Run:
```bash
mvn spring-boot:run
```

## REST Endpoints
### Vehicles
- `GET /api/vehicles`
- `GET /api/vehicles/{id}`
- `POST /api/vehicles`
- `PUT /api/vehicles/{id}`
- `DELETE /api/vehicles/{id}`

Sample `POST /api/vehicles` body:
```json
{ "type":"CAR", "brand":"Kia", "model":"K5", "pricePerDay": 28000 }
```

### Customers
- `GET /api/customers`
- `GET /api/customers/{id}`
- `POST /api/customers`
- `PUT /api/customers/{id}`
- `DELETE /api/customers/{id}`

### Rentals
- `GET /api/rentals`
- `GET /api/rentals/{id}`
- `POST /api/rentals` (rent a vehicle)
- `POST /api/rentals/{id}/complete`
- `POST /api/rentals/{id}/cancel`
- `DELETE /api/rentals/{id}`

Sample `POST /api/rentals` body:
```json
{ "vehicleId": 1, "customerId": 1, "days": 3 }
```

## Global error handling
`GlobalExceptionHandler` returns JSON errors with status codes.

<img width="960" height="1020" alt="Screenshot 2026-02-08 201246" src="https://github.com/user-attachments/assets/b3bcdc17-5f0b-48ae-bd54-89cf0f7f51a8" />
<img width="960" height="1020" alt="Screenshot 2026-02-08 201324" src="https://github.com/user-attachments/assets/3e49c73b-ce09-494d-af63-8e4d4e50359b" />
<img width="960" height="1020" alt="Screenshot 2026-02-08 201402" src="https://github.com/user-attachments/assets/8ab6bfd9-d983-45c7-b8df-5bbce6ac9a3d" />
<img width="960" height="1020" alt="Screenshot 2026-02-08 201436" src="https://github.com/user-attachments/assets/ee7b644b-bfe5-49ba-8eec-e4a326a0443d" />
