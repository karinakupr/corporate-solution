A RESTful Spring Boot API for user authentication and product management with role-based access controFeatures:

- User login with JWT token generation
- Role-based authorization (ADMIN, USER)
- Product management (CRUD)
- VAT-based price calculation
- Audit logging for product changes
- 
- 
  Default Users:
  | Username | Password | Role |
  |----------|-----------|-------|
  | admin | admin123 | ADMIN |
  | karina | kupr123 | USER |
- 
  Authentication:
  POST /api/users/login
  {
  "name": "admin",
  "password": "admin123"
  }
- 
  Use the token in headers:
  Authorization: Bearer <your_token>
  Product Endpoints:
- GET /api/products
- POST /api/products (ADMIN only)
- PUT /api/products/{id} (ADMIN only)
- DELETE /api/products/{id} (ADMIN only)