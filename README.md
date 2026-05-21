# Taxi Dispatch Service - JDBC

A robust **Command Line Interface (CLI)** Taxi Dispatch System built with pure **JDBC** and Oracle Database.

## Overview

This project implements a complete taxi dispatch service using vanilla JDBC (no ORM). It demonstrates advanced database operations, proper entity modeling, inheritance (IS-A), gerund/associative entities, and clean layered architecture.

The system manages clients, drivers, vehicles, employees, ride orders, ride executions, and tariff groups.

## Features

- Full CRUD operations for all entities
- Support for **IS-A** inheritance hierarchy
- Complex relationships using gerund entities (`NarucujeVoznju` and `ObavljaVoznju`)
- Ride ordering and execution workflow
- Tariff group and pricing management
- **Simple and complex SQL queries** (from basic SELECTs to multi-table joins, aggregations, and nested queries)
- Connection pooling with HikariCP
- Transaction management and prepared statements
- Clean CLI interface

## Technologies

- **Java**
- **JDBC** (Pure JDBC - main focus of the project)
- **HikariCP** - JDBC Connection Pool
- **Oracle Database**
- **Maven** (build tool)

## Project Architecture

- **Model** – Domain entities (`Klijent`, `Vozac`, `Vozilo`, `Zaposleni`, `TarifnaGrupa`, etc.)
- **DTO** – Data Transfer Objects
- **DAO** – Data Access Layer (pure JDBC implementations)
- **Service** – Business logic layer
- **UI Handler** – Command Line Interface
- **Hikari** – Database connection configuration

## Domain Model

### Entities
- `Klijent` (Client)
- `Vozac` (Driver)
- `Vozilo` (Vehicle)
- `Zaposleni` (Employee)
- `TarifnaGrupa` (Tariff Group)

### Relationships
- **IS-A Hierarchy** (Inheritance)
- **Gerund Entities**:
  - `NarucujeVoznju` – Ride Ordering (associative entity)
  - `ObavljaVoznju` – Ride Execution (associative entity)

## How to Run

1. Clone the repository
2. Configure database connection in `src/main/resources/application.properties` (or hikari config)
3. Run the SQL scripts to create tables and constraints
4. Build the project:
   ```bash
   mvn clean install
