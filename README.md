
# Book Service API

A Spring Boot application for managing books, allowing users to create, read, update, and delete book records. This project demonstrates RESTful API development with proper error handling, validation, and consistent response formatting.

## Table of Contents
- [Features](#features)
- [Technologies](#technologies)
- [Project Structure](#project-structure)
- [Setup](#setup)
- [API Endpoints](#api-endpoints)
- [Error Handling](#error-handling)
- [Examples](#examples)

## Features

- **CRUD Operations**: Basic operations for managing book data.
- **Validation**: Ensures required fields (e.g., ISBN) are provided and unique.
- **Error Handling**: Provides consistent error responses for client applications.
- **Consistent API Response**: Standardized response format using `ApiResponse` class.

## Technologies

- **Java 17**
- **Spring Boot** (Web, JPA, Validation)
- **PostgreSQL** for database (or **H2** for local testing)
- **Maven** for dependency management

## Project Structure

```plaintext
src/
└── main/
    └── java/
        └── com/
            └── vinhnt/
                └── book_service/
                    ├── controller/          # Controllers with API endpoints
                    │   └── BookController.java
                    ├── dto/                 # Data transfer objects and response structures
                    │   └── ApiResponse.java
                    ├── exception/           # Custom exception handling
                    │   └── GlobalExceptionHandler.java
                    ├── model/               # JPA entities
                    │   └── Book.java
                    ├── repository/          # Repository for database operations
                    │   └── BookRepository.java
                    └── service/             # Business logic layer
                        └── BookService.java
    └── resources/
        └── application.properties           # Application configuration
       
```

## Setup

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/1712916/java-spring-book-service.git
   cd java-spring-book-service
   ```

2. **Database Configuration**:
   Configure the database in `src/main/resources/application.properties`:

   ```properties
   # Application Configuration
   spring.application.name=book_service
   server.port=9999
   # Database configuration
   spring.datasource.url=jdbc:postgresql://localhost:32768/book_db
   spring.datasource.username=postgres
   spring.datasource.password=postgrespw
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true
   logging.level.org.springframework=DEBUG
   ```

3. **Run the Application**:
   ```bash
   ./mvnw spring-boot:run
   ```
   The application will start on [http://localhost:9999](http://localhost:9999).

## API Endpoints

### Base URL
`http://localhost:9999/api/books`

### Endpoints

| Method | Endpoint       | Description                   | Request Body         |
|--------|----------------|-------------------------------|----------------------|
| GET    | `/`            | Retrieve all books            |                      |
| GET    | `/{id}`        | Retrieve a book by ID         |                      |
| POST   | `/`            | Create a new book             | `{ Book JSON }`      |
| PUT    | `/{id}`        | Update an existing book       | `{ Book JSON }`      |
| DELETE | `/{id}`        | Delete a book by ID           |                      |

### Request Body Format for Book
```json
{
  "title": "Effective Java",
  "author": "Joshua Bloch",
  "publishedDate": "2018-01-06",
  "isbn": "9780134685991",
  "price": 45.99
}
```

### Example `ApiResponse` Format

All API responses use a consistent format:
```json
{
  "success": true,
  "message": "Your custom message here",
  "data": { ... }
}
```

## Error Handling

Custom error handling is implemented using `GlobalExceptionHandler` with `ApiResponse`. Common errors include:
- **400 Bad Request**: Validation errors, missing required fields.
- **404 Not Found**: When a requested book is not found.
- **500 Internal Server Error**: Unexpected errors.

## Examples

### 1. Create a New Book

**Request**:
```bash
curl -X POST http://localhost:9999/api/books      -H "Content-Type: application/json"      -d '{
           "title": "Spring Boot in Action",
           "author": "Craig Walls",
           "publishedDate": "2016-11-28",
           "isbn": "9781617292545",
           "price": 39.99
         }'
```

**Response**:
```json
{
  "success": true,
  "message": "Book created successfully",
  "data": {
    "id": 1,
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "publishedDate": "2016-11-28",
    "isbn": "9781617292545",
    "price": 39.99
  }
}
```

### 2. Retrieve a Book by ID

**Request**:
```bash
curl -X GET http://localhost:9999/api/books/1
```

**Response**:
```json
{
  "success": true,
  "message": "Retrieved book with ID 1 successfully",
  "data": {
    "id": 1,
    "title": "Spring Boot in Action",
    "author": "Craig Walls",
    "publishedDate": "2016-11-28",
    "isbn": "9781617292545",
    "price": 39.99
  }
}
```

### 3. Delete a Book by ID

**Request**:
```bash
curl -X DELETE http://localhost:9999/api/books/1
```

**Response**:
```json
{
  "success": true,
  "message": "Deleted book with ID 1 successfully",
  "data": null
}
```