# ============================
#   STAGE 1 — BUILD APPLICATION
# ============================
FROM gradle:8.8-jdk21 AS builder
WORKDIR /app

# Copy Gradle wrapper and project files
COPY gradlew gradlew.bat ./
COPY gradle/ ./gradle/
COPY build.gradle settings.gradle ./
COPY src ./src

# Ensure gradlew is executable
RUN chmod +x ./gradlew

# Build the Spring Boot jar
RUN ./gradlew bootJar --no-daemon

# ============================
#   STAGE 2 — RUNTIME IMAGE
# ============================
FROM eclipse-temurin:21-jre-alpine AS runtime

# Install curl for healthcheck
RUN apk add --no-cache curl bash

# Non-root user for security
RUN addgroup --system spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copy the jar from builder stage (supports wildcard)
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8282

# Healthcheck using curl
HEALTHCHECK --interval=30s --timeout=5s --start-period=20s --retries=3 \
  CMD curl -fsS http://localhost:8282/actuator/health | grep '"status":"UP"' || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]