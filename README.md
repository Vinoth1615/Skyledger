<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:1e3c72,100:2a5298&height=220&section=header&text=SkyLedger&fontSize=70&fontColor=ffffff&animation=fadeIn&fontAlignY=38&desc=Microservices-Based%20Airline%20Reservation%20System&descAlignY=58&descSize=20" width="100%"/>

<img src="https://readme-typing-svg.demolab.com/?font=Fira+Code&weight=600&size=24&duration=3000&pause=800&color=2A5298&center=true&vCenter=true&width=780&lines=A+distributed+airline+reservation+platform;Simulating+a+mini+Global+Distribution+System+(GDS);Java+%7C+Spring+Boot+%7C+Spring+Cloud+%7C+Kafka;Redis+%7C+MySQL+%7C+Docker" alt="Typing SVG" />

<br/>

[![Java](https://img.shields.io/badge/Java-17-orange.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-202x-6DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)
[![Kafka](https://img.shields.io/badge/Apache%20Kafka-black.svg?style=for-the-badge&logo=apachekafka&logoColor=white)](https://kafka.apache.org/)
[![Redis](https://img.shields.io/badge/Redis-red.svg?style=for-the-badge&logo=redis&logoColor=white)](https://redis.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED.svg?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)](LICENSE)

<br/>

![Stars](https://img.shields.io/github/stars/Vinoth1615/SkyLedger?style=social)
![Forks](https://img.shields.io/github/forks/Vinoth1615/SkyLedger?style=social)
![Last Commit](https://img.shields.io/github/last-commit/Vinoth1615/SkyLedger?color=blue)
![Repo Size](https://img.shields.io/github/repo-size/Vinoth1615/SkyLedger?color=informational)

</div>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📌 Overview

**SkyLedger** is a microservices-based airline reservation system designed to simulate the architecture and workflows found in modern airline reservation and **Global Distribution System (GDS)** platforms.

Instead of implementing the entire application as a single monolithic service, SkyLedger separates major airline business domains into independently deployable microservices.

The platform supports the complete airline booking lifecycle:

<div align="center">

**Flight Search → Fare Selection → Seat Availability → Booking → Payment → Confirmation → Notification**

</div>

The system uses **synchronous REST communication** for request-response operations and **Apache Kafka-based event-driven communication** for asynchronous workflows.

Distributed booking consistency is handled using the **Saga Pattern**, where services publish events and execute compensating actions when downstream operations fail.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🗂️ Table of Contents

<details>
<summary>Click to expand</summary>

- [Project Goals](#-project-goals)
- [System Architecture](#️-system-architecture)
- [Microservices](#-microservices)
- [Core Features](#️-core-features)
- [Saga-Based Distributed Transaction](#-saga-based-distributed-transaction)
- [Event-Driven Architecture](#-event-driven-architecture)
- [Security](#-security)
- [Redis Caching](#-redis-caching)
- [API Gateway](#-api-gateway)
- [Service Discovery](#-service-discovery)
- [Inter-Service Communication](#-inter-service-communication)
- [Resilience & Fault Tolerance](#️-resilience--fault-tolerance)
- [Payment Integration](#-payment-integration)
- [Notification System](#-notification-system)
- [Database Architecture](#️-database-architecture)
- [Technology Stack](#️-technology-stack)
- [Project Structure](#-project-structure)
- [End-to-End Booking Flow](#-end-to-end-booking-flow)
- [Testing](#-testing)
- [Docker Deployment](#-docker-deployment)
- [Local Development](#️-local-development)
- [API Endpoints](#-example-api-endpoints)
- [Architecture Patterns Used](#-architecture-patterns-used)
- [Key Engineering Challenges](#-key-engineering-challenges)
- [Future Enhancements](#-future-enhancements)
- [Learning Outcomes](#-learning-outcomes)
- [Screenshots](#-screenshots)
- [Author](#-author)

</details>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🎯 Project Goals

SkyLedger was designed to demonstrate practical understanding of:

- Microservices architecture
- Domain-driven service decomposition
- Distributed transactions
- Event-driven architecture
- Saga-based workflows
- API Gateway architecture
- Service discovery
- JWT security
- Role-based authorization
- Redis caching
- Inter-service communication
- Fault-tolerant distributed systems
- Containerized deployment

> The project focuses on **real-world backend architecture rather than simple CRUD implementation.**

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🏗️ System Architecture

```mermaid
flowchart TD
    Client["🖥️ Client / UI"] --> Gateway["🌐 API Gateway<br/>Routing / Security"]
    Gateway --> Flight["✈️ Flight Service"]
    Gateway --> Booking["🎫 Booking Service"]
    Gateway --> Auth["🔐 Auth Service"]

    Flight --> Fare["💰 Fare Service"]
    Booking --> Seat["💺 Seat Service"]
    Seat --> Payment["💳 Payment Service"]
    Payment --> Notification["📧 Notification Service"]

    Kafka[("📨 Apache Kafka<br/>Event-Driven Messaging")]
    Redis[("⚡ Redis<br/>Cache / Token Revocation")]
    MySQL[("🗄️ MySQL<br/>Database per Service")]

    Booking -.-> Kafka
    Seat -.-> Kafka
    Payment -.-> Kafka
    Notification -.-> Kafka

    Flight -.-> Redis
    Auth -.-> Redis

    Flight --- MySQL
    Booking --- MySQL
    Payment --- MySQL

    style Client fill:#1e3c72,color:#fff
    style Gateway fill:#2a5298,color:#fff
    style Kafka fill:#000,color:#fff
    style Redis fill:#d82c20,color:#fff
    style MySQL fill:#4479A1,color:#fff
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🧩 Microservices

SkyLedger is divided into independent business domains.

| Service | Responsibility |
|---|---|
| **API Gateway** | Central entry point, routing and cross-cutting concerns |
| **Eureka Server** | Service registration and discovery |
| **Auth Service** | Authentication, JWT generation and authorization |
| **Flight Service** | Flight schedules, routes and flight information |
| **Fare Service** | Fare calculation and pricing |
| **Seat Service** | Seat inventory and seat locking |
| **Booking Service** | Reservation lifecycle and booking management |
| **Payment Service** | Payment processing |
| **Notification Service** | Booking confirmation and notifications |

> The exact service list should be updated to match the services actually present in the repository.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## ✈️ Core Features

### 🔎 Flight Search

Users can search available flights using parameters such as:

- Source
- Destination
- Departure date
- Passenger count
- Flight availability

The Flight Service manages flight and schedule information independently.

### 💺 Seat Availability Management

The Seat Service manages:

- Seat inventory
- Seat status
- Seat selection
- Temporary seat locking
- Seat release after booking failure

Seat locking is particularly important for preventing multiple users from successfully booking the same seat.

### 💰 Fare Pricing

The Fare Service handles:

- Base fare
- Fare categories
- Passenger pricing
- Dynamic pricing logic
- Fare retrieval

Fare information is separated from flight and booking data to maintain independent domain ownership.

### 🎫 Booking & Reservation

The Booking Service manages the reservation lifecycle.

```mermaid
stateDiagram-v2
    [*] --> INITIATED
    INITIATED --> SEAT_LOCKED
    SEAT_LOCKED --> PAYMENT_PENDING
    PAYMENT_PENDING --> PAYMENT_SUCCESS
    PAYMENT_SUCCESS --> CONFIRMED
    CONFIRMED --> [*]

    PAYMENT_PENDING --> PAYMENT_FAILED
    PAYMENT_FAILED --> CANCEL_BOOKING
    CANCEL_BOOKING --> RELEASE_SEAT
    RELEASE_SEAT --> [*]
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🔄 Saga-Based Distributed Transaction

Airline booking involves multiple services and therefore cannot rely on a traditional single-database transaction.

SkyLedger uses the **Saga Pattern** to coordinate the distributed booking workflow.

### Booking Saga

```mermaid
flowchart TD
    A["Booking Created"] --> B["Seat Locked"]
    B --> C["Payment Initiated"]
    C -->|SUCCESS| D["Booking Confirmed"]
    C -->|FAILURE| E["Release Seat"]
    D --> F["Notification"]
    E --> G["Cancel Booking"]

    style D fill:#2e7d32,color:#fff
    style F fill:#2e7d32,color:#fff
    style E fill:#c62828,color:#fff
    style G fill:#c62828,color:#fff
```

Each service performs its local transaction and publishes an event. If a downstream operation fails, previously completed operations can be compensated.

### Example — Payment Failure Compensation

```mermaid
sequenceDiagram
    participant P as Payment Service
    participant B as Booking Service
    participant S as Seat Service

    P->>B: PaymentFailedEvent
    B->>B: Cancel Booking
    B->>S: ReleaseSeatEvent
    S->>S: Seat Available
```

This avoids leaving the system in an inconsistent state such as:

```text
Payment Failed
      +
Seat Still Locked
      +
Booking Still Confirmed
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📨 Event-Driven Architecture

SkyLedger uses **Apache Kafka** for asynchronous communication between services.

Example events include:

```text
BookingCreatedEvent
SeatLockedEvent
SeatReleasedEvent
PaymentInitiatedEvent
PaymentSuccessfulEvent
PaymentFailedEvent
BookingConfirmedEvent
BookingCancelledEvent
```

```mermaid
flowchart LR
    B["Booking Service"] -->|BookingCreatedEvent| K[("Kafka")]
    K --> S["Seat Service"]
    K --> P["Payment Service"]
    K --> N["Notification Service"]

    style K fill:#000,color:#fff
```

This reduces direct coupling between services and enables asynchronous processing.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🔐 Security

SkyLedger uses **Spring Security** with JWT-based authentication.

### Authentication Flow

```mermaid
sequenceDiagram
    participant C as Client
    participant A as Auth Service
    participant G as API Gateway
    participant M as Microservice

    C->>A: Login
    A->>C: JWT
    C->>G: Authorization: Bearer <token>
    G->>M: Forward Request
```

Security capabilities include:

- JWT authentication
- Role-based access control
- Protected REST endpoints
- Stateless authentication
- Token validation
- Token revocation using Redis

Example roles:

```text
ROLE_USER
ROLE_ADMIN
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## ⚡ Redis Caching

Redis is used to reduce repeated database access for frequently requested information.

Potential cached data includes:

```text
Flight Search Results
Fare Information
Seat Availability
JWT Revocation / Blacklist
```

```mermaid
flowchart TD
    C["Client"] --> F["Flight Service"]
    F -->|Cache HIT| R[("Redis")] --> Resp1["Response"]
    F -->|Cache MISS| M[("MySQL")] --> R2[("Redis")] --> Resp2["Response"]
```

Cache invalidation is triggered when relevant booking or seat state changes.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🌐 API Gateway

The API Gateway acts as the single entry point into the distributed system.

Responsibilities include:

- Request routing
- Authentication filtering
- Authorization
- Service discovery integration
- Centralized cross-cutting concerns
- API abstraction

```text
/api/auth/**       → Auth Service
/api/flights/**    → Flight Service
/api/fares/**      → Fare Service
/api/seats/**      → Seat Service
/api/bookings/**   → Booking Service
/api/payments/**   → Payment Service
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🔍 Service Discovery

SkyLedger uses **Netflix Eureka** for service registration and discovery.

```mermaid
flowchart TD
    E["Eureka Server"] --- FS["Flight Service"]
    E --- BS["Booking Service"]
    E --- PS["Payment Service"]
```

Services register themselves with Eureka, allowing other services and the API Gateway to locate them dynamically.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🔗 Inter-Service Communication

SkyLedger uses two communication mechanisms.

### Synchronous Communication — OpenFeign

Used when an immediate response is required.

```mermaid
flowchart LR
    Booking["Booking Service"] -->|REST / OpenFeign| Fare["Fare Service"]
```

### Asynchronous Communication — Apache Kafka

Used for:

- Booking events
- Seat state changes
- Payment events
- Notification events
- Saga coordination

```mermaid
flowchart LR
    Service --> Topic[("Kafka Topic")]
    Topic --> ConA["Consumer A"]
    Topic --> ConB["Consumer B"]
    Topic --> ConC["Consumer C"]
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🛡️ Resilience & Fault Tolerance

Where implemented, SkyLedger uses **Resilience4j** for distributed-system resilience.

Patterns include:

- Circuit Breaker
- Retry
- Timeout handling
- Fallback mechanisms

```mermaid
flowchart TD
    B["Booking Service"] --> P["Payment Service"]
    P -->|Available| Cont["Continue"]
    P -->|Unavailable| CB["Circuit Breaker"] --> FB["Fallback"]

    style CB fill:#c62828,color:#fff
    style FB fill:#ef6c00,color:#fff
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 💳 Payment Integration

Where enabled, SkyLedger integrates **Razorpay** for payment processing.

```mermaid
flowchart TD
    Booking --> PaymentSvc["Payment Service"] --> Razorpay
    Razorpay -->|Success| PS["PaymentSuccessEvent"]
    Razorpay -->|Failure| PF["PaymentFailedEvent"]

    style PS fill:#2e7d32,color:#fff
    style PF fill:#c62828,color:#fff
```

> Payment integration should only be documented as implemented if the repository contains the actual Razorpay integration.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📧 Notification System

The Notification Service handles post-booking communication.

```mermaid
flowchart LR
    E["BookingConfirmedEvent"] --> N["Notification Service"] --> Msg["Email / Notification"]
```

Possible notifications:

- Booking confirmation
- Payment confirmation
- Booking cancellation
- Payment failure

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🗄️ Database Architecture

SkyLedger follows the **Database-per-Service** principle.

```mermaid
flowchart LR
    Flight["Flight Service"] --> FDB[("Flight DB")]
    Fare["Fare Service"] --> FaDB[("Fare DB")]
    Seat["Seat Service"] --> SDB[("Seat DB")]
    Booking["Booking Service"] --> BDB[("Booking DB")]
    Payment["Payment Service"] --> PDB[("Payment DB")]
```

This provides:

- Service autonomy
- Reduced database coupling
- Independent schema evolution
- Better fault isolation
- Clear ownership of domain data

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🛠️ Technology Stack

<div align="center">

| Category | Technologies |
|---|---|
| **Backend** | Java 17 · Spring Boot · Spring Cloud · Spring Security · Spring Data JPA · Hibernate · Maven |
| **Microservices** | Spring Cloud Gateway · Netflix Eureka · OpenFeign · Resilience4j |
| **Messaging** | Apache Kafka |
| **Database** | MySQL |
| **Caching** | Redis |
| **Security** | JWT · Spring Security · Role-Based Access Control |
| **DevOps** | Docker · Docker Compose · Google Jib · Spring Boot Actuator |
| **Integrations** | Razorpay · Email notification service |

</div>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📁 Project Structure

```text
SkyLedger/
│
├── api-gateway/
├── service-registry/
├── auth-service/
├── flight-service/
├── fare-service/
├── seat-service/
├── booking-service/
├── payment-service/
├── notification-service/
├── docker-compose.yml
├── README.md
└── docs/
    ├── architecture/
    ├── api/
    ├── database/
    └── diagrams/
```

> Rename these modules to exactly match the repository structure.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🔄 End-to-End Booking Flow

```mermaid
flowchart TD
    S1["1. User searches for a flight"] --> S2["2. Flight Service returns available flights"]
    S2 --> S3["3. User selects flight and fare"]
    S3 --> S4["4. Fare Service provides pricing"]
    S4 --> S5["5. Seat Service checks availability"]
    S5 --> S6["6. Seat is temporarily locked"]
    S6 --> S7["7. Booking is created"]
    S7 --> S8["8. Payment is initiated"]
    S8 --> S9["9. Payment succeeds"]
    S9 --> S10["10. Booking is confirmed"]
    S10 --> S11["11. Seat becomes permanently assigned"]
    S11 --> S12["12. Confirmation event is published"]
    S12 --> S13["13. Notification Service sends confirmation"]
```

### Failure Scenario

```mermaid
flowchart TD
    B["Booking"] --> SL["Seat Locked"]
    SL --> Pay["Payment"]
    Pay --> PF["Payment Failed"]
    PF --> Comp["Compensation"]
    Comp --> CB["Cancel Booking"]
    Comp --> RS["Release Seat"]

    style PF fill:#c62828,color:#fff
    style Comp fill:#ef6c00,color:#fff
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🧪 Testing

Testing strategy can include:

- Unit testing
- Controller testing
- Service-layer testing
- Repository testing
- Integration testing
- API testing
- Kafka event testing
- Security testing

Recommended tools:

```text
JUnit 5
Mockito
Spring Boot Test
MockMvc
Testcontainers
Postman
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🐳 Docker Deployment

SkyLedger is designed to run as a collection of containerized services.

```text
Docker Compose
│
├── API Gateway
├── Eureka Server
├── Auth Service
├── Flight Service
├── Fare Service
├── Seat Service
├── Booking Service
├── Payment Service
├── Notification Service
├── Kafka
├── Zookeeper / Kafka Controller
├── Redis
└── MySQL
```

Start the complete environment:

```bash
docker compose up -d
```

Stop the environment:

```bash
docker compose down
```

View running containers:

```bash
docker compose ps
```

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## ⚙️ Local Development

### Prerequisites

Install:

```text
Java 17+
Maven 3.9+
MySQL 8+
Redis
Apache Kafka
Docker
Docker Compose
Git
```

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

### Clone Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd SkyLedger
```

### Configuration

Create the required environment variables or application configuration files.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/skyledger
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.data.redis.host=${REDIS_HOST}
spring.data.redis.port=${REDIS_PORT}

spring.kafka.bootstrap-servers=${KAFKA_BOOTSTRAP_SERVERS}

jwt.secret=${JWT_SECRET}
```

> ⚠️ Never commit: Passwords · JWT secrets · API keys · Razorpay keys · SMTP credentials · Database credentials

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📡 Example API Endpoints

> Replace these examples with the actual endpoints implemented in the project.

<details>
<summary><b>Authentication</b></summary>

```http
POST /api/auth/register
POST /api/auth/login
POST /api/auth/logout
```
</details>

<details>
<summary><b>Flights</b></summary>

```http
GET /api/flights/search
GET /api/flights/{id}
```
</details>

<details>
<summary><b>Fares</b></summary>

```http
GET /api/fares/{flightId}
```
</details>

<details>
<summary><b>Seats</b></summary>

```http
GET /api/seats/{flightId}
POST /api/seats/lock
POST /api/seats/release
```
</details>

<details>
<summary><b>Bookings</b></summary>

```http
POST /api/bookings
GET /api/bookings/{id}
DELETE /api/bookings/{id}
```
</details>

<details>
<summary><b>Payments</b></summary>

```http
POST /api/payments/create
POST /api/payments/verify
```
</details>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📊 Architecture Patterns Used

| Pattern | Purpose |
|---|---|
| **Microservices** | Independent business services |
| **API Gateway** | Centralized request routing |
| **Service Discovery** | Dynamic service registration |
| **Database per Service** | Data ownership and isolation |
| **Saga Pattern** | Distributed transaction management |
| **Event-Driven Architecture** | Asynchronous communication |
| **Circuit Breaker** | Failure isolation |
| **Retry Pattern** | Temporary failure recovery |
| **Caching** | Reduce database load |
| **JWT Authentication** | Stateless authentication |
| **RBAC** | Role-based authorization |

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🧠 Key Engineering Challenges

### 1. Distributed Transactions

A booking operation can span Booking, Seat, Payment, and Notification services. Since these services maintain independent databases, a traditional ACID transaction cannot span the entire workflow.

**Solution:** Saga-based orchestration/choreography with compensating operations.

### 2. Seat Concurrency

Multiple users may attempt to book the same seat simultaneously.

```mermaid
stateDiagram-v2
    [*] --> AVAILABLE
    AVAILABLE --> LOCKED
    LOCKED --> BOOKED
    LOCKED --> AVAILABLE: expired / failed
    BOOKED --> [*]
```

### 3. Service Failures

A downstream service may become unavailable during a booking request. Resilience patterns such as Timeout, Retry, Circuit Breaker, and Fallback can prevent failures from cascading through the system.

### 4. Cache Consistency

Cached flight, fare, and seat data can become stale. The system therefore requires appropriate cache invalidation when booking state changes.

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📈 Future Enhancements

- Dynamic airline pricing
- Multi-airline inventory
- Multi-city booking
- Round-trip booking
- PNR generation
- Flight cancellation and rescheduling
- Refund workflows
- Loyalty/reward points
- Admin dashboard
- Real-time seat updates using WebSockets
- Distributed tracing
- Prometheus metrics
- Grafana dashboards
- ELK/OpenSearch centralized logging
- Kubernetes deployment
- CI/CD pipeline
- Testcontainers-based integration testing
- OpenTelemetry observability

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📚 Learning Outcomes

Through SkyLedger, the project demonstrates practical experience with:

<table>
<tr>
<td valign="top" width="33%">

**Backend Engineering**
- Java
- Spring Boot
- REST APIs
- JPA/Hibernate
- Database design

**Distributed Systems**
- Microservices
- Service discovery
- API Gateway
- Distributed communication
- Distributed transactions

</td>
<td valign="top" width="33%">

**Event-Driven Systems**
- Kafka producers
- Kafka consumers
- Event-based workflows
- Saga pattern
- Compensation events

**Security**
- JWT
- Spring Security
- RBAC
- Token revocation

</td>
<td valign="top" width="33%">

**Performance**
- Redis caching
- Cache invalidation
- Database optimization

**DevOps**
- Docker
- Docker Compose
- Containerized services
- Jib
- Actuator

</td>
</tr>
</table>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 📸 Screenshots

Add project screenshots to `/docs/screenshots/`

Recommended screenshots:

1. Login / Registration
2. Flight Search
3. Flight Results
4. Seat Selection
5. Booking Summary
6. Payment
7. Booking Confirmation
8. Admin Dashboard
9. Eureka Dashboard
10. Kafka Events
11. Docker Containers
12. API Gateway

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 🎥 Project Reference

This project was developed as a practical implementation of microservices, event-driven architecture, distributed transactions, and airline reservation domain concepts.

Reference: [Project Tutorial / Reference Video](https://youtu.be/yFoI4a3HbO0?utm_source=chatgpt.com)

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## 👨‍💻 Author

<div align="center">

### **Vinoth**
Graduate Software Engineer  
Java | Spring Boot | Microservices | SQL | Angular

[![GitHub](https://img.shields.io/badge/GitHub-Vinoth1615-181717?style=for-the-badge&logo=github)](https://github.com/Vinoth1615)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-vinothkumardeva-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/vinothkumardeva/)
[![Portfolio](https://img.shields.io/badge/Portfolio-vinoth--kumar-000000?style=for-the-badge&logo=vercel&logoColor=white)](https://vinoth-kumar-portfolio-eta.vercel.app/)

</div>

<img src="https://capsule-render.vercel.app/api?type=rect&color=0:2a5298,100:1e3c72&height=3&width=100%"/>

## ⭐ If You Find This Project Useful

Give the repository a ⭐ and feel free to explore the architecture, implementation, and distributed booking workflow.

## 📄 License

This project is licensed under the MIT License.

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:1e3c72,100:2a5298&height=150&section=footer&animation=fadeIn"/>
