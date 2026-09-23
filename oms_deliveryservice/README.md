# OMS Delivery Service — Phase 3

Microservice responsible for managing deliveries in the Order Management System.

- **Port:** `8083`
- **Database:** `delivery_db` (MySQL, auto-created on startup)
- **Auth:** JWT (shared secret with Phase 1 & 2)

---

## Endpoints

| Method | Path | Role | Description |
|--------|------|------|-------------|
| POST | `/api/deliveries` | ADMIN | Create a delivery for an order |
| GET | `/api/deliveries/{id}` | Any authenticated | Get delivery by ID |
| GET | `/api/deliveries/order/{orderId}` | Any authenticated | Get delivery by order ID |
| GET | `/api/deliveries/customer/{customerId}` | Any authenticated | Get all deliveries for a customer |
| PATCH | `/api/deliveries/{id}/status` | ADMIN | Update delivery status |

---

## Delivery Status Lifecycle

```
PENDING → OUT_FOR_DELIVERY → DELIVERED
                           → FAILED
```

---

## Setup

1. Ensure MySQL is running with a `root/root` user (or update `application.yaml`)
2. The `delivery_db` database is created automatically
3. Run the service:

```bash
mvn spring-boot:run
```

---

## Sample Postman Requests

### Create Delivery (ADMIN token required)
```json
POST http://localhost:8083/api/deliveries
Authorization: Bearer <admin_token>

{
  "orderId": 1,
  "customerId": 1,
  "deliveryAddress": "123 Main Street, Johannesburg",
  "driverName": "John Driver"
}
```

### Update Status (ADMIN token required)
```json
PATCH http://localhost:8083/api/deliveries/1/status
Authorization: Bearer <admin_token>

{
  "status": "OUT_FOR_DELIVERY"
}
```

### Get Delivery by Order
```
GET http://localhost:8083/api/deliveries/order/1
Authorization: Bearer <any_token>
```

---

## Related Services

- **Phase 1 — User Management:** `http://localhost:8081`
- **Phase 2 — Order Management:** `http://localhost:8082`
