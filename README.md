# Bookshop REST API

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

A fully-featured REST API for managing books and their authors in a bookshop system.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Database Schema](#database-schema)
- [API Documentation](#api-documentation)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [License](#license)

## Features

### Book Management
- Full CRUD operations for books
- Advanced filtering:
  - By maximum price
  - With/without author details
- Get all books by specific author
- Comprehensive validation

### Author Integration
- Books are linked to authors
- Author information included in book responses

## Technologies

- **Backend**:
  - Java 17
  - Spring Boot 3.x
  - Spring Data JPA
  - Maven

- **Database**:
  - PostgreSQL 12+
  - Automatic schema generation
  - Pre-loaded sample data

- **Other**:
  - RESTful design principles
  - Exception handling

## Database Schema

```sql
CREATE TABLE author (
    author_id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100),
    birthday DATE
);

CREATE TABLE book (
    book_id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author_id UUID REFERENCES author(author_id),
    price DECIMAL(10,2) CHECK (price >= 0),
    language VARCHAR(50),
    FOREIGN KEY (author_id) REFERENCES author(author_id)
);
```

## API Endpoints

### Books

| Method |             Endpoint              |                        Description                        |
|--------|-----------------------------------|-----------------------------------------------------------|
| GET    | `/api/v1/books`                   | Get all books (optional: `?maxPrice=XX&withAuthor=false`) |
| GET    | `/api/v1/books/{id}`              | Get specific book                                         |
| GET    | `/api/v1/books/author/{authorId}` | Get books by author                                       |
| POST   | `/api/v1/books`                   | Create new book                                           |
| PUT    | `/api/v1/books/{id}`              | Update book                                               |
| DELETE | `/api/v1/books/{id}`              | Delete book                                               |

### Authors

- Authors hase no API Endpoints

## Setup & Installation

1. **Prerequisites**:
   - Java 17 JDK
   - PostgreSQL 12+
   - Maven

2. **Database Setup**:
   ```sql
   CREATE DATABASE bookshop;
   ```

3. **Configuration**:
    - Update application.properties with your database credentials if different from:

    - Copy
    - spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
    - spring.datasource.username=postgres
    - spring.datasource.password=postgres

## License

- No License
