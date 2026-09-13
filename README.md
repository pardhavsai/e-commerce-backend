# E-Commerce Backend

A RESTful e-commerce backend built with Java and Spring Boot. The project demonstrates layered backend architecture, REST APIs, JPA persistence, entity relationships, cart operations, and order management.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs
- Maven

## Features
- User creation and retrieval
- Product CRUD and name search
- Shopping cart creation and item management
- Order retrieval and status updates
- MySQL persistence through Spring Data JPA
- Controller-based REST API design

## Architecture
The application follows a simple layered structure:

`Controller -> Service/Repository layer -> Database`

The current implementation keeps the business logic intentionally small so the core Spring Boot, REST, JPA, and relational-database concepts are easy to understand and extend.

## Database Setup
1. Create a MySQL database named `ecommerce_db`.
2. Update the environment variables below if your MySQL credentials differ:

```text
DB_URL=jdbc:mysql://localhost:3306/ecommerce_db
DB_USERNAME=root
DB_PASSWORD=root
```

3. Run the application with Maven:

```bash
mvn spring-boot:run
```

The API starts on `http://localhost:8080`.

## Main Endpoints

### Users
- `GET /api/users`
- `GET /api/users/{id}`
- `POST /api/users`
- `DELETE /api/users/{id}`

Example user JSON:
```json
{"name":"Pardhav","email":"pardhav@example.com"}
```

### Products
- `GET /api/products`
- `GET /api/products/{id}`
- `GET /api/products/search?name=phone`
- `POST /api/products`
- `PUT /api/products/{id}`
- `DELETE /api/products/{id}`

Example product JSON:
```json
{"name":"Wireless Headphones","price":2499.0,"stock":20}
```

### Cart
- `POST /api/carts/{userId}`
- `GET /api/carts/{userId}/items`
- `POST /api/carts/{userId}/items?productId=1&quantity=2`
- `DELETE /api/carts/items/{itemId}`

### Orders
- `GET /api/orders`
- `GET /api/orders/{id}`
- `GET /api/orders/user/{userId}`
- `PATCH /api/orders/{id}/status?status=SHIPPED`

## Future Improvements
- Service layer for business logic
- DTOs and centralized exception handling
- Order creation directly from the cart
- Authentication and authorization
- Automated unit and integration tests
