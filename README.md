# E-Commerce Backend

A RESTful e-commerce backend built with Java and Spring Boot. It demonstrates layered backend architecture, REST APIs, JPA persistence, relational entity relationships, cart operations, stock management, and checkout/order processing.

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- REST APIs
- Maven

## Features
- User creation, retrieval, and deletion
- Product CRUD and name search
- Shopping cart creation and item management
- Stock validation when adding items to a cart
- Checkout directly from the cart
- Automatic stock reduction after checkout
- Order retrieval by ID or user
- Order status updates
- Request validation and centralized exception handling

## Architecture
The application follows a layered structure:

`Controller -> Service -> Repository -> MySQL`

Controllers handle HTTP requests, services contain business logic, repositories handle persistence through Spring Data JPA, and MySQL stores application data.

## Database Setup
1. Create a MySQL database named `ecommerce_db`.
2. Set these environment variables if your MySQL credentials differ from the defaults:

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

Example:
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

Example:
```json
{"name":"Wireless Headphones","price":2499.0,"stock":20}
```

### Cart
- `POST /api/carts/{userId}`
- `GET /api/carts/{userId}/items`
- `POST /api/carts/{userId}/items?productId=1&quantity=2`
- `PATCH /api/carts/items/{itemId}?quantity=3`
- `DELETE /api/carts/items/{itemId}`

### Orders
- `POST /api/orders/checkout/{userId}`
- `GET /api/orders`
- `GET /api/orders/{id}`
- `GET /api/orders/user/{userId}`
- `PATCH /api/orders/{id}/status?status=SHIPPED`

## Project Structure

```text
src/main/java/com/pardhavsai/ecommerce
├── controller
├── entity
├── exception
├── repository
└── service
```

## Testing
Run the test suite with:

```bash
mvn test
```

## Future Improvements
- DTOs for API request/response models
- Authentication and authorization
- Pagination for product and order queries
- Integration tests with a dedicated test database
