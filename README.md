# 🍽️ Food Ordering API

A production-style **Food Ordering REST API** built with **Spring Boot, JPA, MySQL and Spring Security**.

The project focuses on clean backend architecture, relational database design, JWT authentication, authorization, ownership checks and real-world order/payment workflows.

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA / Hibernate
* MySQL
* Spring Security
* JWT
* BCrypt
* MapStruct
* Bean Validation
* Lombok
* Maven
* Postman

---

## 🏗️ Architecture

```text
Client
  ↓
Controller
  ↓
DTO
  ↓
Service
  ↓
Mapper
  ↓
Repository
  ↓
MySQL
```

### Project Structure

```text
controller/
services/
repository/
models/
dto/
mapper/
exception/
webSecurity/
```

Each layer has a separate responsibility:

* **Controller** → HTTP/API handling
* **Service** → Business logic
* **DTO** → API contract
* **Mapper** → DTO ↔ Entity
* **Repository** → Database access
* **Security** → JWT authentication & authorization
* **Exception** → Centralized error handling

---

# 🗄️ Database Design

Main entities:

```text
User
Restaurant
Address
Item
Order
OrderItem
Payment
```

### Relationships

```text
User 1 ──── M Address
User 1 ──── M Order

Restaurant 1 ──── M Item
Restaurant 1 ──── M Order

Order 1 ──── M OrderItem
Item  1 ──── M OrderItem

Order 1 ──── 1 Payment

Order M ──── 1 Address
Order M ──── 1 Restaurant
```

### Order ↔ Item

Instead of directly using `@ManyToMany`, the project uses an intermediate `OrderItem` entity because the relationship contains:

```text
quantity
price
```

```text
Order
  ↓ 1:M
OrderItem
  ↓ M:1
Item
```

---

# 🔐 Authentication & Authorization

Authentication uses:

```text
Spring Security
+
JWT
+
BCrypt
```

Flow:

```text
Login
 ↓
Verify BCrypt Password
 ↓
Generate JWT
 ↓
Client
 ↓
Bearer Token
 ↓
JWT Filter
 ↓
SecurityContext
```

JWT contains:

```text
email
userId
role
expiration
```

Authorization:

```text
USER
 ↓
hasAuthority("USER")
```

---

# 🔒 Resource Ownership

The backend doesn't rely only on roles.

It also verifies whether a resource belongs to the authenticated user.

Example:

```text
User 2 JWT
    ↓
GET /api/users/1
    ↓
403 Forbidden
```

While:

```text
User 2 JWT
    ↓
GET /api/users/2
    ↓
200 OK
```

Order creation also derives the user from JWT instead of accepting `userId` from the client.

---

# 🍕 Order Flow

```text
Create Order
     ↓
Get Current User from JWT
     ↓
Validate Restaurant
     ↓
Validate Address Ownership
     ↓
Validate Items
     ↓
Verify Items belong to Restaurant
     ↓
Calculate Total
     ↓
Create OrderItems
     ↓
Save Order
```

Price calculation:

```text
Item Price × Quantity = Subtotal
```

`BigDecimal` is used for monetary calculations.

---

# 💳 Payment Flow

```text
Order
 ↓
Create Payment
 ↓
Generate Transaction ID
 ↓
Set Amount from Order Total
 ↓
PENDING
 ↓
SUCCESS
```

---

# 📡 Main APIs

### Authentication

```http
POST /api/auth/register
POST /api/auth/login
```

### Users

```http
GET    /api/users/{id}
PUT    /api/users/{id}
DELETE /api/users/{id}
```

### Restaurants

```http
POST   /api/restaurants
GET    /api/restaurants/{id}
PUT    /api/restaurants/{id}
DELETE /api/restaurants/{id}
```

### Items

```http
POST   /api/items/restaurant/{restaurantId}
GET    /api/items/{id}
PUT    /api/items/{id}
DELETE /api/items/{id}
```

### Orders

```http
POST  /api/orders
GET   /api/orders/{id}
GET   /api/orders/user/{userId}
PATCH /api/orders/{orderId}/status
PATCH /api/orders/{orderId}/cancel
```

### Payments

```http
POST  /api/payments/orders/{orderId}/payment
GET   /api/payments/{id}
GET   /api/payments/orders/{orderId}/payment
PATCH /api/payments/{paymentId}/status
```

---

# 🛡️ Validation & Exception Handling

Implemented:

* Bean Validation
* Custom business exceptions
* Resource-not-found handling
* Global exception handling
* `400 Bad Request`
* `403 Forbidden`
* `404 Not Found`
* `409 Conflict`
* `500 Internal Server Error`

---

# 🧪 API Testing

The complete backend flow has been tested using **Postman**:

```text
Register
 ↓
Login
 ↓
JWT Authentication
 ↓
Restaurant
 ↓
Address
 ↓
Item
 ↓
Order
 ↓
Payment
 ↓
Order Status
```

Ownership and invalid business scenarios were also tested.

---

# 🎯 Key Concepts Demonstrated

* REST API Design
* Spring Boot
* JPA/Hibernate Relationships
* DTO Architecture
* MapStruct
* JWT Authentication
* Role-Based Authorization
* Resource Ownership
* BCrypt Password Hashing
* Bean Validation
* Global Exception Handling
* Business Logic
* MySQL Relational Design
* Postman API Testing

---

## 🚧 Future Improvements

* Restaurant authentication & authorization
* Restaurant/menu listing APIs
* Payment gateway integration
* Swagger/OpenAPI
* Automated tests
* Docker deployment
* Production environment configuration

---

## 👨‍💻 Project

**Food Ordering API**

Built as a real-world backend project to practice and demonstrate modern Java/Spring Boot backend development.
