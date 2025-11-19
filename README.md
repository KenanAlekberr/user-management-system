# 🚀 User Management Service — Intern Task

A production-ready User Management & Authentication Service built with Spring Boot, PostgreSQL, Redis, Kafka, and Docker
Compose.

This project was developed as part of an internship evaluation task — however, it follows real-world backend
architecture and clean coding practices.
It includes both mandatory User CRUD functionality and additional bonus features such as JWT authentication, caching,
email OTP, password reset, and Kafka events.

---

# 📌 Features

## 🎯 **Core Requirements (Mandatory)**

### User CRUD operations:

- Create a new user (Register)
- Get user by ID
- List users with pagination + filtering + sorting
- Update user information
- Delete user (status = DELETED)

___ 

# ⭐ Bonus Features (Advanced)

Added intentionally to show extra backend skills:

### 🔐 Authentication

- JWT Access & Refresh tokens
- BCrypt password hashing
- Login / Logout
- Forgot / Reset / Change password
- Token blacklist with Redis (logout)
- Refresh token rotation
- Secure endpoints with Spring Security

### 📧 Email Verification

- Generate OTP
- Send OTP via email (Mail Service)
- Verify email during registration

### 🧵 Kafka Integration

- User create/update events published to Kafka
- Demo consumer for analytics/logging
- Pluggable architecture for microservice communication

### ⚡ Redis Caching (Redisson)

- Caching for user list & user by ID
- Custom Redisson cache utility
- Cache invalidation after updates
- Automatic key formatting (prefix-based)

### 🧹 Validation

- Spring Boot Validation (@Valid, @NotNull, @Email, etc.)
- Custom exception handlers
- Global error response structure

### 🧱 Database Migrations (Liquibase)

- Auto-applied schema scripts
- Versioned SQL changesets
- Rollback support

---

#  🛠️ Tech Stack

### Backend

- Java 21
- Spring Boot (Web, Security, Validation, Data JPA)

### Database

- PostgreSQL
- Liquibase

### Caching

- Redis
- Redisson (advanced distributed features)

### Messaging

- Apache Kafka

### Authentication

- JWT (JJWT + Nimbus)
- BCrypt password encoder

### Other

- Lombok
- Mail Service
- Docker & Docker Compose
- Swagger

___

# 🔍 Advanced Query Features

This project implements enterprise-level data querying using Spring Data Pagination, Sorting, Filtering, and JPA
Specifications, combined with Redis caching for optimal performance.
___

# 📄 1️⃣ Pagination

All paginated responses return a PaginationResponse<T> structure containing:

- **content** — List of users for the current page
- **totalElements** — Total number of users in the database
- **totalPages** — Total pages based on the page size
- **hasNext** — Indicates whether the next page exists

___

# 🌐 Deployment

### The project is deployed live on Render and can be accessed here:

### 🔗 Production URL:
https://user-management-system-oyhr.onrender.com

### 🔗 Swagger UI:
https://user-management-system-oyhr.onrender.com/swagger-ui/index.html