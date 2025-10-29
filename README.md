# 🎟️ Ticket Buying Backend System

A microservices-based backend for booking and managing event tickets, built with **Spring Boot**, **Spring Cloud Gateway**, **Keycloak**, **MySQL**, and **Docker**.  
This project demonstrates scalable microservice communication, centralized authentication, and fault tolerance with **Resilience4j**.

---

## 🧩 Architecture Overview

The system follows a **microservices architecture**, where each module handles a specific responsibility:

| Service | Description | Port |
|----------|--------------|------|
| **API Gateway** | Routes client requests to the appropriate microservice and secures them via Keycloak. | `8090` |
| **Inventory Service** | Manages events and ticket availability. | `8081` |
| **Booking Service** | Handles ticket reservations and booking operations. | `8082` |
| **Keycloak** | Authentication and authorization server (OpenID Connect). | `9090` |
| **MySQL** | Database used by microservices and Keycloak. | `3306` |
| **Kafka + Zookeeper** | Enables asynchronous event communication between services. | `9092`, `2181` |

---

## 🏗️ Technologies Used

- **Java 21**
- **Spring Boot 3.5+**
- **Spring Cloud Gateway (MVC)**
- **Spring Data JPA**
- **Keycloak (OIDC Authorization)**
- **Resilience4j (Circuit Breaker, Retry, Rate Limiter)**
- **Kafka & Zookeeper**
- **MySQL**
- **Docker & Docker Compose**
- **Swagger / OpenAPI for documentation**

---

## ⚙️ Running the Project

### 🐳 1. Start via Docker Compose
In the project root directory, simply run:

```bash
docker-compose up -d
