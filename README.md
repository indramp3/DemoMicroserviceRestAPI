# Demo Bank Microservices

This project consists of two simple Spring Boot microservices:

1. `account` — Internal microservice managing database operations for the `ACCOUNT_MASTER` table.
2. `gateway` — An API facade that exposes REST endpoints to clients and forwards requests to the `account` microservice.

## Prerequisites

- Java 17
- Maven
- MySQL Database

## Database Setup

Ensure you have a MySQL server running with the following credentials:

- **Host**: 127.0.0.1
- **Port**: 3306
- **Database Name**: `demo_bank` (Create this database manually if it doesn't exist: `CREATE DATABASE demo_bank;`)
- **Username**: root
- **Password**: root

The `account` microservice will automatically create/update the `ACCOUNT_MASTER` table using Hibernate.

## Running the Microservices

### 1. Account Microservice

Navigate to the `account` directory and run the application. It will start on port `8081`.

```bash
cd account
mvn spring-boot:run
```

### 2. Gateway Microservice

Open a new terminal, navigate to the `gateway` directory, and run the application. It will start on port `8080`.

```bash
cd gateway
mvn spring-boot:run
```

## API Endpoints (via Gateway)

All client requests should be directed to the `gateway` on port `8080`. The gateway routes them to the `account` service.

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/gateway/accounts` | Retrieve all accounts |
| `GET` | `/api/gateway/accounts/{nik}` | Retrieve a specific account by NIK |
| `POST` | `/api/gateway/accounts` | Create a new account |
| `PUT` | `/api/gateway/accounts/{nik}` | Update an existing account |
| `DELETE`| `/api/gateway/accounts/{nik}` | Delete an account |

### Postman Collection

A Postman collection named `Demo_Bank_Microservices.postman_collection.json` is provided in the root directory. You can import this directly into Postman to test all endpoints.
