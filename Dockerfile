# Stage 1: Build the Spring Boot application
FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app

# Copy pom.xml first to cache dependencies
COPY pom.xml .
# Copy source code
COPY src ./src

# Build the application without tests
RUN mvn package -DskipTests

# Stage 2: Runtime image using Eclipse Temurin 17 Alpine
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose port 8080 (optional, metadata only)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]