# Use OpenJDK 21 for Spring Boot
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY backend/mvnw .
COPY backend/.mvn .mvn
COPY backend/pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY backend/src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create runtime directory
RUN mkdir -p /app/runtime

# Copy the built JAR
RUN cp target/ledger-backend-0.0.1-SNAPSHOT.jar /app/runtime/

# Set working directory to runtime
WORKDIR /app/runtime

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/api/ledger || exit 1

# Run the application
CMD ["java", "-jar", "ledger-backend-0.0.1-SNAPSHOT.jar"]
